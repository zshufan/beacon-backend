package com.lagranmoon.beacon.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Lagranmoon
 */
@Data
@Builder
public class HabitDetailDto {

    private String content;
    private Integer frequency;
    private Integer duration;
    private Date createTime;

}
