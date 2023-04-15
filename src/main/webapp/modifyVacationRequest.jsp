
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <style>
      .img-container {
        display: flex;
        justify-content: center;
        align-items: center;
      }
      .img-container img {
        display: block;
        margin: auto;
      }
    </style>
</head>
<body>
 <h1 style="text-align: center;" style="font-size: 100px;">You want to make a change</h1>
 <h1 style="font-size: 40px;text-align: center;color: red;">Choose an option</h1>
<form action = "modify" method = "post" style = "text-align: center;" >
 <h1  style="font-size: 20px;text-align: center;  ">Modify an existing vacation request  </h1>
<input type="submit" style="width: 100px;text-align: center; height: 35px;  background-color: #008080; color: #fff;" value="Modify">
</form>
<h1 style="font-size: 20px; text-align: center;">Delete an existing vacation request</h1>
<form action = "delete" method = "post" style = "text-align: center;" >
<input type="submit" style="width: 100px;text-align: center; display: inline; height: 35px;  background-color: #008080; color: #fff;" value="Delete">
</form>

 <div class="img-container">
      <img src="https://d2jx2rerrg6sh3.cloudfront.net/image-handler/ts/20210913063105/ri/673/picture/2021/9/shutterstock_739797655.jpg" alt="descriere_poza">
    </div>

</body>
</html>