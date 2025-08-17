package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속한 것만으로도 자동으로 빈에 등록됨
public interface UserRepository extends JpaRepository<User, Long> { // User의 id가 Long 타입이기 때문에
    User findByName(String name); //find는 1개의 데이터만 가져온다.
    // By 뒤에 붙는 필드 이름으로 select 쿼리의 where 문이 작성된다.
    // select * from user where name = ?

}
