package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.dao.*;
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
    public List<HouseUserVo> searchUser(String name, Integer buildingNum, Integer roomNum) {
        QueryWrapper<HouseholdInfo> householdInfoQueryWrapper = new QueryWrapper<>();
        QueryWrapper<House> houseQueryWrapper = new QueryWrapper<>();
        if(buildingNum != null){
            houseQueryWrapper.eq(House.BUILDING_NUMBER, buildingNum);
        }
        if(roomNum != null){
            houseQueryWrapper.eq(House.ROOM_NUMBER, roomNum);
        }
        List<Integer> houseIds = houseMapper.selectList(houseQueryWrapper).stream().map(House::getId).collect(Collectors.toList());

        if(!houseIds.isEmpty()) {
            QueryWrapper<HouseUser> houseUserQueryWrapper = new QueryWrapper<>();
            houseUserQueryWrapper.in(HouseUser.HOUSE_ID, houseIds);
            List<Long> userIds = houseUserMapper.selectList(houseUserQueryWrapper).stream().map(HouseUser::getUserId).collect(Collectors.toList());
            if(!userIds.isEmpty()){
                householdInfoQueryWrapper.in(HouseholdInfo.USER_ID, userIds);
            }
        }else {
            return null;
        }

        if(name != null){
            householdInfoQueryWrapper.like(HouseholdInfo.NAME, name);
        }

        List<HouseholdInfo> householdInfos = householdInfoMapper.selectList(householdInfoQueryWrapper);

        QueryWrapper<Car> queryWrapper;
        List<HouseUserVo> houseUserVos = new LinkedList<>();
        for(HouseholdInfo householdInfo : householdInfos){
            HouseUserVo houseUserVo = new HouseUserVo();

            House house = houseMapper.selectHouseByUserId(householdInfo.getUserId());

            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", householdInfo.getUserId());

            Car car = carMapper.selectOne(queryWrapper);

            if(car != null){
                houseUserVo.setPlateNumber(car.getPlateNumber());
            }
            houseUserVo.setName(householdInfo.getName())
                    .setUserId(householdInfo.getUserId())
                    .setBuildingNumber(house.getBuildingNumber())
                    .setRoomNumber(house.getRoomNumber());

            houseUserVos.add(houseUserVo);
        }
        return houseUserVos;
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
