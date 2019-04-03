package com.lagranmoon.beacon.mapper;

import com.lagranmoon.beacon.model.domain.Habit;
import com.lagranmoon.beacon.model.domain.HabitDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Lagranmoon
 */
@Mapper
public interface HabitMapper {


    List<Habit> getHabitByOpenId(String openId);

    List<String> getHabitTagsById(Long habitId);

    void getFrequencyById(Long habitId);

    void insertHabit(Habit habit);

    void insertHabitTags(@Param("id") Long habitId
            ,@Param("tagList") List<Long> tagList);

    List<Long> getTagIdsByName(List<String> tagList);



}
