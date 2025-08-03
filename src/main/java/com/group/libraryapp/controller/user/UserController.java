package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate; //jdbc 템플릿을 이용해서 db에 접근 가능한 것

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql= "INSERT INTO user (name, age) VALUES (?, ?)"; // 이름과 나이는 고정된 sql이 아니기 때문에 유동적으로 값을 넣어줄 수 있도록
        jdbcTemplate.update(sql, request.getName(), request.getAge()); // String sql의 물음표에 각각 request.getName(), request.getAge() 넣어짐
    }

    @GetMapping("/user") // GET /user
    public List<UserResponse> getUsers () {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() { //select * from user(String sql)를 수행하여 나온 결과들을 우리가 작성한 로직에 따라서 UserResponse로 바꿔주는 작업
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age= rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });
    }

    @PutMapping("/user") // PUT /user
    public void updateUser(@RequestBody UserUpdateRequest request){
        String readSql="SELECT * FROM user WHERE id = ?";
        boolean isUserNotExist=jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty(); // select 결과가 하나라도 있다면 0이 있는 리스트 반환
        if (isUserNotExist){
            throw new IllegalArgumentException();
        }

        String sql="UPDATE user SET name=? where id=?";
        jdbcTemplate.update(sql, request.getName(), request.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        String readSql="SELECT * FROM user WHERE name = ?";
        boolean isUserNotExist=jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty(); // select 결과가 하나라도 있다면 0이 있는 리스트 반환
        if (isUserNotExist){
            throw new IllegalArgumentException();
        }

        String sql="DELETE FROM USER WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

}
