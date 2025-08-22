package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 { // JPA 사용하는 버전
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    // 아래 있는 함수가 시작될 때 start transaction;을 해준다 (트랜잭션을 시작!)
    // 함수가 예외없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request){
        // save된 User 반환
        userRepository.save(new User(request.getName(), request.getAge())); //save 메소드에 객체를 넣어주면 INSERT SQL이 자동으로 날라간다
        // u.getId();
        // throw new IllegalArgumentException();
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(user -> new UserResponse(user.getId(), user.getName(), user.getAge())).collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        //select * from user where id=?;
        //결과는 Optional<User>
        User user=userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new); // 유저가 없다면 예외가 나간다
        
        user.updateName(request.getName()); //객체를 변경
        userRepository.save(user); //바뀐 user 기반으로 update 쿼리 날라감
    }

    @Transactional
    public void deleteUser(String name) {
        // select * from user where name = ?

        User user=userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);

//바꾸기 전        User user= userRepository.findByName(name);
//        if (user==null) {
//            throw new IllegalArgumentException();
//        }

        userRepository.delete(user);


    }
}
