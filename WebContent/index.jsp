<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8" import="Impl.Parama" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="lib/jquery.js"></script>
<script src="lib/jquery.cookie.js"></script>
<script src="lib/bootstrap.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/myshow.css" rel="stylesheet" />
</head>
<body>
	<br>
	<div>
		<form id="usrform" >
			<div class="leftitem form-group">
				<c:if test="${text!=null}">
				</c:if>
				<textarea id="textn" name="description" rows="30" cols="80"
					onClick="SelectText()"></textarea>
			</div>
			<button type="button" class="leftitem"  id="tijiao">提交</button>　
		</form>
		<form action="ZHI" id="parameters" method="POST">
			<div class="leftitem" id="parameterlist">
				<c:forEach items="${parameters}" var="item" varStatus="status">
					<button type='button' class='btn btn-primary btn-sm buttonshow'
						onclick='deletefuc(event);'>${item.getParama()}</button>
					<br>
				</c:forEach>
				<table id="tbe">
					
				</table>
			</div>
			<div class="endleft">
				SolutionID:<input name="solution" type="text"></input> <input
					type="submit">
			</div>
		</form>
	</div>
	<script src="js/index.js"></script>
</body>
</html>