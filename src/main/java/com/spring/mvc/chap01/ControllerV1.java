package com.spring.mvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

// 컨트롤러: 클라이언트의 요청을 받아서 처리 후 응답을 보내주는 역할
@Controller // 빈 등록 : 이 클래스의 객체 생성 관리는 스프링이 처리한다.
public class ControllerV1 {

    // 세부 요청 처리는 메서드를 통해 처리한다.
    @RequestMapping("/")
    public String home() {
        // return문에는 어떤 jsp로 포워딩 할지 경로를 적는다.
        return "index";
    }

    // /food 요청이 오면 food.jsp파일을 열어보세요
    @RequestMapping("/food")
    public String food() {
        return "chap01/food";
    }

    // 요청 파라미터 읽기 (클라이언트가 보낸 정보)

    // 1. HttpServletRequest객체 이용하기
    // ex: /person?name=kim&age=30
    @RequestMapping("/person")
    public String person(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return "";
    }

    // 2. @RequestParam 사용하기
    // ex: /major?stu=park&major=business&grade=3
    @RequestMapping("/major")
    public String major(String stu, @RequestParam("major") String mj, @RequestParam(defaultValue = "1") Integer grade) {
        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);
        return "";
    }

    // 3. DTO(Data Transfer Object)객체 사용하기
    // -> parameter의 양이 많거나 서로 연관되어 있는 경우에 사용
    // ex : /order?orderNum=123&goodsName=구두&amount=3&price=200000 ...
    @RequestMapping("/order")
    public String order(OrderRequestDTO dto) {
        System.out.println("dto = " + dto);
        System.out.println("dto.getOrderNum() = " + dto.getOrderNum());
        System.out.println("dto.getGoodsName() = " + dto.getGoodsName());
        return "";
    }

    // 4. URL경로에 붙어 있는 데이터 읽기
    // ex : /member/kim/107
//    @RequestMapping("/member/{userName}/{userNo}")
    @GetMapping("/member/{userName}/{userNo}")
    public String member(@PathVariable String userName,@PathVariable int userNo) {
        System.out.println("userName = " + userName);
        System.out.println("userNo = " + userNo);
        return "";
    }

    // 5. POST요청 데이터 읽기
    // -> food.jsp에서 보낸 데이털르 읽을 것임.
    // POST요청만 받겠다.
//    @RequestMapping(value = "/food-select", method = RequestMethod.POST)
    @PostMapping("/food-select")
    public String select(String foodName, String category) {
        System.out.println("foodName = " + foodName);
        System.out.println("category = " + category);
        return "index";
    }

}
