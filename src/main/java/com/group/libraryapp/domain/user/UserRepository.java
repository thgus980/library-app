package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속한 것만으로도 자동으로 빈에 등록됨
public interface UserRepository extends JpaRepository<User, Long> { // User의 id가 Long 타입이기 때문에
}
