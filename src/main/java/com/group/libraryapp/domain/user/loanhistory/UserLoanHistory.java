package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;

    @ManyToOne // 대출 기록은 여러 개이고, 이 대출 기록을 소유하고 있는 사용자는 한명 (내가 N, 너가 1)
    //private long userId;
    private User user;

    private String bookName;

    private boolean isReturn;
    
    protected UserLoanHistory(){ // JPA는 기본 생성자가 하나 필요함
        
    }

    public UserLoanHistory(User user, String bookName){
        this.user=user;
        this.bookName=bookName;
        this.isReturn=false;
    }

    public void doReturn() { // 대출 기록에 대한 Return
        this.isReturn=true;
    }

    public String getBookName(){
        return this.bookName;
    }

}
