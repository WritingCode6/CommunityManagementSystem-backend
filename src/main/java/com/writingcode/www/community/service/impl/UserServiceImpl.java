package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.User;
import com.writingcode.www.community.dao.UserMapper;
import com.writingcode.www.community.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
