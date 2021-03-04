<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.lhw.cop.login.service.LoginVO" %>
<%
    LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
    <link rel="stylesheet" href="/common/css/default.css" />
    <link rel="stylesheet" href="/common/css/program.css" />
</head>
<body>

<div class="p-wrap bbs bbs__list bbs1" style="padding: 200px;">
    <div class="card card--bgcolor p-search">
        <form name="boardSearchForm" action="./selectBoardList.do" method="get" class="boardSearchForm">
            <fieldset>
                <legend>게시물 검색</legend>
                <div class="p-form-group">
                    <select name="searchCnd" id="searchCnd" class="p-input" title="검색영역선택">
                        <option value="SJ" <c:if test="${boardVO.searchCnd eq 'SJ'}">selected="selected"</c:if>>제목</option>
                        <option value="CN" <c:if test="${boardVO.searchCnd eq 'CN'}">selected="selected"</c:if>>내용</option>
                        <option value="WT" <c:if test="${boardVO.searchCnd eq 'WT'}">selected="selected"</c:if>>작성자</option>
                    </select>
                    <span class="p-form__split p-form__split-short"></span>
                    <input type="text" name="searchKrwd" id="searchKrwd" class="p-input p-input--beside" title="검색단어 입력" value="${boardVO.searchKrwd}" placeholder="검색단어 입력">
                    <span class="p-form-group__button">
                        <button type="submit" class="p-button search">검색</button>
                        <c:choose>
                            <c:when test="${not empty loginVO}">
                                <a href="/logout.do" class="p-button write">
                                    로그아웃
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="/loginView.do" class="p-button write">
                                    로그인
                                </a>
                            </c:otherwise>
                        </c:choose>

                    </span>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="row">
        <div class="col-12 col-sm-24 margin_t_10 small">
            <fmt:formatNumber var="totalRecordCount" value="${paginationInfo.totalRecordCount}"/>
            <fmt:formatNumber var="currentPageNo" value="${paginationInfo.currentPageNo}"/>
            <fmt:formatNumber var="totalPageCount" value="${paginationInfo.totalPageCount}"/>
            <div class="p-total">
                총 <em  data-mask="#,##0" data-mask-reverse="true">${totalRecordCount}</em>건  &nbsp; 페이지 ${currentPageNo}/${totalPageCount}
            </div>
        </div>
    </div>
    <c:choose>
        <c:when test="${!empty(boardList)}">
            <c:set var="currentPageStartNo" value="${paginationInfo.currentPageStartNo}" />
            <table class="p-table simple" data-table="rwd" data-tabletype="simple" data-breakpoint="760">
                <caption>공지사항 목록 - 번호,제목,작성자,작성일,조회수,파일 정보  제공</caption>
                <colgroup>
                    <col style="width:70px">
                    <col style="width:120px">
                    <col style="width:80px">
                    <col style="width:80px">
                </colgroup>
                <thead>
                <tr>
                    <th scope="col" class="first">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">작성자</th>
                    <th scope="col">작성일</th>
                </tr>
                </thead>
                <tbody class="text_center">
                <c:forEach var="result" items="${boardList}" varStatus="status">
                    <fmt:formatNumber var="number" value="${currentPageStartNo}"/>
                    <tr>
                        <td><c:out value="${number}"/></td>
                        <td class="p-subject">
                            <a href="./selectBoardView.do?boardNo=${result.boardNo}&amp;searchCnd=${boardVO.searchCnd}&amp;searchKrwd=${boardVO.searchKrwd}&amp;pageIndex=${boardVO.pageIndex}">
                                ${result.title}
                            </a>
                        </td>
                        <td><c:out value="${result.firstName}"/></td>
                        <td>
                            <fmt:parseDate value="${result.firstDate}" var="firstDate" pattern="yyyyMMddHHmmss"/>
                            <fmt:formatDate value="${firstDate}" pattern="yyyy.MM.dd"/>
                        </td>
                    </tr>
                    <c:set var="currentPageStartNo" value="${currentPageStartNo-1}" />
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="p-empty">
                등록된 게시물이 없습니다.
            </div>
        </c:otherwise>
    </c:choose>

    <div class="p-pagination">
        <ui:pagination paginationInfo = "${paginationInfo}" type="board" jsFunction="./selectBoardList.do?pageUnit=${boardVO.pageUnit}&amp;searchCnd=${boardVO.searchCnd}&amp;searchKrwd=${boardVO.searchKrwd}&amp;pageIndex=" />
    </div>
    <div class="text_right">
        <a href="./addBoardView.do?pageUnit=${boardVO.pageUnit}&amp;searchCnd=${boardVO.searchCnd}&amp;searchKrwd=${boardVO.searchKrwd}&amp;" class="p-button write">글쓰기</a>
    </div>
</div>
</body>
</html>