<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	 <h1 style="text-align: center; color : green;" style="font-size: 100px;">Validation!</h1>
 
 
<form action = "createRequestServlet" method = "post" style="text-align: center;">
 <h1 style="font-size: 20px;">Enter the OTP</h1>
<input type = "text"style ="font-size: 30px;" name = "otp"><br>


<h1 style="text-align: center;" style="font-size: 60px;">The request you want to modify is registered from date </h1><br>
	<%out.println(request.getAttribute("startData"));   %>
	<h1 style="text-align: center;" style="font-size: 60px;">to date </h1><br>
	<%out.println(request.getAttribute("endData"));%>

	
	<h1 style="text-align: center;" style="font-size: 60px;">Insert the date from which you want to start your application: </h1><br>
	<span>
     <select name="start_month" style ="font-size: 30px;"  >
        <option value="1">January</option>
        <option value="2">February</option>
        <option value="3">March</option>
        <option value="4">April</option>
        <option value="5">May</option>
        <option value="6">June</option>
        <option value="7">July</option>
        <option value="8">August</option>
        <option value="9">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
     </select> 
</span>
<span>
     <select name="start_day" style ="font-size: 30px;" >
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
          <option value="13">13</option>
          <option value="14">14</option>
          <option value="15">15</option>
          <option value="16">16</option>
          <option value="17">17</option>
          <option value="18">18</option>
          <option value="19">19</option>
          <option value="20">20</option>
          <option value="21">21</option>
          <option value="22">22</option>
          <option value="23">23</option>
          <option value="24">24</option>
          <option value="25">25</option>
          <option value="26">26</option>
          <option value="27">27</option>
          <option value="28">28</option>
          <option value="29">29</option>
          <option value="30">30</option>
          <option value="31">31</option>
     </select>
</span>
<span>
     <select name="start_year" style ="font-size: 30px;" >
          <option value="2024">2024</option>
          <option value="2023">2023</option>
          <option value="2022">2022</option>
          <option value="2021">2021</option>
          <option value="2020">2020</option>
            </select>
</span><br>
<h1 style="text-align: center;" style="font-size: 60px;">until</h1><br>
<span>
     <select name="end_month" style ="font-size: 30px;" >
        <option value="1">January</option>
        <option value="2">February</option>
        <option value="3">March</option>
        <option value="4">April</option>
        <option value="5">May</option>
        <option value="6">June</option>
        <option value="7">July</option>
        <option value="8">August</option>
        <option value="9">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
     </select> 
</span>
<span>
     <select name="end_day" style ="font-size: 30px;" >
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
          <option value="13">13</option>
          <option value="14">14</option>
          <option value="15">15</option>
          <option value="16">16</option>
          <option value="17">17</option>
          <option value="18">18</option>
          <option value="19">19</option>
          <option value="20">20</option>
          <option value="21">21</option>
          <option value="22">22</option>
          <option value="23">23</option>
          <option value="24">24</option>
          <option value="25">25</option>
          <option value="26">26</option>
          <option value="27">27</option>
          <option value="28">28</option>
          <option value="29">29</option>
          <option value="30">30</option>
          <option value="31">31</option>
     </select>
</span>
<span>
     <select name="end_year" style ="font-size: 30px;" >
          
          <option value="2024">2024</option>
          <option value="2023">2023</option>
          <option value="2022">2022</option>
          <option value="2021">2021</option>
          <option value="2020">2020</option>
          
            </select>
</span><br>
	
	<input type="submit" style="width: 100px; height: 35px;  background-color: #008080; color: #fff;" value="Modify">
	</form>
	<img src="https://d2jx2rerrg6sh3.cloudfront.net/image-handler/ts/20210913063105/ri/673/picture/2021/9/shutterstock_739797655.jpg" >
</body>
</html>


