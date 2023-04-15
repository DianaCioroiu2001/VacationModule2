<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 
</head>
<body>
<h1 style="text-align: center;color: green; font-size: 50px;">Congratulations!</h1>
	
	<style>
    .text-large {
        font-size: 20px;
        text-align: center;
    }
  
</style>
<h1 style="text-align: center;" style="font-size: 40px;"> Your request from date</h1>
<p class="text-large">${startData}</p>
<h1 style="text-align: center;" style="font-size: 40px;"> to date</h1>
<p class="text-large">${endData}</p>
 <h1 style="text-align: center;" style="font-size: 40px;">has been created, you still have</h1>
<p class="text-large">${days}</p>
<h1 style="text-align: center;" style="font-size: 40px;">days of leave available</h1>
<img   src="https://previews.123rf.com/images/krisdog/krisdog2211/krisdog221100203/194661822-thumbs-up-emoji-emoticon-face-cartoon-icon.jpg"width="100" height="100" class = "center">


</body>
</html>