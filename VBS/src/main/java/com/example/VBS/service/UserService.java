package com.example.VBS.service;

import com.example.VBS.DTO.RequestDTO.UserRequestDto;
import com.example.VBS.DTO.ResponseDTO.UserResponseDto;

import java.util.List;

public interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);

   public UserResponseDto findUserByEmailId(String emailId) throws RuntimeException;

   public UserResponseDto updateNameGivenMob(String mobNo, String name);

   public List<String> usersNotVaccinated();

   public List<String> usersNotVaccinatedTwice();

   public  List<String> usersFullyVaccinated();

   public  List<String> maleUsersVaccinatedOnce();

   public List<String> femaleUsersFullyVaccinated();
}
