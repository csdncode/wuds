<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="wuds.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="wuds.entity.Message" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="../css/homepage.css">
<script src="../js/countdown.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<title>${user.name },欢迎</title>
</head>
<body>
	<div class="panel panel-default" id="work_info_div">
		<div class="panel-heading"><p>作业列表</p></div>
		<div class="panel-body">
			<table class="table" id="work_tb">
				<tr>
					<td>作业</td>
					<td>剩余时间</td>
					<td></td>
					<td></td>
				</tr>
				<c:forEach items="${works }" var="work">
					<tr>
						<td width="200px">${work.course }${work.expNo }</td>
						<c:choose>
							<c:when test="${work.endTime - nowTime < 0}">
								<td width="200px">0</td>
								<td width="168px"></td>
								<td>
									<button class="btn btn-warning" onclick="alert('请联系作业管理员')">失效</button>
								</td>
							</c:when>
							<c:otherwise>
								<td width="200px" id="${work.course }${work.expNo }"></td>
								<script type="text/javascript">
									countdown("${work.course }${work.expNo }", ${work.endTime - nowTime});
								</script>
								<form action="upload" method="post" enctype="multipart/form-data" onsubmit="return checkfile('f${work.course }${work.expNo }')">
									<input type="hidden" name="course" value="${work.course }">
									<input type="hidden" name="expNo" value="${work.expNo }">
									<td width="168px">
										<input type="file" name="workfile" id="f${work.course }${work.expNo }">
									</td>
									<td>
										<input id="submit${work.course }${work.expNo }" type="submit" value="上传" class="btn btn-success" title="再次上传可覆盖" />
										<script type="text/javascript">
											$.ajax({
												type:"get",
												url:"workexist?course=${work.course }&expNo=${work.expNo }",
												dataType:"json",
												success:function(data) {
													if(data.success) {
														document.getElementById("submit${work.course }${work.expNo }").value = '重传';
													}
												}
											})
											function checkfile(id) {
												    if(document.getElementById(id).value == null || document.getElementById(id).value == "") {
														alert("请选择要上传的文件");
														return false;
													} else {
														return true;
													}
											}
										</script>
									</td>
								</form>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="panel panel-default" id="user_info_div">
		<div class="panel-heading"><p>个人信息</p></div>
		<div class="panel-body">
			<table class="table" id="user_tb">
				<tr>
					<td width="89px">姓名：</td>
					<td width="89px">${user.name }</td>
					<td width="89px"></td>
				</tr>
				<tr>
					<td width="89px">学号：</td>
					<td width="89px">${user.username }</td>
					<td width="89px"></td>
				</tr>
				<tr>
					<td width="89px">班级：</td>
					<td width="89px">${user.grade }</td>
					<td width="89px"></td>
				</tr>
				<tr>
					<td width="89px"><a href="change_password">修改密码</a></td>
					<td width="89px"><a href="logout">退出</a></td>
					<td width="89px">${manage }</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="panel panel-default" id="ann_div">
		<div class="panel-heading"><p>公告</p></div>
		<div class="panel-body">
			<p><a href="../explain.html">说明</a></p>
			<c:forEach items="${messages }" var="message">
				<p>${message.msg }</p>
			</c:forEach>
		</div>
	</div>
</body>
</html>