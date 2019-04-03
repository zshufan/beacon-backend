package com.lagranmoon.beacon.model;

import lombok.Data;

import java.util.List;

/**
 * @author Lagranmoon
 */
@Data
public class HabitDto {

    private Long id;
    private String title;
    private Integer times;
    private List<String> typeList;


}
