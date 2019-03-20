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

    void updateSessionKey(@Param("openId") String openId,
                          @Param("sessionKey") String sessionKey);

    String getSessionKeyByUid(@Param("id") Long id);

    String getOpenIdByid(@Param(("id")) Long id);

    String getUserNameByOpenId(@Param("openId") String openId);

}
