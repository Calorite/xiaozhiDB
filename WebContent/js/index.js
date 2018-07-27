var buttoncount=1;
var testlist=[234,453,565,657];
var newquestionmap=new Map();
function SelectText()
{
	try{
		var selecter=window.getSelection().toString();
		if(selecter!=null&&selecter.trim()!=""){
			$("#tbe").append("<tr id='"+buttoncount+"'><td><div class='btn-toolbar'><div class='btn-group'><button class='btn btn-info ' onclick='dealfuc(event);'>编辑</button></div><div class='btn-group'><button  class='btn btn-danger' onclick='deletefuc(event);'>删除</button></div><div class='btn-group'><button  class='btn btn-default' onclick='shownewquestion(event);'>"+selecter+"</button></div></div></td></tr>");
			$("#tbe").append("<tr></tr>");
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
	var p=event.srcElement.parentNode.parentNode.parentNode.parentNode.id;
	$("#"+p).remove();
	$("#newquestion"+p).remove();
}



function showquestion(event){
	questionid=event.srcElement.getAttribute("questionid");
	if(questionid!=null){
		$.ajax({
			type : "POST",
			url : "/jerseyREST/webapi/myresource/question",
			data:{quesid:questionid},
			success : function (data) {
				console.log(data);
			}
		});
	}
}

function shownewquestion(event){
	newparameter=event.srcElement.innerText;
	trid=event.srcElement.parentNode.parentNode.parentNode.parentNode.id;
	if(newquestionmap[trid]){
		newquestionmap[trid]=false;
		$("#newquestion"+trid).remove();
	}else{
		$("#"+trid).after('<div id=newquestion'+trid+'>'
				+'<div class="form-group">'
				+'<label for="question">问题:</label>'
				+'<input type="text" class="form-control" id="question" >'
				+'</div>'
				+'<button class="btn btn-default" onclick="">确定</button>'
				+'</div>');
		newquestionmap[trid]=true;
	}
}

$('#gengxin').click(function(){
	solutionid=3;
	var json = JSON.stringify(testlist);
	$.ajax({
		type : "POST",
		url : "/jerseyREST/webapi/myresource/solution",
		data:{solution:solutionid,
			parameters:json
		},
		success : function (data) {
			console.log(data);
		}
	});
});
