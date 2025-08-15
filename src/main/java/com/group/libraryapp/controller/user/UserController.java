package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // UserController 클래스를 API 진입 지점으로 만들어 줌, UserController 클래스를 스프링 빈(스프링 컨테이너 안에 들어간 클래스)으로 등록
public class UserController { // API의 진입 지점으로써 HTTP Body를 객체로 변환하고 있다 (http, api 관련 작업)

    private final UserServiceV1 userServiceV1;
    
    // 스프링 컨테이너는 필요한 클래스를 연결해준다
    public UserController(UserServiceV1 userService) { // UserController는 jdbcTemplate에 의존하고 있다(jdbTemplate가 없으면 동작하지 않는다), JdbcTemplate도 스프링 빈으로 등록되어 있다(build.gradle에서 dependencies 설정에 의해서 미리 만들어진 코드에 의해 스프링 빈으로 자동 등록되어 있음)
        this.userServiceV1=userService;
    }

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userServiceV1.saveUser(request);
    }

    @GetMapping("/user") // GET /user
    public List<UserResponse> getUsers () {
        return userServiceV1.getUsers();
    }

    @PutMapping("/user") // PUT /user
    public void updateUser(@RequestBody UserUpdateRequest request){ //컨트롤러는 api 관련된 부분만 수행하도록 분리
        userServiceV1.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userServiceV1.deleteUser(name);
    }

}
