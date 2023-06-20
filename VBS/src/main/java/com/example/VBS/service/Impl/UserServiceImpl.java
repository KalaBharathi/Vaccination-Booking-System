package com.example.VBS.service.Impl;

import com.example.VBS.DTO.RequestDTO.UserRequestDto;
import com.example.VBS.DTO.ResponseDTO.UserResponseDto;
import com.example.VBS.Enum.Gender;
import com.example.VBS.model.User;
import com.example.VBS.repository.UserRepository;
import com.example.VBS.service.UserService;
import com.example.VBS.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        /*User user=new User();
        user.setName(userRequestDto.getName());
        user.setGender(userRequestDto.getGender());
        user.setMobNo(userRequestDto.getMobNo());
        user.setAge(userRequestDto.getAge());
        user.setEmailId(userRequestDto.getEmailId());*/
        User user=UserTransformer.UserRequestDtoToUser(userRequestDto);

        User savedUser=userRepository.save(user);

        /*UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setMessage("Congrats! You have Registered");*/
        UserResponseDto userResponseDto= UserTransformer.userToUserResponseDto(savedUser);
        return userResponseDto;
    }

    @Override
    public UserResponseDto findUserByEmailId (String emailId) throws RuntimeException {
        Optional<User> userOptional=userRepository.findByEmailId(emailId);
        UserResponseDto userResponseDto = new UserResponseDto();
        if(userOptional.isPresent()) {

            User user = userOptional.get();

            userResponseDto.setName(user.getName());
            userResponseDto.setMessage("This is the user name for given email id");
        }
        else throw new RuntimeException();

        return userResponseDto;
    }

    @Override
    public UserResponseDto updateNameGivenMob(String mobNo, String name) {
        Optional<User> userOptional=userRepository.findByMobNo(mobNo);
        UserResponseDto userResponseDto = new UserResponseDto();
        User user = userOptional.get();
        user.setName(name);
        userRepository.save(user);
        userResponseDto.setName(user.getName());
        userResponseDto.setMessage("Username updated successfully");
        return userResponseDto;
    }

    @Override
    public List<String> usersNotVaccinated() {
        List<String> users=new ArrayList<>();
        List<User> allUsers=userRepository.findAll();
        for(User user:allUsers){
            if(!user.isDose1Taken()){
                users.add(user.getName());
            }
        }
        return users;
    }

    @Override
    public List<String> usersNotVaccinatedTwice() {
        List<String> users=new ArrayList<>();
        List<User> allUsers=userRepository.findAll();
        for(User user:allUsers){
            if(!user.isDose2Taken() && user.isDose1Taken()){
                users.add(user.getName());
            }
        }
        return users;
    }

    @Override
    public List<String> usersFullyVaccinated() {
        List<String> users=new ArrayList<>();
        List<User> allUsers=userRepository.findAll();
        for(User user:allUsers){
            if(user.isDose2Taken() && user.isDose1Taken()){
                users.add(user.getName());
            }
        }
        return users;
    }

    @Override
    public List<String> maleUsersVaccinatedOnce() {
        List<String> users=new ArrayList<>();
        List<User> allUsers=userRepository.findAll();
        for(User user:allUsers){
            if(user.getGender()== Gender.MALE && user.isDose1Taken()){
                users.add(user.getName());
            }
        }
        return users;
    }

    @Override
    public List<String> femaleUsersFullyVaccinated() {
        List<String> users=new ArrayList<>();
        List<User> allUsers=userRepository.findAll();
        for(User user:allUsers){
            if(user.getGender()== Gender.FEMALE && user.isDose1Taken() && user.isDose2Taken()){
                users.add(user.getName());
            }
        }
        return users;
    }
}
