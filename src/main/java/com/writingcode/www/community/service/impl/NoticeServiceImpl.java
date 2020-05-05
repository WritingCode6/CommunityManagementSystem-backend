package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.po.Notice;
import com.writingcode.www.community.dao.NoticeMapper;
import com.writingcode.www.community.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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

    @Override
    public boolean updateCommunityNotice(Notice notice) {
        Assert.notNull(notice, "更新信息不能为空");
        Assert.notNull(notice.getId(), "主键不能为空");

        Assert.state(noticeMapper.updateById(notice) == 1, "更新失败");

        return true;
    }
}
