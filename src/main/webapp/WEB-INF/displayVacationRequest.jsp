<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1 style="text-align: center;" style="font-size: 60px;">Your requests are:</h1>
	<%
		while (request.getAttribute("size") != null) {   %><p><%
			out.println(request.getAttribute("list1"));
	%></p><%	}
	%>

 <h1 style="text-align: center;" style="font-size: 60px;">Log In</h1>
  <h1 style="text-align: center;font-size: 20px;">It's quick and easy</h1>


<img src="https://thumbs.dreamstime.com/z/vector-illustration-isolated-white-background-login-button-icon-126999949.jpg"width="300" height="300">
</body>
</html>