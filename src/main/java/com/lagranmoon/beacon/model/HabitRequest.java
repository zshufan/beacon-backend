package com.lagranmoon.beacon.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * @author Lagranmoon
 */
@Data
public class HabitRequest {

    @NotEmpty(message = "title can not be empty")
    private String title;

    private String content;

    @Positive(message = "frequency must be positive")
    private Integer frequency;

    @Positive(message = "duration must be positive")
    private Integer duration;

    private List<String> tagList;
}
