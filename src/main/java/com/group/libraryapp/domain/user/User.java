package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity // 스프링이 User 객체와 user 테이블을 같은 것으로 바라본다
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment 가 걸려 있기 때문에 붙여줌
    private Long id=null;

    @Column(nullable = false, length = 20, name="name") // name varchar(20)
    private String name;
    private Integer age; // age int와 완전하게 동일하기 때문에 @Column 생략 가능

    // @Entity는 생성자가 무조건 필요하다, JPA를 사용하기 위해서는 기본 생성자가 꼭 필요하다
    protected User(){}

    public User(String name, Integer age) {
        if(name== null|| name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다", name));
        }
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name){
        this.name=name;
    }
}
