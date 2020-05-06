package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.writingcode.www.community.dao.StaffInfoMapper;
import com.writingcode.www.community.entity.po.Notice;
import com.writingcode.www.community.dao.NoticeMapper;
import com.writingcode.www.community.entity.po.StaffInfo;
import com.writingcode.www.community.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private StaffInfoMapper staffInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateCommunityNotice(Notice notice) {
        Assert.notNull(notice, "更新信息不能为空");
        Assert.notNull(notice.getId(), "主键不能为空");
        Assert.state(notice.getTitle() != null || notice.getContent() != null, "更新内容不能为空");

        Assert.state(noticeMapper.updateById(notice) == 1, "更新失败");

        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addCommunityNotice(Notice notice) {
        Assert.notNull(notice, "新增信息不能为空");
        Assert.notNull(notice.getEmployeeId(), "用户id不能为空");
        Assert.notNull(notice.getTitle(), "标题不能为空");
        Assert.notNull(notice.getContent(), "内容不能为空");

        Assert.state(staffInfoMapper.selectStaffExist(notice.getEmployeeId()), "用户不存在或不是工作人员");

        Assert.state(noticeMapper.insert(notice) == 1, "新增失败");

        return true;
    }

    @Override
    public Page<Notice> getCommunityNotice(Page<Notice> page) {
        page = noticeMapper.selectPage(page, null);
        return page;
    }
}
