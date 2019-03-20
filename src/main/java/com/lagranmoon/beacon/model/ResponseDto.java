package com.lagranmoon.beacon.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Lagranmoon
 */
@Data
@Builder
public class ResponseDto<T> {
    T data;
    String msg;
    Integer errCode;
}
