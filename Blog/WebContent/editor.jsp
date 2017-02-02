<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
    <!--link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet"-->

    <!-- Custom styles for this template -->
	<title>Insert title here</title>
    <link href="css/dashboard.css" rel="stylesheet">
    <%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
</head>
<body>
 <div class="container-fluid">
      <div class="row">
        <h1 style="text-align:center">博客后台</h1>
      </div>
      <div class="row">
          <form action="<%=basePath %>BlogController" method="POST">
              <input type="hidden" class="form-control" id="content" name="content" value="1">
              
              <div class="form-group">
                <label for="title">标题</label>
                <input type="text" class="form-control" id="title" name="tile" placeholder="Title" required  >
              </div>
              <div class="form-group">
                <label>正文</label>
                <div id="epiceditor"></div>
                <!--input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"-->
              </div>
              <div class="form-group">
                <label for="InputFile">文件上传</label>
                <input type="file" id="InputFile">
               
              </div>
              <div class="form-group">
                <p class="help-block">标签（使用英文输入法的逗号分隔）:</p>
                <input type="text" class="form-control" id="tag" name="tag" placeholder="Tag">
                <!--input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"-->
              </div>
            
              <button type="submit" class="btn btn-default">保存</button>
            </form>
        </div>
    </div>
  
    

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery-3.1.1.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/epiceditor.js"></script>
    <script>
        var opts = {
          container: 'epiceditor',
          textarea: null,
          basePath: '',
          clientSideStorage: true,
          localStorageName: 'epiceditor',
          useNativeFullscreen: true,
          parser: marked,
          file: {
            name: 'epiceditor',
            defaultContent: '',
            autoSave: 10000
          },
          theme: {
            base: './themes/base/epiceditor.css',
            preview: './themes/preview/github.css',
            editor: './themes/editor/epic-dark.css'
          },
          button: {
            preview: true,
            fullscreen: true,
            bar: "auto"
          },
          focusOnLoad: false,
          shortcut: {
            modifier: 118,
            fullscreen: 70,
            preview: 80
          },
          string: {
            togglePreview: 'Toggle Preview Mode',
            toggleEdit: 'Toggle Edit Mode',
            toggleFullscreen: 'Enter Fullscreen'
          },
          autogrow: true
        }
        var editor = new EpicEditor(opts).load();
        //var editor = new EpicEditor({basePath: '/static/lib/epiceditor'}).load();
   
    
    </script>
    <script type="text/javascript">
		$(document).ready(function(){
	    $("form").submit(function(e){
	    //e.preventDefault();
	    editor.save();
	    var theContent = editor.exportFile();
	    alert("Submit prevented :" + theContent);
	    $("#content").val(theContent);
	    //alert("Submit prevented :" + theContent);
		/*   saveToServerAjaxCall('/save', {data:theContent}, function () {
		    console.log('Data was saved to the database.');
		  }); */
	    
	  });
	});
	</script>
</body>
</html>