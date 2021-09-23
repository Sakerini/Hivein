package com.hivein.userdataservice.controller;

import com.hivein.userdataservice.model.entity.Address;
import com.hivein.userdataservice.model.entity.Profile;
import com.hivein.userdataservice.model.entity.User;
import com.hivein.userdataservice.model.response.BaseResponse;
import com.hivein.userdataservice.model.response.ErrorResponse;
import com.hivein.userdataservice.model.dto.UserSummaryDTO;
import com.hivein.userdataservice.service.UserService;
import com.hivein.userdataservice.util.StatusCodes;
import com.hivein.userdataservice.util.UserDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserDataController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/test")
    public ResponseEntity<?> testMethod() {
        return ResponseEntity.ok(new BaseResponse(StatusCodes.OK.getCode(), "HELLO BEE"));
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<?> getCurrentUser(@PathVariable(value = "username") String username) {
        Optional<User> user = userService.findByUsername(username);
        if (!user.isPresent()) {
            return new ResponseEntity<>(new ErrorResponse(
                    StatusCodes.BAD_REQUEST.getCode(),
                    "No such user"),
                    HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(UserSummaryDTO
                .builder()
                .id(user.get().getId())
                .username(user.get().getUsername())
                .name(user.get().getUserProfile().getDisplayName())
                .profilePicture(user.get().getUserProfile().getProfilePictureUrl())
                .birthday(user.get().getUserProfile().getBirthday())
                .city(user.get().getUserProfile().getAddress().getCity())
                .country(user.get().getUserProfile().getAddress().getCountry())
                .streetName(user.get().getUserProfile().getAddress().getStreetName())
                .firstName(user.get().getUserProfile().getFirstName())
                .lastName(user.get().getUserProfile().getLastName())
                .zipCode(user.get().getUserProfile().getAddress().getZipCode())
                .build());
    }

    @GetMapping("/find/summaries/{username}")
    public ResponseEntity<?> findAllUserSummaries(@PathVariable(value = "username") String username) {
        log.info("retrieving all users summaries");

        return ResponseEntity.ok(userService
                .findAllUsers()
                .stream()
                .filter(user -> !user.getUsername().equals(username))
                .map(UserDataUtil::convertTo));
    }

    @PutMapping("/update-summary")
    public ResponseEntity<?> updateProfile(@RequestBody UserSummaryDTO summaryDTO) {

        Optional<User> userOptional = userService.findByUsername(summaryDTO.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Profile profile = user.getUserProfile();
            Address address = profile.getAddress();
            address.setCity(summaryDTO.getCity());
            address.setCountry(summaryDTO.getCountry());
            address.setZipCode(summaryDTO.getZipCode());
            address.setStreetName(summaryDTO.getStreetName());
            profile.setAddress(address);

            profile.setBirthday(summaryDTO.getBirthday());
            profile.setProfilePictureUrl(summaryDTO.getProfilePicture());
            profile.setDisplayName(summaryDTO.getName());
            profile.setFirstName(summaryDTO.getFirstName());
            profile.setLastName(summaryDTO.getLastName());
            user.setUserProfile(profile);

            userService.updateUser(user);
            return ResponseEntity.ok(new BaseResponse(StatusCodes.OK.getCode(), "User updated"));
        }

        return new ResponseEntity<>(new ErrorResponse(
                StatusCodes.NOT_FOUND.getCode(),
                "Username not found"),
                HttpStatus.NOT_FOUND);
    }
}
