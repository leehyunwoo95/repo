package com.lhw.cop.board.service.impl;

import com.lhw.cmm.DateUtil;
import com.lhw.cop.board.service.BoardService;
import com.lhw.cop.board.service.BoardVO;
import com.lhw.cop.login.service.LoginVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("boardService")
public class BoardServiceImpl  implements BoardService {


    /** boardDAO */
    @Resource(name="boardDAO")
    private BoardDAO boardDAO;

    @Override
    public boolean loginCheck(HttpServletRequest request) throws Exception {
        LoginVO loginVO = new LoginVO();
        loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        if(null != loginVO){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public LoginVO getLoginVO(HttpServletRequest request) throws Exception {
        LoginVO loginVO = new LoginVO();
        loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        if(null != loginVO){
            return loginVO;
        }else{
            return null;
        }
    }

    @Override
    public int selectBoardTotCnt(BoardVO boardVO) throws Exception {
        return boardDAO.selectBoardTotCnt(boardVO);
    }

    @Override
    public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception {
        return boardDAO.selectBoardList(boardVO);
    }

    @Override
    public void insertBoard(HttpServletRequest request, BoardVO boardVO) throws Exception {
        // 사용자 세션 처리
        LoginVO loginVO = new LoginVO();
        loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

        boardVO.setFirstDate(DateUtil.getNowDateTime("yyyyMMddHHmmss"));
        boardVO.setFirstId(loginVO.getUserId());
        boardVO.setFirstName(loginVO.getUserNm());

        boardDAO.insertBoard(boardVO);
    }

    @Override
    public BoardVO selectBoard(BoardVO boardVO) throws Exception {
        return boardDAO.selectBoard(boardVO);
    }

    @Override
    public void updateBoard(HttpServletRequest request, BoardVO boardVO) throws Exception {
        // 사용자 세션 처리
        LoginVO loginVO = new LoginVO();
        loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

        boardVO.setLastDate(DateUtil.getNowDateTime("yyyyMMddHHmmss"));
        boardVO.setLastId(loginVO.getUserId());
        boardVO.setLastName(loginVO.getUserNm());
        boardDAO.updateBoard(boardVO);
    }

    @Override
    public void deleteBoard(HttpServletRequest request, BoardVO boardVO) throws Exception {
        // 사용자 세션 처리
        LoginVO loginVO = new LoginVO();
        loginVO = (LoginVO) request.getSession().getAttribute("loginVO");

        boardVO.setLastDate(DateUtil.getNowDateTime("yyyyMMddHHmmss"));
        boardVO.setLastId(loginVO.getUserId());
        boardVO.setLastName(loginVO.getUserNm());
        boardDAO.deleteBoard(boardVO);
    }
}
