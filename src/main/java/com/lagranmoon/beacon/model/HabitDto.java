package com.lagranmoon.beacon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Lagranmoon
 */
@Data
@Builder
public class HabitDto {

    private Long id;
    private String title;
    private Integer count;
    private List<String> tagList;


}
