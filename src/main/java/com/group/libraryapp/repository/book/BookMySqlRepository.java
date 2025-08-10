package com.group.libraryapp.repository.book;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary // 스프링 빈이 같은 타입으로 2가지가 있다면 (BookRepository-> BookMemoryRepository, BoolMySqlRepository) 우리가 @Primary를 활용해서 조절 할 수 있다
@Repository
public class BookMySqlRepository implements BookRepository{
    @Override
    public void saveBook(){
        System.out.println("MySqlRepository");
    }
}
