package com.example.demo.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ysl
 * @since 2023-05-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(User user) {
        String salt = "1a2b3c4d";
        // 查询用户名和密码
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, user.getName());
        wrapper.eq(User::getPassword, MD5Util.inputPassToDBPass(user.getPassword(), salt));

        User login_user = userMapper.selectOne(wrapper);

        //结果不为空，返回token
        if(null!=login_user){

            String key = "user:" + UUID.randomUUID();

            //存入redis
            redisTemplate.opsForValue().set(key, login_user, 30, TimeUnit.MINUTES);

            //返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", key);
            return data;
        }

        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        // 从redis获取信息

        Object o = redisTemplate.opsForValue().get(token);
        if(null != o){
            User parseObject = JSON.parseObject(JSON.toJSONString(o), User.class);
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", parseObject.getName());
            map.put("id", parseObject.getId());
            return map;
        }

        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

//    @Override
//    public void updateUser(User user) {
//
//    userMapper.insert(user);
//
//    }
    @Override
    public int updateUser(User user) {

        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<User>()
                .set(User::getName,user.getName())
                .eq(User::getId , user.getId());

        return userMapper.update(null,wrapper);
    }

}
