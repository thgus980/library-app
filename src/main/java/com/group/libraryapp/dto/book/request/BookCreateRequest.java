package com.group.libraryapp.dto.book.request;

public class BookCreateRequest { // http body로 들어온 JSON이 매핑 됨 -> DTO
    private String name;

    public String getName() {
        return name;
    }
}
