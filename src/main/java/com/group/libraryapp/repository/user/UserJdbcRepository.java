package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository // 자동으로 스프링 빈 등록
public class UserJdbcRepository { // SQL을 사용해 실제 DB와의 통신을 담당한다

    private final JdbcTemplate jdbcTemplate; //jdbc 템플릿을 이용해서 db에 접근 가능한 것
    // @Repository를 통해서 UserRepository도 스프링 빈으로 등록되었으니, 바로 JdbcTemplate 사용 가능하다

    public UserJdbcRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public boolean isUserNotExist(long id) {
        String readSql="SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty(); // select 결과가 하나라도 있다면 0이 있는 리스트 반환
    }

    public void updateUserName(String name, long id){
        String sql="UPDATE user SET name=? where id=?";
        jdbcTemplate.update(sql, name, id);
    }

    public boolean isUserNotExist(String name){
        String readSql="SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty(); // select 결과가 하나라도 있다면 0이 있는 리스트 반환
    }

    public void deleteUser(String name){
        String sql="DELETE FROM USER WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }

    public  void saveUser(String name, int age){
        String sql= "INSERT INTO user (name, age) VALUES (?, ?)"; // 이름과 나이는 고정된 sql이 아니기 때문에 유동적으로 값을 넣어줄 수 있도록
        jdbcTemplate.update(sql, name, age); // String sql의 물음표에 각각 request.getName(), request.getAge() 넣어짐
    }

    public List<UserResponse> getUsers(){
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

}
