<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>알림</title>
<script type="text/javascript" src="<c:url value="/common/js/jquery-1.11.0.min.js"/>"></script>
</head>
<body>

<script type="text/javascript">
	alert("${msg}");
	$(location).attr("href", "${url}");
</script>

</body>
</html>