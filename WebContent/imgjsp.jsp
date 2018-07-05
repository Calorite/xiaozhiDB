<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--
preview.js
-------------------------------------------------------------
$(function(){
	// 画像ファイルプレビュー表示のイベント追加 fileを選択時に発火するイベントを登録
	$('form').on('change', 'input[type="file"]', function(e) {
	    var file = e.target.files[0],
	        reader = new FileReader(),
	        $preview = $(".preview");
	        t = this;

	        console.log( e.target.files );
	    // 画像ファイル以外の場合は何もしない
	    if(file.type.indexOf("image") < 0){
	    	$("#uploadTrigger").prop("disabled", true);
	      return false;
	    }
	    // ファイル読み込みが完了した際のイベント登録
	    reader.onload = (function(file) {
	      return function(e) {
	        // 既存のプレビューを削除
	        $preview.empty();
	        // .prevewの領域の中にロードした画像を表示するimageタグを追加
	        $preview.append($('<img>').attr({
	                  src: e.target.result,
	                  //width: "150px",
	                  class: "preview",
	                  title: file.name
	              }));
	      };
	    })(file);
	    reader.readAsDataURL(file);

	    //preview画像の下にアップロードした画像ファイルの名前を取得して表示させる
	    var imgPath = $('input[type="file"]').prop('files')[0].name;
		$("#imageName").text( imgPath );
		$('input[type="hidden"]').val( imgPath );
		$("#uploadTrigger").prop("disabled", false);
	});
});


--------------------------------------------------------imgChange.js---------------------------
$('#imgFile').change(
    function () {
        if (!this.files.length) {
            return;
        }

        var file = $(this).prop('files')[0];
        var fr = new FileReader();
        $('.preview').css('background-image', 'none');
        fr.onload = function() {
            $('.preview').css('background-image', 'url(' + fr.result + ')');
        }
        fr.readAsDataURL(file);
        $(".preview img").css('opacity', 0);
    }
);


---------------------------------------------------------------------------------------------
-->
</body>
</html>