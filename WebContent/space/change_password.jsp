<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<style type="text/css">
	#chpwd{
		text-align: center;
		width: 400px;
		margin: 0 auto;
		margin-top: 100px;
	}
	#oldpwd{
		width: 300px;
	}
	#newpwd{
		width: 300px;
	}
	#againpwd{
		width: 300px;;
	}
	#s1,#s2,#s3{
		width: 80px;
	}
	#achpwd_submit{
		width: 100px;
	}
	#msg{
		color: red;
	}
</style>
<title>修改密码</title>
</head>
<body>
	<div id="chpwd">
		<p id="msg">${msg }</p>
		<form action="change_password" method="post" onsubmit="return check()">
			<div id="old_pwd_div" class="input-group">
				<span id="s1" class="input-group-addon">旧密码</span>
				<input type="password" name="oldpwd" id="oldpwd" class="form-control"/>
			</div><br>
			<div id="new_pwd_div" class="input-group">
				<span id="s2" class="input-group-addon">新密码</span>
				<input type="password" name="newpwd" id="newpwd" class="form-control"/>
			</div><br>
			<div id="again_new_pwd_div" class="input-group">
				<span id="s3" class="input-group-addon">再次输入</span>
				<input type="password" name="againpwd" id="againpwd" class="form-control"/>
			</div><br>
			<input id="achpwd_submit" type="submit" id="submit" value="确定" class="btn btn-default" />
		</form>
	</div>
</body>
<script type="text/javascript">
	function check() {
		var newPassword = document.getElementById("newpwd").value;
		var againPassword = document.getElementById("againpwd").value;
		if(newPassword.length == 0) {
			alert("新密码不能为空");
			return false;
		}
		if(newPassword == againPassword)
			return true;
		else {
			alert("两次密码不一致");
			return false;
		}
	}

</script>
</html>