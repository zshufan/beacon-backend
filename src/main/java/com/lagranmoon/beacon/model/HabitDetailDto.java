package com.lagranmoon.beacon.model;

import lombok.Data;

import java.util.Date;

/**
 * @author Lagranmoon
 */
@Data
public class HabitDetailDto {

    private String content;
    private String frequency;
    private String duration;
    private Date createTime;

}
