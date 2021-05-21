<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content" content="text/html;" charset="UTF-8">
	<title>Null 에러 발생 </title>
</head>
<body>

<%
	String name = request.getParameter("name");
	if(name==null) {
		throw new NullPointerException();
	}
%>

</body>
</html>