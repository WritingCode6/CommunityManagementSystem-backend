package com.writingcode.www.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.writingcode.www.community.dao.CarMapper;
import com.writingcode.www.community.dao.HouseMapper;
import com.writingcode.www.community.dao.HouseUserMapper;
import com.writingcode.www.community.entity.po.Car;
import com.writingcode.www.community.entity.po.House;
import com.writingcode.www.community.entity.po.HouseUser;
import com.writingcode.www.community.entity.po.HouseholdInfo;
import com.writingcode.www.community.dao.HouseholdInfoMapper;
import com.writingcode.www.community.entity.vo.HouseUserVo;
import com.writingcode.www.community.service.IHouseholdInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

            houseUserVo.setName(householdInfo.getName())
                    .setUserId(householdInfo.getUserId())
                    .setBuildingNumber(house.getBuildingNumber())
                    .setRoomNumber(house.getRoomNumber())
                    .setPlateNumber(car.getPlateNumber());

            houseUserVos.add(houseUserVo);
        }
        return houseUserVos;
    }
}
