package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.common.Search;
import com.spring.mvc.chap05.dto.response.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.BoardResponseDTO;
import com.spring.mvc.chap05.dto.request.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.util.LoginUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

//    private final BoardRepository boardRepository;

    private final BoardMapper boardRepository;
    // 목록 조회 중간처리
    public List<BoardResponseDTO> getList(Search page) {
        return boardRepository.findAll(page)
                .stream()
                .map(BoardResponseDTO::new)
                .collect(Collectors.toList())
                ;
    }

    // 글 쓰기 중간처리
    public void register(HttpSession session, BoardWriteRequestDTO dto) {
        // dto 를 엔터티로 변환
        Board board = new Board(dto);
        board.setAccount(LoginUtils.getCurrentLoginMemberAccount(session));
        boardRepository.save(board);
    }


    public void delete(int boardNo) {
        boardRepository.delete(boardNo);
    }

    public BoardDetailResponseDTO getDetail(int bno) {
        Board board = boardRepository.findOne(bno);

        // 조회수 상승처리
//        board.upViewCount();
        boardRepository.updateViewCount(bno);

        return new BoardDetailResponseDTO(board);
    }

    public int getTotalCount(Search search) {
        return boardRepository.getTotalCount(search);
    }
}
