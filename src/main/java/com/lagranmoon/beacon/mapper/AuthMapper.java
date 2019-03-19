package com.lagranmoon.beacon.mapper;

import com.lagranmoon.beacon.model.domain.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Lagranmoon
 */
@Mapper
public interface AuthMapper {

    void saveUser(UserAuth userAuth);

    void updateSessionKey(@Param("openId") String openId
            ,@Param("sessionKey")String sessionKey);

    void getSessionKey(@Param("uid")String id);

}
