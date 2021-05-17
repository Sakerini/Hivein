package com.hivein.userdataservice.util;

import com.hivein.userdataservice.model.dto.UserSummaryDTO;
import com.hivein.userdataservice.model.entity.User;

public class UserDataUtil {
    public static UserSummaryDTO convertTo(User user) {
        return UserSummaryDTO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getUserProfile().getDisplayName())
                .profilePicture(user.getUserProfile().getProfilePictureUrl())
                .build();
    }
}
