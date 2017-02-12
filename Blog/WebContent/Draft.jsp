<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
</head>
<body>
<div class="row">
        <div class="col-md-12">
          <table class="table">
            <thead>
              <tr>
                <th>标题</th>
                <th>阅读</th>
                <th>评论</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1</td>
                <td>Mark</td>
                <td>Otto</td>
                <td>  
                	<button type="button" class="btn btn-primary">编辑</button>
                	<button type="button" class="btn btn-danger">删除</button>
                </td>
              </tr>
             <!--  <tr>
                <td>2</td>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
              </tr>
              <tr>
                <td>3</td>
                <td>Larry</td>
                <td>the Bird</td>
                <td>@twitter</td>
              </tr> -->
            </tbody>
          </table>
        </div>
       
      </div>
   <script src="js/jquery-3.1.1.js"></script>
    <script src="js/bootstrap.js"></script>
     <script type="text/javascript">
     
     function deleteDraft(id) {
    	  $.ajax({
    		    url: "<%=basePath%>/BlogController?type=draft&id=" + id,
    		    type: 'DELETE',
    		    success: function(result) {
    		        // Do something with the result
    		        var objName = "#" + id;
    		        $("#" + id).remove();
    		    }
    		});
     }
     
     $(document).ready(function(){
    	<%--   
    	  htmlobj=$.ajax({url:"<%=basePath%>/BlogController?type=draft",async:false});
    	  $("#myDiv").html(htmlobj.responseText);
    	   --%>
    	  $.get("<%=basePath%>/BlogController?type=draft", function(result){
    		    var json = $.parseJSON(result);
    		    $.each(json, function(i, item) {
    	            $("tbody").append(
    	            	"<tr id=\""+ item.id + "\">"+
	    	            "<td>" + item.title + "</td>" +
	    	            "<td>" + item.readCount + "</td>" +
	    	            "<td>" + item.commentCount + "</td>" +
	                    "<td>" +
	                    "<button type=\"button\" class=\"btn btn-primary\">编辑</button>" +
	                    "<button type=\"button\" class=\"btn btn-danger\" onclick=\"deleteDraft("+item.id +")\">删除</button>" +
	                    "<td>" + "<tr>");
    	        });
    		  });
    	});
		
	</script>
</body>
</html>