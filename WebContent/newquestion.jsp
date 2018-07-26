<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8" import="" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../css/listswap.css" />
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<title>问题内选项管理</title>
<style>
.container {
	margin:150px auto 0 auto;
	max-width:960px;
}
</style>
</head>

<body><div id="jquery-script-menu">
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script></div>
<div class="jquery-script-clear"></div>
<div class="container">
	<h1>选项提取</h1>
	<h2>question1</h2>
    <hr />
	<form method="post">
            <select id="source" data-search="Search for options">
                <option value="option_1">Option 1</option>
                <option value="option_2">Why not option 2</option>
                <option value="option_3">Here's another option 3</option>
                <option value="option_4">What about option 4</option>
                <option value="option_5">I'll go with option 5</option>
                <option value="option_6">Let's stick to option 6</option>
            </select>
            <select id="destination"  data-search="Search for options">
            </select>
    </form>
    <h2>question2</h2>
    <hr />
	<form method="post">
            <select id="source_4" data-search="Search for options">
                <option value="option_1">Option 1</option>
                <option value="option_2">Why not option 2</option>
                <option value="option_3">Here's another option 3</option>
                <option value="option_4">What about option 4</option>
                <option value="option_5">I'll go with option 5</option>
                <option value="option_6">Let's stick to option 6</option>
            </select>
            <select id="destination_4"  data-search="Search for options">
            </select>
    </form>

</div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/jquery.listswap.js"></script>
<script src="../js/question.js"></script>
</body>
</html>