<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<html>
<head>
 <meta http-equiv="content-type" content="txt/html; charset=utf-8" />
<link rel="stylesheet" href="./common/media/css/bootstrap.min.css" type="text/css" />
<script type="text/javascript" src="./common/media/js/jquery-1.10.1.min.js"></script>

</head>
<body>
<h2>Hello，我是index.jsp</h2>
<br>
 <input style="margin-left:20px;" type="button" onclick="getUserAll()" id="test1" value="获得用户列表"/> 
 <input style="margin-left:20px;" type="button" onclick="addUser()" id="test1" value="注册用户"/> 
 <input style="margin-left:20px;" type="button" onclick="login()" id="test1" value="登陆"/> 
 <br/>
 <textarea style="margin:20px;" rows="3" cols="100" id="textarea1"></textarea>
</body>
</html>
<script type="text/javascript">
var basePath='http://127.0.0.1:8080/ssm';
var token = '';
//登陆
function login() {
	  $.ajax({
		  type:'POST',
          url: basePath + '/user/login',
          dataType: 'json',
          data : JSON.stringify(
    		  	{param:{
    		  		phoneNo : "18862123185",
    		  		password:'123456'
    			  }
    			}), 
          success: function (data) {
        		if(data.flag=='1'){
        			token = data.token;
        			console.log(token);
        			document.getElementById('textarea1').innerText=JSON.stringify(token);
        		}else{
        			console.log(data.msg);
        		}
          }
});
};
//获得用户列表
function getUserAll() {
	  $.ajax({
		  type:'POST',
          url: basePath + '/user/all',
          dataType: 'json',
          data : JSON.stringify(
    		  	{param:{
    		  		'token' : token
    			  }
    			}), 
          success: function (data) {
        		if(data.flag=='1'){
        			var result=data.data;
        			console.log(result);
        			document.getElementById('textarea1').innerText=JSON.stringify(result);
        		}else{
        			console.log(data.msg);
        			document.getElementById('textarea1').innerText=JSON.stringify(data.msg);
        		}
          }
});
};
//添加用户
function addUser() {
	  $.ajax({
		  type:'POST',
        url: basePath + '/user/register',
        dataType: 'json',
        data :JSON.stringify(
    		  	{param:{
    		  		userName : "å°ç",
    		  		age:21,
    		  		password:'123456',
    		  		phoneNo:'18862123188',
    		  		token : "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE0OTYzNzY2NDE4NTMsInVpZCI6MzMwMDUsImlhdCI6MTQ5NjM3MzA0MTg1M30.r1INSFfs7v9j6GP-rsjafPvBeGtnHseIn7LnAJdqdic"
    			  }
    			}), 
        success: function (data) {
      		if(data.flag=='1'){
      			document.getElementById('textarea1').innerText=data.msg;
      		}else{
      			console.log(data.msg);
      		}
        }
});
};
</script>
