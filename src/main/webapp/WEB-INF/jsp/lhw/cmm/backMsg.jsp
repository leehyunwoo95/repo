<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>오류발생</title>
<script src="<c:url value="/common/js/jquery-1.11.0.min.js"/>"></script>
</head>
<body>

<script>
	alert("${msg}");
	window.history.back();
</script>

</body>
</html>