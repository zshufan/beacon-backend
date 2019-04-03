package com.lagranmoon.beacon.service.impl;

import com.lagranmoon.beacon.mapper.HabitMapper;
import com.lagranmoon.beacon.model.HabitDetailDto;
import com.lagranmoon.beacon.model.HabitDto;
import com.lagranmoon.beacon.model.HabitRequest;
import com.lagranmoon.beacon.service.HabitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lagranmoon
 */
@Service
public class HabitMapperImpl implements HabitService {

    @Resource
    private HabitMapper habitMapper;

    @Override
    public List<HabitDto> getHabitListByOpenId(String openId) {



        return null;
    }

    @Override
    public HabitDetailDto getHabitDetailById(Long id) {
        return null;
    }

    @Override
    public void saveHabit(HabitRequest habitRequest) {

    }
}
