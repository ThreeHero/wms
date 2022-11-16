package com.threehero.warehouse.service.impl;

import com.threehero.warehouse.dao.User;
import com.threehero.warehouse.mapper.UserMapper;
import com.threehero.warehouse.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author threehero
 * @since 2022-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
