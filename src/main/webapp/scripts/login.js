$(document).ready(function(){
	$("#login#loginButton").click(function(e){
		
		var uname=$("input$username");
		var passwd=$("input$password");
		
		var data=JSON.stringify({userName:uname, password:passwd});
		
		$.ajax({
			type : "POST",
			url : "rest/user/login",
			datatype : "json",
			contentType : "application/json",
			data : data,
			success : function(result){
				alert("User login successful");
			}
		});
	});
});