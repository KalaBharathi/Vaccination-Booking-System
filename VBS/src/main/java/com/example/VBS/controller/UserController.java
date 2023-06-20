package com.example.VBS.controller;

import com.example.VBS.DTO.RequestDTO.UserRequestDto;
import com.example.VBS.DTO.ResponseDTO.UserResponseDto;
import com.example.VBS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto =userService.addUser(userRequestDto);
        return new ResponseEntity(userResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/findByEmailId")
    public ResponseEntity findUserByEmailId(@RequestParam String emailId){
        try {
            UserResponseDto userResponseDto = userService.findUserByEmailId(emailId);
            return new ResponseEntity(userResponseDto, HttpStatus.OK);
        }
        catch (RuntimeException e){
            String error="No user found";
            return new ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/updateName")
    public ResponseEntity updateNameGivenMob(@RequestParam String mobNo,@RequestParam String name){
           UserResponseDto userResponseDto=userService.updateNameGivenMob(mobNo,name);
            return new ResponseEntity(userResponseDto, HttpStatus.OK);
    }
    @GetMapping("/notVaccinated")
    public ResponseEntity usersNotVaccinated(){
        List<String> users=userService.usersNotVaccinated();
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping("/notVaccinatedTwice")
    public ResponseEntity usersNotVaccinatedTwice(){
        List<String> users=userService.usersNotVaccinatedTwice();
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping("/fullyVaccinated")
    public ResponseEntity usersFullyVaccinated(){
        List<String> users=userService.usersFullyVaccinated();
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping("/maleUsersVaccinatedOnce")
    public ResponseEntity maleUsersVaccinatedOnce(){
        List<String> users=userService.maleUsersVaccinatedOnce();
        return new ResponseEntity(users, HttpStatus.OK);
    }
    @GetMapping("/femaleUsersFullyVaccinated")
    public ResponseEntity femaleUsersFullyVaccinated(){
        List<String> users=userService.femaleUsersFullyVaccinated();
        return new ResponseEntity(users, HttpStatus.OK);
    }

}
