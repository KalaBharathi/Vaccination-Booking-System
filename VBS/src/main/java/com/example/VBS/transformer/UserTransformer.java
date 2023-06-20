package com.example.VBS.transformer;

import com.example.VBS.DTO.RequestDTO.UserRequestDto;
import com.example.VBS.DTO.ResponseDTO.UserResponseDto;
import com.example.VBS.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass

public class UserTransformer {
    public static User UserRequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .gender(userRequestDto.getGender())
                .mobNo(userRequestDto.getMobNo())
                .age(userRequestDto.getAge())
                .emailId(userRequestDto.getEmailId())
                .build();
    }
    public static UserResponseDto userToUserResponseDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("Congrats! You have Registered")
                .build();

    }
}
