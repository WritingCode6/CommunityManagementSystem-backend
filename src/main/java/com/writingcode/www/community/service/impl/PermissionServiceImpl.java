package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.Permission;
import com.writingcode.www.community.dao.PermissionMapper;
import com.writingcode.www.community.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
