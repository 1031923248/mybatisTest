package com.amane.mybatistest.service.serviceImp;

import com.amane.mybatistest.mapper.UserMapper;
import com.amane.mybatistest.pojo.User;
import com.amane.mybatistest.service.UserService;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper,User> implements UserService {
}
