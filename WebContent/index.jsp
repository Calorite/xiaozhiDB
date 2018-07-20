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
	<div style="padding-top:10px">
		<form id="usrform" >
			<div class="leftitem form-group">
				<textarea id="textn" name="description" rows="15" cols="30"
					onClick="SelectText()"></textarea>
			</div>
			<div class="leftitem form-group">
				<button type="button"  id="tijiao">提交</button>
			</div>	　
		</form>
		<form id="parameterform">
			<div class="leftitem form-group" id="parameterlist">
				<table id="tbe" class="table">

				</table>
			</div>
			<div class="endleft form-group">
				SolutionID:<input name="solution" type="text"></input> <input
					type="submit">
			</div>
		</form>
	</div>
	<script src="js/index.js"></script>
</body>
</html>