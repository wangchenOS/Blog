<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.wc.blog.bean.Blog"%>
<!DOCTYPE html>
<head>
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
        Blog blog=(Blog)request.getAttribute("blog");
        
       
	%>
</head>
<body>
   <%
		if (blog != null) {
	%>
	 <input type="hidden"  id="draftId" name=""draftId"" value="<%=blog.getId()%>">
	 <input type="hidden"  id="draftTitle" name="draftTitle" value="<%=blog.getTitle()%>">
	 <input type="hidden"  id="draftContent" name="draftContent" value="<%=blog.getContent()%>">
	 <input type="hidden"  id="draftTag" name="draftTag" value="<%=blog.getTag()%>">
	<%
		}
	%>
   
  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#"></a>
        </div>
       
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
            <li><a href="#">发布文章</a></li>
            <li><a href="" onclick="">文章管理</a></li>
            <li><a href="#" onclick="showDraft()">草稿夹</a></li>
          </ul>
          
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <div class="row placeholders">
             <iframe src="editor.jsp" frameBorder="0" width="100%" scrolling="no" height="900"></iframe>
          </div>

        </div>
      </div>
    </div>
    
     <script src="js/jquery-3.1.1.js"></script>
    <script src="js/bootstrap.js"></script>
     <script type="text/javascript">
	     function showDraft()
	     {
	    	$("iframe").attr("src", "draft.jsp");
	     }
		
	</script>
</body>
</html>