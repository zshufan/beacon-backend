package com.lagranmoon.beacon.service;

import com.lagranmoon.beacon.model.HabitDetailDto;
import com.lagranmoon.beacon.model.HabitDto;
import com.lagranmoon.beacon.model.HabitRequest;

import java.util.List;

/**
 * @author Lagranmoon
 */
public interface HabitService {

    List<HabitDto> getHabitListByOpenId(String openId);

    HabitDetailDto getHabitDetailById(Long id);

    void saveHabit(HabitRequest habitRequest);

}
