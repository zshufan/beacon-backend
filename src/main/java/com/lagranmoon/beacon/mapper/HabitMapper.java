package com.lagranmoon.beacon.mapper;

import com.lagranmoon.beacon.model.domain.Habit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Lagranmoon
 */
@Mapper
public interface HabitMapper {


    /**
     * 根据用户的OpenID返回Habit
     * @param openId 用户的OpenID
     * @return 返回的Habit中包含id和title
     */
    List<Habit> getHabitByOpenId(String openId);

    Habit getHabitById(Long id);

    /**
     * 根据Habit ID获取其所有的Tag
     * @param habitId Habit Id
     * @return Tag名称列表
     */
    List<String> getHabitTagsById(Long habitId);

    /**
     * 根据Habit ID获取当前Habit完成次数
     * @param habitId Habit ID
     * @return Habit完成次数
     */
    Integer getCountById(Long habitId);

    void insertHabit(Habit habit);

    void insertHabitTags(@Param("id") Long habitId
            ,@Param("tagList") List<Long> tagList);

    List<Long> getTagIdsByName(List<String> tagList);

    List<String> getTagNameById(Long habitId);



}
