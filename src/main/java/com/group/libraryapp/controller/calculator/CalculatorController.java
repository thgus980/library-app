package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.*;

@RestController // API 개발 시. 해당 클래스를 API 의 진입 지점으로 만들어주는 것. 주어진 class를 controller(API의 입구)로 등록한다
public class CalculatorController {

    @GetMapping("/add") // GET /add
    public int addTwoNumbers(CalculatorAddRequest request){ //RequestParam을 사용할 수도 있고 객체를 매개변수로 넣어줄 수도 있다, CalculatorAddRequest -> 데이터를 전달하는 객체 DTO

        return request.getNumber1()+ request.getNumber2();

    }

    @PostMapping("/multiply") // POST /multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request){ // @RequestBody가 있어야 post api에서 정상적으로 http 바디 안에 담긴 json을 이 객체로 변환시킬 수 있음

        return request.getNumber1()*request.getNumber2();

    }


}
