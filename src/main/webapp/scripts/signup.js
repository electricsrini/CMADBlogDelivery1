$(document).ready(function(){
	$("#signup").click(function(){
		
		var fname=$("#firstnamesignup").val();
		var lname=$("#lastnamesignup").val();
		var uname=$("#usernamesignup").val();
		var mail=$("#emailsignup").val();
		var passwd=$("#passwordsignup").val();
		
		var data=JSON.stringify({firstName:fname, lastName:lname, userName:uname, emailId:mail, password:passwd});
		
		$.ajax({
			type : "POST",
			url : "rest/user",
			dataType : "json",
			contentType : "application/json",
			data : data,
			success : function(result){
				alert("User registration for "+uname+" is successful");
				window.location("#tologin");
			}
		});
	});
});