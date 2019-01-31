<%@ page import="wuds.entity.Work" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/workpanle.css"/>
<script src="../js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="../js/countdown.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function check() {
			if($("#course").val().length == 0) {
				alert("课程名不能为空");
			} else if($("#expNo").val().length == 0){
				alert("实验编号不能为空");
			} else if($("#endTime").val().length == 0){
				alert("截止时间不能为空");
			} else {
				formSubmit("post","addwork","#addwork");
			}
	}
</script>
<title>管理面板</title>
</head>
<body>
	<div id="work_add_div" class="panel panel-default">
		<div class="panel-heading"><p>添加作业</p></div>
		<div class="panel-body">
			<form id="addwork">
				<table class="table">
					<tr>
						<td>课程</td>
						<td>实验编号</td>
						<td>截止日期</td>
						<td></td>
					</tr>
					<tr>
						<td><input id="course" name="course" type="text" class="form-control" placeholder="例如:操作系统" /></td>
						<td><input id="expNo" name="expNo" type="text" class="form-control" placeholder="例如:实验一" /></td>
						<td><input id="endTime" name="endTime" type="date" class="form-control" maxlength="10" placeholder="例如:2018-09-01" /></td>
						<td>
							<button type="button" class="btn btn-default" onclick="check()">添加</button>
							<button type="button" class="btn btn-default" onclick="window.location.href='../space/home'">返回</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<div id="work_download_div" class="panel panel-default">
		<div class="panel-heading"><p>作业包下载</p></div>
		<div class="panel-body">
			<table class="table">
				<tr>
					<td width="150px">课程</td>
					<td width="120px">实验编号</td>
					<td width="150px">剩余时间</td>
					<td width="148px" align="center">已提交人数</td>
					<td width="100px"></td>
					<td width="100px"></td>
				</tr>	
				<c:forEach items="${works }" var="work">
					<tr>
						<td width="150px">${work.course }</td>
						<td width="120px">${work.expNo }</td>
						<c:choose>
							<c:when test="${work.endTime - nowTime < 0 }">
								<td width="150px">0</td>
							</c:when>
							<c:otherwise>
								<td  width="150px" id="${work.course }${work.expNo }"></td>
								<script type="text/javascript">
									countdown("${work.course }${work.expNo }", ${work.endTime - nowTime});
								</script>
							</c:otherwise>
						</c:choose>
						<td width="148px" align="center">${work.count }</td>
						<td width="100px"><a href="download?course=${work.course }&expNo=${work.expNo }" class="btn btn-success">下载</a></td>
						<td width="100px"><a href="delete?course=${work.course }&expNo=${work.expNo }" class="btn btn-danger">删除</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>