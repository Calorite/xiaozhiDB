var buttoncount=1;
function SelectText()
{
	try{
		var selecter=window.getSelection().toString();
		if(selecter!=null&&selecter.trim()!=""){
			$("#tbe").append("<tr id='"+buttoncount+"'><td><button onclick='deletefuc(event);'>删除</button></td> <td><button  type='button' class='btn btn-warning btn-sm buttonshow' onclick='showquestion(event);'>"+selecter+"</button></td></tr>");
			buttoncount=buttoncount+1;
		}
	}catch(err){
		var selecter=document.selection.createRange();
		var s=selecter.text;
		if(s!=null&&s.trim()!=""){
			alert(s)
		}
	}
}

function deletefuc(event){
	var p=event.srcElement.parentNode.parentNode.id;
	$("#"+p).remove();
}


$("#tijiao").on("click",function(){
	var text=$("#textn").val();
	$.ajax({
		type : "POST",
		url : "/xiaozhiDB/getparametes",
		data:{description:text},
		success : function (data) {
			var obj = JSON.parse(data);
			for(j = 0; j < obj.length; j++){
				item=obj[j];
				$("#tbe").append("<tr id='"+buttoncount+"'><td><button onclick='deletefuc(event);'>删除</button></td> <td><button id='"+item.id+"' questionid='"+item.questionId+"' type='button' class='btn btn-primary btn-sm buttonshow' onclick='showquestion(event);'>"+item.parama+"</button></td></tr>");
				buttoncount=buttoncount+1;
			}
		}});
});

function showquestion(event){
	 questionid=event.srcElement.getAttribute("questionid");
	 if(questionid!=null){
		 $.ajax({
				type : "POST",
				url : "/xiaozhiDB/question",
				data:{quesid:questionid},
				success : function (data) {
					console.log(data);
					}
				});
	 }
}

