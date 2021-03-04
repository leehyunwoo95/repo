package com.lhw.cop.board.service.impl;

import com.lhw.cop.board.service.BoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO {

    public int selectBoardTotCnt(BoardVO boardVO) throws Exception{
        return (Integer)select("boardDAO.selectBoardTotCnt", boardVO);
    }

    public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception{
        return (List<BoardVO>)list("boardDAO.selectBoardList", boardVO);
    }

    public void insertBoard(BoardVO boardVO) throws Exception{
        insert("boardDAO.insertBoard", boardVO);
    }

    public BoardVO selectBoard(BoardVO boardVO) throws Exception{
        return (BoardVO)select("boardDAO.selectBoard", boardVO);
    }

    public void updateBoard(BoardVO boardVO) throws Exception{
        update("boardDAO.updateBoard", boardVO);
    }

    public void deleteBoard(BoardVO boardVO) throws Exception{
        update("boardDAO.deleteBoard", boardVO);
    }
}
