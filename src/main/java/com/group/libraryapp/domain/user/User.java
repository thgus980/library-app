package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 스프링이 User 객체와 user 테이블을 같은 것으로 바라본다
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment 가 걸려 있기 때문에 붙여줌
    private Long id=null;

    @Column(nullable = false, length = 20, name="name") // name varchar(20)
    private String name;
    private Integer age; // age int와 완전하게 동일하기 때문에 @Column 생략 가능


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // User 객체와 1:N 관계, 연관관계 주인 설정
    // cascade? user가 삭제되면 해당 user와 연결된 정보도 같이 삭제 (User가 삭제될 때 User와 연결된 UserLoanHistory까지 한 번에 사라지게 된다
    // orphanRemoval? 객체간의 관계가 끊어진 데이터를 자동으로 제거하는 옵션
    private List<UserLoanHistory> userLoanHistories=new ArrayList<>();

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

    public void loanBook(String bookName) {
        this.userLoanHistories.add(new UserLoanHistory(this,bookName)); // user와 userLoanHistory 협력 -> 도메인 계층에 비즈니스 로직이 들어갔다
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history->history.getBookName().equals(bookName))
                .findFirst() // Optional
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}
