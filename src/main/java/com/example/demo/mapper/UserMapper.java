package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ysl
 * @since 2023-05-01
 */
public interface UserMapper extends BaseMapper<User> {

    @Insert("Insert into t_user(id,name,password) VALUES (#{id},#{name},#{password})")
    int insert(User user);
}
