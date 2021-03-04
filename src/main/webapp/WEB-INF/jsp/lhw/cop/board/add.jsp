<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
    <link rel="stylesheet" href="/common/css/default.css" />
    <link rel="stylesheet" href="/common/css/program.css" />
</head>
<body>

<div class="p-wrap bbs bbs__form" style="padding: 100px;">
    <form name="boardForm" id="board" method="post" action="./addBoard.do" onsubmit="return fn_validatorBoard(this)">
        <fieldset>
            <legend>게시물 작성</legend>
            <table class="p-table mobile block form">
                <caption>공지사항 상세보기 - 제목,작성자,작성일,조회수,내용,파일 정보 제공</caption>
                <colgroup>
                    <col class="w15p">
                    <col>
                </colgroup>
                <tbody class="p-table--th-left">
                <tr class="p-table__subject">
                    <th scope="row"><label class="p-form__label" for="title">제목</label></th>
                    <td><input type="text" name="title" id="title" value="${boardVO.title}" class="p-input"/></td>
                </tr>
                <tr>
                    <th scope="row">작성자</th>
                    <td>
                        ${userNm}
                    </td>
                </tr>
                <tr>
                    <th scope="row"><label class="p-form__label" for="contents">내용</label></th>
                    <td>
                        <textarea name="contents" id="contents" class="p-input">${boardVO.contents}</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="text_right margin_t_30">
                <a href="./selectBoardList.do?searchCnd=${boardVO.searchCnd}&amp;searchKrwd=${boardVO.searchKrwd}&amp;pageIndex=${boardVO.pageIndex}" class="p-button cancel">취소</a>
                <input type="submit" class="p-button write" value="글등록">
            </div>
        </fieldset>
    </form>
</div>

<script>
    function fn_validatorBoard( frm ) {

        if( !frm.title.value ) {
            alert("제목을 입력해주세요.");
            frm.title.focus();
            return false;
        }

        if( !frm.contents.value ) {
            alert("내용을 입력해주세요.");
            frm.contents.focus();
            return false;
        }

        return true;
    }
</script>

</body>
</html>