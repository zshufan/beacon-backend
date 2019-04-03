package com.lagranmoon.beacon.model.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author Lagranmoon
 */
@Data
public class Habit {

    private Long id;
    private String openId;
    private String title;
    private String content;
    private Integer frequency;
    private Integer duration;
    private Date createTime;


}
