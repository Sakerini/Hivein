package com.hivein.userdataservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class ProfileUpdateDTO {
    private String username;
    private String displayName;

}
