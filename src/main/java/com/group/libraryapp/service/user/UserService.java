package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 자동으로 스프링 빈 등록
public class UserService { // 현재 유저가 있는지, 없는지 등을 확인하고 예외 처리를 해준다 (분기 처리, 로직 담당)

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;

    }

    public void saveUser(UserCreateRequest request) {
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request) { // 컨트롤러가 전달해준 객체를 그냥 받기만 할 것이기 때문에 @RequestBody UserUpdateRequest request 와 같이 어노테이션을 붙이지 않는다
        if (userRepository.isUserNotExist(request.getId())){
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }
        userRepository.deleteUser(name);
    }

}
