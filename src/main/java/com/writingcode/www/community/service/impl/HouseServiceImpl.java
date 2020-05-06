package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.dao.HouseholdInfoMapper;
import com.writingcode.www.community.dao.UserMapper;
import com.writingcode.www.community.entity.po.House;
import com.writingcode.www.community.dao.HouseMapper;
import com.writingcode.www.community.entity.po.HouseholdInfo;
import com.writingcode.www.community.entity.vo.HouseInfoVo;
import com.writingcode.www.community.service.IHouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements IHouseService {

    @Resource
    private HouseMapper houseMapper;

    @Resource
    private HouseholdInfoMapper householdInfoMapper;

    @Override
    public Page<HouseInfoVo> getHouseInfo(Long userId, Integer buildingNum, Page<HouseInfoVo> page) {

        Page<House> housePage = new Page<>(page.getCurrent(), page.getSize());

        QueryWrapper<House> queryWrapper = new QueryWrapper<>();
        if(userId != null){
            queryWrapper.eq(House.USER_ID, userId);
        }
        if(buildingNum != null){
            queryWrapper.eq(House.BUILDING_NUMBER, buildingNum);
        }

        housePage = houseMapper.selectPage(housePage, queryWrapper);
        //拿出全部的用户id
        List<Long> userIds = housePage.getRecords().stream().filter(house -> house.getUserId() != null).map(House::getUserId).collect(Collectors.toList());
        QueryWrapper<HouseholdInfo> householdInfoQueryWrapper = new QueryWrapper<>();
        householdInfoQueryWrapper.select(HouseholdInfo.USER_ID, HouseholdInfo.NAME);
        if(!userIds.isEmpty()) {
            householdInfoQueryWrapper.in(HouseholdInfo.USER_ID, userIds);
        }
        Map<Long, String> names = householdInfoMapper.selectList(householdInfoQueryWrapper)
                .stream().collect(Collectors.toMap(HouseholdInfo::getUserId, HouseholdInfo::getName));
        //设置name
        page.setRecords(housePage.getRecords().stream().map(house -> {
            HouseInfoVo houseInfoVo = HouseInfoVo.convert(house);
            if(house.getUserId() != null) {
                houseInfoVo.setName(names.get(house.getUserId()));
            }
            return houseInfoVo;
        }).collect(Collectors.toList()));
        page.setTotal(housePage.getTotal());
        return page;
    }
}
