package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.vo.ReturnBack;
import com.example.demo.vo.ReturnBackEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ysl
 * @since 2023-05-01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ReturnBack getAllUser(){
        List<User> list = userService.list();
        return ReturnBack.success(list);
    }

    @PostMapping("/login")
    public ReturnBack login(@RequestBody User user){
        Map<String, Object> map = userService.login(user);

        if(map != null){
            return ReturnBack.success(map);
        }
        return ReturnBack.error(ReturnBackEnum.NOT_EXIST);
    }

    @GetMapping("/info")
    public ReturnBack getUserInfo(@RequestParam("token") String token){
        // 根据token获取用户信息

        Map<String, Object> info = userService.getUserInfo(token);

        if(info != null){
            return ReturnBack.success(info);
        }
        return ReturnBack.error(ReturnBackEnum.NO_INFO);
    }

    @PostMapping("/logout")
    public ReturnBack logout(@RequestHeader("X-Token") String token){
        userService.logout(token);
        return ReturnBack.success();
    }
    @PostMapping("/updateUser")
    public ReturnBack updateUser(@RequestBody User user){
        int i = userService.updateUser(user);
        if (i == 1)
            return ReturnBack.success(i);
        else
            return ReturnBack.error(ReturnBackEnum.UPDATE_ERROR);
    }
    @PostMapping("/add")
    public ReturnBack addRecord(@RequestBody User user){
        int insert = userMapper.insert(user);
        if (insert == 1)
            return ReturnBack.success(insert);
        else
            return ReturnBack.error(ReturnBackEnum.ADD_ERROR);
    }



}
