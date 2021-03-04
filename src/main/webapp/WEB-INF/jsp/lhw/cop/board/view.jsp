<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    pageContext.setAttribute("rn", "\r\n"); //Space, Enter
    pageContext.setAttribute("br", "<br/>"); //br 태그
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
    <link rel="stylesheet" href="/common/css/default.css" />
    <link rel="stylesheet" href="/common/css/program.css" />
</head>
<body>

<div class="p-wrap bbs bbs__view" style="padding: 200px">
    <table class="p-table block" data-table="rwd" data-tabletype="block" data-breakpoint="760">
        <caption>게시물 상세보기 - 제목,작성자,작성일,조회수,내용,파일 정보  제공</caption>
        <colgroup>
            <col class="w15p">
            <col>
        </colgroup>
        <tbody class="p-table--th-left">
        <tr class="p-table__subject">
            <td colspan="2">
                <span class="p-table__subject_text"><c:out value="${board.title}"/></span>
                <div class="p-author__info">
                    <span class="p-split"><c:out value="${board.firstName}"/></span>
                    <fmt:parseDate value="${board.firstDate}" var="firstDate" pattern="yyyyMMddHHmmss"/>
                    <span class="p-split"> 작성일 : <fmt:formatDate value="${firstDate}" pattern="yyyy.MM.dd"/></span>
                    <c:if test="${not empty board.lastDate}">
                        <fmt:parseDate value="${board.lastDate}" var="lastDate" pattern="yyyyMMddHHmmss"/>
                        <span class="p-split">최종수정일 : <fmt:formatDate value="${lastDate}" pattern="yyyy.MM.dd"/></span>
                    </c:if>
                </div>
            </td>
        </tr>

        <tr>
            <th scope="row" style="width: 140px;">내용</th>
            <td>
                ${board.contents}
            </td>
        </tr>
        </tbody>
    </table>
    <div class="row margin_t_30">
        <div class="col-12">
            <a href="./updateBoardView.do?boardNo=${board.boardNo}&amp;searchCnd=${fn:escapeXml(boardVO.searchCnd)}&amp;searchKrwd=${fn:escapeXml(boardVO.searchKrwd)}&amp;pageIndex=${fn:escapeXml(boardVO.pageIndex)}" class="p-button p-button--bordered edit">수정</a>
            <a href="./deleteBoard.do?boardNo=${fn:escapeXml(boardVO.boardNo)}&amp;searchCnd=${fn:escapeXml(boardVO.searchCnd)}&amp;searchKrwd=${fn:escapeXml(boardVO.searchKrwd)}&amp;pageIndex=${fn:escapeXml(boardVO.pageIndex)}" onclick="fn_deleteBoard(this.href); return false;" class="p-button p-button--bordered delete">삭제</a>
        </div>
        <div class="col-12 right">
            <a href="./selectBoardList.do?boardNo=${board.boardNo}&amp;searchCnd=${boardVO.searchCnd}&amp;searchKrwd=${encSearchKrwd}&amp;pageIndex=${boardVO.pageIndex}" class="p-button">목록<span></span></a>
        </div>
    </div>
</div>

<script>
    function fn_deleteBoard( url ) {
        if( confirm("삭제하시겠습니까?") ) {
            window.location = url;
        }
        return false;
    }
</script>

</body>
</html>