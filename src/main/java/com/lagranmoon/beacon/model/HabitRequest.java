package com.lagranmoon.beacon.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lagranmoon
 */
@Data
public class HabitRequest {

    @NotEmpty
    private String title;

}
