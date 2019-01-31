<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/login.css"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
<title>login</title>
</head>

<body>
	<div class="formDiv">
		<div id="msg">${msg }</div>
		<form action="login" method="post">
		<div class="input-group" class="input_div">
			<span class="input-group-addon">账号</span>
				<input id="username" name="username" value="${username }" class="form-control" type="text" placeholder="学号"/>
				</div><br>
			<div class="input-group" class="input_div">
			<span class="input-group-addon">密码</span>
				<input id="password" name="password" class="form-control" type="password" placeholder="初始密码123456" />
				</div><br>
			<input id="login" name="login" class="btn btn-default" type="submit" value="登录" />
		</form>
	</div>
</body>
</html>