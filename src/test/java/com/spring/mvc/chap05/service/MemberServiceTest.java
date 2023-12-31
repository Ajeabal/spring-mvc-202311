package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService service;

    @Test
    @DisplayName("회원정보를 전달하면 비밀번호가 암호화되어 DB에 저장된다.")
    void joinTest() {
        //given
        SignUpRequestDTO dto = SignUpRequestDTO.builder()
                .account("kitty")
                .password("kkk1234!")
                .name("헬로키티")
                .email("sanrio@gmail.com")
                .build();
        //when
        boolean flag = service.join(dto, savePath);
        //then
        assertTrue(flag);
    }

//    @Test
//    @DisplayName("계정명이 kitty인 회원의 로그인 시도 결과를 상황별로 검증한다")
//    void loginTEst() {
//        //given
//        LoginRequestDTO dto = LoginRequestDTO.builder()
//                .account("kitty")
//                .password("kkk1234!")
//                .build();
//        LoginRequestDTO dto1 = LoginRequestDTO.builder()
//                .account("hello")
//                .password("kkk1234!")
//                .build();
//        LoginRequestDTO dto2 = LoginRequestDTO.builder()
//                .account("kitty")
//                .password("kkk1234")
//                .build();
//        //when
//        LoginResult result = service.authenticate(dto);
//        LoginResult result1 = service.authenticate(dto1);
//        LoginResult result2 = service.authenticate(dto2);
//        //then
//        assertEquals(SUCCESS, result);
//        assertEquals(NO_ACC, result1);
//        assertEquals(NO_PW, result2);
//    }
}