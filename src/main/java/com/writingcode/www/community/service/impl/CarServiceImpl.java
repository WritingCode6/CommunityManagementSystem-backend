package com.writingcode.www.community.service.impl;

import com.writingcode.www.community.entity.Car;
import com.writingcode.www.community.dao.CarMapper;
import com.writingcode.www.community.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chavy
 * @date  2020/05/02
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {

}
