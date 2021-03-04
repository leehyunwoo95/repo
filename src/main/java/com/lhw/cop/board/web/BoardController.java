package com.lhw.cop.board.web;

import com.lhw.cop.board.service.BoardService;
import com.lhw.cop.board.service.BoardVO;
import com.lhw.cop.login.service.LoginVO;
import com.lhw.tag.pagination.LhwPaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {

    /** boardService */
    @Resource(name="boardService")
    private BoardService boardService;

    /**
     * 게시물의 목록을 조회한다.
     * @param boardVO
     * @param request
     * @param model
     * @return list.jsp
     * @throws Exception
     */
    @RequestMapping(value="/selectBoardList.do")
    public String selectBoardList(@ModelAttribute("boardVO") BoardVO boardVO,
                                  HttpServletRequest request, ModelMap model) throws Exception {

        // 게시물 카운트
        int totCnt = boardService.selectBoardTotCnt(boardVO);
        // 페이징 정보
        LhwPaginationInfo lhwPaginationInfo = boardVO.getLhwPaginationInfo(totCnt);
        // 페이징 정보 등록
        model.addAttribute("paginationInfo", lhwPaginationInfo);

        // 게시물 목록
        List<BoardVO> boardList = boardService.selectBoardList(boardVO);
        model.addAttribute("boardList", boardList);

        return "lhw/cop/board/list";

    }

    /**
     * 게시물 등록 페이지로 이동한다.
     * @param boardVO
     * @param request
     * @param model
     * @throws Exception
     */
    @RequestMapping(value="/addBoardView.do")
    public String addBoardView(@ModelAttribute("boardVO") BoardVO boardVO,
                                  HttpServletRequest request, ModelMap model) throws Exception {


        boolean loginCheck = boardService.loginCheck(request);
        if(!loginCheck){
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "/loginView.do");
            return "lhw/cmm/redirectMsg";
        }

        LoginVO loginVO = new LoginVO();
        loginVO = boardService.getLoginVO(request);
        model.addAttribute("userNm", loginVO.getUserNm());

        return "lhw/cop/board/add";
    }

    /**
     * 게시물 등록을 처리한다.
     * @param boardVO
     * @param request
     * @param model
     * @throws Exception
     */
    @RequestMapping(value="/addBoard.do")
    public String addBbsNtt( @ModelAttribute("boardVO") BoardVO boardVO,
                            HttpServletRequest request, ModelMap model) throws Exception {

        boolean loginCheck = boardService.loginCheck(request);
        if(!loginCheck){
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "/loginView.do");
            return "lhw/cmm/redirectMsg";
        }

        // 등록 처리
        try {
            boardService.insertBoard(request, boardVO);
        } catch(Exception e) {
            model.addAttribute("msg", "에러가 발생하였습니다.");
            return "lhw/cmm/backMsg";
        }

        model.addAttribute("msg", "등록을 완료하였습니다.");
        model.addAttribute("url", "/selectBoardList.do");
        return "lhw/cmm/redirectMsg";
    }

    /**
     * 게시물 수정 페이지로 이동한다.
     * @param boardVO
     * @param request
     * @param model
     * @throws Exception
     */
    @RequestMapping(value="/updateBoardView.do")
    public String updateBoardView(@ModelAttribute("boardVO") BoardVO boardVO,
                               HttpServletRequest request, ModelMap model) throws Exception {

        boolean loginCheck = boardService.loginCheck(request);
        if(!loginCheck){
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "/loginView.do");
            return "lhw/cmm/redirectMsg";
        }

        BoardVO resultVO = new BoardVO();
        resultVO = boardService.selectBoard(boardVO);
        if(null == resultVO){
            model.addAttribute("msg", "잘못된 접근입니다.");
            return "lhw/cmm/backMsg";
        }

        model.addAttribute("board", resultVO);

        LoginVO loginVO = new LoginVO();
        loginVO = (LoginVO) request.getSession().getAttribute("loginVO");
        if(null != loginVO){
            if(loginVO.getUserId().equals(resultVO.getFirstId())){
                model.addAttribute("boardMng", true);
            }
        }
        return "lhw/cop/board/updt";
    }

    /**
     * 게시물 수정을 처리한다.
     * @param boardVO
     * @param request
     * @param model
     * @throws Exception
     */
    @RequestMapping(value="/updateBoard.do")
    public String updateBoard( @ModelAttribute("boardVO") BoardVO boardVO,
                             HttpServletRequest request, ModelMap model) throws Exception {

        boolean loginCheck = boardService.loginCheck(request);
        if(!loginCheck){
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "/loginView.do");
            return "lhw/cmm/redirectMsg";
        }

        BoardVO resultVO = new BoardVO();
        resultVO = boardService.selectBoard(boardVO);
        if(null == resultVO){
            model.addAttribute("msg", "잘못된 접근입니다.");
            return "lhw/cmm/backMsg";
        }

        LoginVO loginVO = new LoginVO();
        loginVO = boardService.getLoginVO(request);
        if(!loginVO.getUserId().equals(resultVO.getFirstId())){
            model.addAttribute("msg", "잘못된 접근입니다.");
            return "lhw/cmm/backMsg";
        }

        // 수정 처리
        try {
            boardService.updateBoard(request, boardVO);
        } catch(Exception e) {
            model.addAttribute("msg", "에러가 발생하였습니다.");
            return "lhw/cmm/backMsg";
        }

        model.addAttribute("msg", "수정을 완료하였습니다.");
        model.addAttribute("url", "/selectBoardList.do");
        return "lhw/cmm/redirectMsg";
    }

    /**
     * 게시물 상세화면 페이지로 이동한다.
     * @param boardVO
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/selectBoardView.do")
    public String selectBoardView(@ModelAttribute("boardVO") BoardVO boardVO,
                                  HttpServletRequest request, ModelMap model) throws Exception {

        BoardVO resultVO = new BoardVO();
        resultVO = boardService.selectBoard(boardVO);
        if(null == resultVO){
            model.addAttribute("msg", "잘못된 접근입니다.");
            return "lhw/cmm/backMsg";
        }

        model.addAttribute("board", resultVO);

        LoginVO loginVO = new LoginVO();
        loginVO = boardService.getLoginVO(request);
        if(null != loginVO){
            if(loginVO.getUserId().equals(resultVO.getFirstId())){
                model.addAttribute("boardMng", true);
            }
        }

        return "lhw/cop/board/view";
    }

    /**
     * 게시물 삭제 처리한다.
     * @param boardVO
     * @param request
     * @param model
     * @throws Exception
     */
    @RequestMapping(value="/deleteBoard.do")
    public String deleteBoard( @ModelAttribute("boardVO") BoardVO boardVO,
                               HttpServletRequest request, ModelMap model) throws Exception {

        boolean loginCheck = boardService.loginCheck(request);
        if(!loginCheck){
            model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
            model.addAttribute("url", "/loginView.do");
            return "lhw/cmm/redirectMsg";
        }

        BoardVO resultVO = new BoardVO();
        resultVO = boardService.selectBoard(boardVO);
        if(null == resultVO){
            model.addAttribute("msg", "잘못된 접근입니다.");
            return "lhw/cmm/backMsg";
        }

        LoginVO loginVO = new LoginVO();
        loginVO = boardService.getLoginVO(request);
        if(!loginVO.getUserId().equals(resultVO.getFirstId())){
            model.addAttribute("msg", "잘못된 접근입니다.");
            return "lhw/cmm/backMsg";
        }

        // 삭제 처리
        try {
            boardService.deleteBoard(request, boardVO);
        } catch(Exception e) {
            model.addAttribute("msg", "에러가 발생하였습니다.");
            return "lhw/cmm/backMsg";
        }

        model.addAttribute("msg", "삭제 완료하였습니다.");
        model.addAttribute("url", "/selectBoardList.do");
        return "lhw/cmm/redirectMsg";
    }
}
