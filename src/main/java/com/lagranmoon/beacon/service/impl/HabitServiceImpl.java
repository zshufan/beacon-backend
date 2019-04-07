package com.lagranmoon.beacon.service.impl;

import com.lagranmoon.beacon.exception.ResourceNotFoundException;
import com.lagranmoon.beacon.mapper.HabitMapper;
import com.lagranmoon.beacon.model.HabitDetailDto;
import com.lagranmoon.beacon.model.HabitDto;
import com.lagranmoon.beacon.model.HabitRequest;
import com.lagranmoon.beacon.model.domain.Habit;
import com.lagranmoon.beacon.service.HabitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lagranmoon
 */
@Service
public class HabitServiceImpl implements HabitService {

    @Resource
    private HabitMapper habitMapper;

    @Override
    public List<HabitDto> getHabitListByOpenId(String openId) {

        List<Habit> habitList = habitMapper.getHabitByOpenId(openId);
        return habitList.stream()
                .map(habit -> {
                    HabitDto habitDto = HabitDto.builder()
                            .id(habit.getId())
                            .title(habit.getTitle())
                            .build();
                    List<String> tagList = habitMapper.getHabitTagsById(habit.getId());
                    habitDto.setTagList(tagList);
                    habitDto.setCount(habitMapper.getCountById(habit.getId()));
                    return habitDto;
                }).collect(Collectors.toList());
    }

    @Override
    public HabitDetailDto getHabitDetailById(Long id) {

        Habit habit = habitMapper.getHabitById(id);

        if (Objects.nonNull(habit)){
            return HabitDetailDto.builder()
                    .content(habit.getContent())
                    .frequency(habit.getFrequency())
                    .duration(habit.getDuration())
                    .createTime(habit.getCreateTime())
                    .build();
        }else {
            throw new ResourceNotFoundException(String.format("Habit %d doesn't exists",id));
        }
    }

    @Override
    public void saveHabit(HabitRequest habitRequest) {

    }
}
