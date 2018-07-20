var buttoncount=1;
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
	$("#"+trid).after('<form class="form-inline">'
	+'<div class="form-group">'
    +'<label for="question">问题:</label>'
    +'<input type="text" class="form-control" id="question" >'
    +'</div>'
    +'<button type="submit" class="btn btn-default">确定</button>'
    +'</form>');
}

