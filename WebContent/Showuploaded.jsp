<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/preview.css" rel="stylesheet" />
<script src="js/jquery.min.js"></script>
<script src="js/preview.js"></script>
<script src="js/imgChange.js"></script>
</head>
<body>
	<div class="container">
		<div style="margin-top: 20vh; folat: left; heigh: 500px; width: 300px">
			<div class="img-display">
				<div class="preview">
					<img alt="img" style="width:100px;heigh:100px;" src="data:image/jpeg;base64,${imagesBytes}"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>