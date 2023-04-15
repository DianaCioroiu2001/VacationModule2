<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="login" method="post" style="text-align: center;">
	<%
		if (request.getAttribute("message") != null) {   %><p><%
			out.println(request.getAttribute("message"));
	%></p><%	}
	%>

 <h1 style="text-align: center;" style="font-size: 60px;">Log In</h1>
  <h1 style="text-align: center;font-size: 20px;">It's quick and easy</h1>
<form action = "userAccount" method = "post" style = "text-align: center;">

<h1 style="text-align: center;" style="font-size: 60px;">Enter email address:</h1>
<input type = "text" name = "email" style ="font-size: 30px; "><br>
<h1 style="text-align: center;" style="font-size: 60px;">Enter password:</h1>
<input type = "password" name = "password" style ="font-size: 30px; "><br>
<h1 style="text-align: center;" style="font-size: 60px;">Enter otp</h1>
<input type="text" name="otp" style ="font-size: 30px; "><br>
         <input type="submit" style="width: 150px; height: 50px;  background-color: #008080; color: #fff;" value="Sign Up">
</form>
<img src="https://thumbs.dreamstime.com/z/vector-illustration-isolated-white-background-login-button-icon-126999949.jpg"width="300" height="300">
</body>
</html>