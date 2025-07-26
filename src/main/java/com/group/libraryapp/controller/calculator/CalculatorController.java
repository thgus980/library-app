package com.group.libraryapp.controller.calculator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // API 개발 시. 해당 클래스를 API 의 진입 지점으로 만들어주는 것. 주어진 class를 controller(API의 입구)로 등록한다
public class CalculatorController {

    @GetMapping("/add") // GET /add
    public int addTwoNumbers(@RequestParam int number1, @RequestParam int number2){ // 쿼리를 통해서 number1과 number2를 받기로 했기 때문에 해당 함수에 연결해줄 때는 @RequestParam(주어지는 쿼리를 함수 파라미터에 넣는다)
        return number1+number2;
    }


}
