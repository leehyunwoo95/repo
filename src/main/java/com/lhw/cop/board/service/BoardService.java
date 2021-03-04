package com.lhw.cop.board.service;

import com.lhw.cop.login.service.LoginVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BoardService {
    int selectBoardTotCnt(BoardVO boardVO)throws Exception;

    List<BoardVO> selectBoardList(BoardVO boardVO)throws Exception;

    void insertBoard(HttpServletRequest request, BoardVO boardVO)throws Exception;

    BoardVO selectBoard(BoardVO boardVO)throws Exception;

    void updateBoard(HttpServletRequest request, BoardVO boardVO)throws Exception;

    boolean loginCheck(HttpServletRequest request)throws Exception;

    LoginVO getLoginVO(HttpServletRequest request)throws Exception;

    void deleteBoard(HttpServletRequest request, BoardVO boardVO)throws Exception;
}
