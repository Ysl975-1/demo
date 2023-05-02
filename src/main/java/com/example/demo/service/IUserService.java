package com.example.demo.service;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ysl
 * @since 2023-05-01
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    int updateUser(User user);
}
