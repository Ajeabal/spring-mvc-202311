package com.spring.mvc.chap05.dto.response;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap05.entity.Board;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @ToString @EqualsAndHashCode
@RequiredArgsConstructor
public class BoardResponseDTO {

    private final int boardNo;
    private final String shortTitle; // 5글자 이상이면 잘라내기
    private final String shortContent; // 30자 이상이면 잘라내기
    private final String date; // 날짜패턴 yyyy-MM-dd HH:mm
    private final int viewCount;
    private final String account;

    public BoardResponseDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.shortTitle = makeShortTitle(board.getTitle());
        this.shortContent = makeShortContent(board.getContent());
        this.date = makePrettierDateString(board.getRegDateTime());
        this.viewCount = board.getViewCount();
        this.account = board.getAccount();
    }

    static String makePrettierDateString(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

    private String makeShortContent(String originContent) {
        return sliceString(originContent, 30);
    }


    private String makeShortTitle(String originTitle) {
        return sliceString(originTitle, 7);
    }

    /**
     *
     * @param targetString : 줄이고 싶은 원본 문자열
     * @param wishLength : 짜르고 싶은 글자 수
     * @return : wishLength보다 targetString이 길면
     *              wishLength만큼 짤라서 뒤에 ... 붙여서 리턴
     */
    private static String sliceString(String targetString, int wishLength) {
        return (targetString.length() > wishLength)
                ? targetString.substring(0, wishLength) + "..."
                : targetString
                ;
    }

}