package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.Activity;
import com.writingcode.www.community.dao.ActivityMapper;
import com.writingcode.www.community.service.IActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements IActivityService {

}
