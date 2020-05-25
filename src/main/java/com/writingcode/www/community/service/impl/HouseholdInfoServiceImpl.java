package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.writingcode.www.community.dao.*;
import com.writingcode.www.community.entity.dto.HouseholdPageDto;
import com.writingcode.www.community.entity.po.*;
import com.writingcode.www.community.entity.vo.HouseHoldVo;
import com.writingcode.www.community.entity.vo.HouseUserVo;
import com.writingcode.www.community.service.IHouseholdInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class HouseholdInfoServiceImpl extends ServiceImpl<HouseholdInfoMapper, HouseholdInfo> implements IHouseholdInfoService {

    @Resource
    private HouseholdInfoMapper householdInfoMapper;

    @Resource
    private HouseMapper houseMapper;

    @Resource
    private HouseUserMapper houseUserMapper;

    @Resource
    private CarMapper carMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;


    @Override
    public Page<HouseUserVo> searchUser(String name, Integer buildingNum, Integer roomNum, Page<HouseUserVo> page) {
        HouseholdPageDto householdPageDto = new HouseholdPageDto()
                .setBuildingNum(buildingNum)
                .setRoomNum(roomNum)
                .setName(name)
                .setSize(page.getSize()).setOffset((page.getCurrent() - 1) * page.getSize());
        List<HouseUserVo> houseUserVoList = householdInfoMapper.selectHouseUser(householdPageDto);

        houseUserVoList.forEach(houseUserVo -> {
            Car car = carMapper.selectByUserId(houseUserVo.getUserId());
            if(car != null){
                houseUserVo.setPlateNumber(car.getPlateNumber());
            }
        });

        page.setRecords(houseUserVoList);
        page.setTotal(householdInfoMapper.selectHouseUserCount(householdPageDto));
        return page;
    }

    @Override
    public boolean updateUserInfo(HouseholdInfo householdInfo) {
        Assert.notNull(householdInfo, "更新内容不能为空");
        Assert.notNull(householdInfo.getId(), "主键不能为空");

        Assert.state(householdInfoMapper.updateById(householdInfo) == 1, "更新失败");
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(HouseHoldVo houseHoldVo) {
        Assert.notNull(houseHoldVo, "传入信息不能为null");
        houseHoldVo.isNotNull();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(User.USER_NAME, houseHoldVo.getUserName());
        Assert.state(userMapper.selectOne(queryWrapper) == null, "该用户名已存在");
        Assert.state(userMapper.insert(new User().setUserName(houseHoldVo.getUserName()).setPassword(houseHoldVo.getPassword())) == 1,
                "添加用户失败");
        User user = userMapper.selectOne(queryWrapper);
        userRoleMapper.insert(new UserRole().setUserId(user.getId()).setRoleId(2));

        QueryWrapper<House> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq(House.ROOM_NUMBER, houseHoldVo.getRoomNumber()).eq(House.BUILDING_NUMBER, houseHoldVo.getBuildingNumber());
        House house = houseMapper.selectOne(queryWrapper1);
        Assert.notNull(house, "该房间不存在");
        householdInfoMapper.insert(HouseHoldVo.convert(houseHoldVo).setUserId(user.getId()));
        houseUserMapper.insert(new HouseUser().setHouseId(house.getId()).setUserId(user.getId()));
        return true;
    }
}
