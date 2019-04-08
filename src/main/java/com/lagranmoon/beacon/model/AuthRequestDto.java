package com.lagranmoon.beacon.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.redis.core.ValueOperations;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lagranmoon
 */
@Data
@ApiModel
public class AuthRequestDto {

    @ApiModelProperty("微信登录request code")
    @NotEmpty(message = "request code 不能为空")
    private String code;

    @ApiModelProperty("微信昵称")
    @NotEmpty(message = "用户名不能为空")
    private String nickName;

}
