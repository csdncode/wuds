<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="wuds.entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/usermanage.css"/>
<script src="../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function resetPassword(url){
		$.ajax({
			type:"get",
			url:url,
			dataType:"json",
			success:function (data) {
				if(data.success) {
					alert("重置成功");
				} else {
					alert("重置失败");
				}
			},
			error:function () {
				alert("系统异常");
			}
		})
	}
</script>
</head>
<body>
	<div id="user_manage" class="panel panel-default">
		<div class="panel-heading">
			<form action="manage" method="post" onsubmit="return check()">
				<table>
					<tr>
						<td><input type="text" name="find" id="find" class="form-control" placeholder="请输入要查找的学号或姓名"/></td>
						<td><input type="submit" id="submit" class="btn btn-default" value="查找"/></td>
						<td><button type="button" id="return" class="btn btn-default" onclick="window.location.href='../space/home'">返回</button></td>
					</tr>
				</table>
			</form>
			<script type="text/javascript">
				function check(){
					if(document.getElementById("find").value == '' || document.getElementById("find").value.length == 0) {
						alert('请输入要查询的学号或姓名');
						return false;
					} else {
						return true;
					}
				}
			</script>
		</div>
		<div class="panel-body">
			<table class="table">
				<tr>
					<td width="266px">学号</td>
					<td width="266px">姓名</td>
					<td width="268px"></td>
				</tr>
				<c:if test="${!empty msg }">
					<tr>
						<td colspan="3" align="center"><font color="red">${msg }</font></td>
					</tr>
				</c:if>
				<c:forEach items="${users }" var="user">
					<tr>
						<td width="266px">${user.username }</td>
						<td width="266px">${user.name }</td>
						<td width="268px"><button onclick="resetPassword('resetpassword?username=${user.username }')" class="btn btn-danger">重置密码</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>