<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.lhw.cmm.StringUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Pragma", "no-cache" );
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-store");
    response.setHeader("Cache-Control", "no-cache" );
%>
<%

    String rurl = StringUtil.nvl((String)request.getParameter("rurl"));
    if( "".equals(rurl) ) {
        rurl = "/index.do";
    } else if( "REFERER".equals(rurl) ) {
        rurl = request.getHeader("referer");
        if( StringUtil.isEmpty(rurl) ) {
            rurl = "/index.do";
        }
    }

    session.setAttribute("LOGIN_RETURN_URL", rurl);

%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <title>로그인</title>
</head>
<body id="main">

<a href="#n" onclick="fn_kakao();" title="새창">
    <img src="/common/images/kakao_login_large_wide.png">
</a>

<script>
    function fn_kakao() {
        window.location = '${kakaoAuthUrl}';
    }
</script>

</body>
</html>
