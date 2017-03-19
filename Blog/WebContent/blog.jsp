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
	%>
</head>

  <%
      Blog blog=(Blog)request.getAttribute("blog");
   %>
<body>
    <div class="blog-masthead">
      <div class="container">
        <nav class="blog-nav">
          <a class="blog-nav-item active" href="<%=basePath %>home.do">Home</a>
       
          <a class="blog-nav-item" href="#">About</a>
        </nav>
      </div>
    </div>

    <div class="container">

      <div class="blog-header">
       		<h1 class="blog-title">柳絮随风的个人博客</h1>
			<p class="lead blog-description">个人博客</p>
      </div>

      <div class="row">

        <div class="col-sm-8 blog-main">
		 
          <div class="blog-post">
            <h2 class="blog-post-title"> 
            	<%=blog.getTitle() %>
            </h2>
            <p class="blog-post-meta">
            	<%=blog.getPublishTime() %>
            </p>
           	 <div id="epiceditor"></div>
             <input type="hidden" class="form-control" id="content" name="content" value="<%=blog.getContent()%>">

          </div><!-- /.blog-post -->

       

          <nav>
            <ul class="pager">
              <li><a href="#">Previous</a></li>
              <li><a href="#">Next</a></li>
            </ul>
          </nav>

        </div><!-- /.blog-main -->

        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
          <div class="sidebar-module sidebar-module-inset">
            <h4>About</h4>
            <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
          </div>
          <div class="sidebar-module">
            <h4>Archives</h4>
            <ol class="list-unstyled">
              <li><a href="#">十一月  2016</a></li>
              <li><a href="#">十二月  2016</a></li>
              <li><a href="#">一月 2017</a></li>
              <li><a href="#">二月 2017</a></li>
              <li><a href="#">三月 2017</a></li>
          
            </ol>
          </div>
          <div class="sidebar-module">
            <h4>Elsewhere</h4>
            <ol class="list-unstyled">
              <li><a href="#">GitHub</a></li>
              <li><a href="#">Twitter</a></li>
              <li><a href="#">Facebook</a></li>
            </ol>
          </div>
        </div><!-- /.blog-sidebar -->

      </div><!-- /.row -->

    </div><!-- /.container -->

   <!--  <footer class="blog-footer">
      <p>Blog template built for <a href="http://getbootstrap.com">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
      <p>
        <a href="#">Back to top</a>
      </p>
    </footer> -->


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
            autoSave: false
          },
          theme: {
            base: './themes/base/epiceditor.css',
            preview: './themes/preview/github.css',
            editor: './themes/editor/epic-dark.css'
          },
          button: {
            preview: true,
            fullscreen: false,
            bar: "hide"
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
        var content = $("#content").val();
        editor.importFile("<%=blog.getTitle() %>", content);
        editor.open("<%=blog.getTitle() %>");
  
        editor.preview();
        //var editor = new EpicEditor({basePath: '/static/lib/epiceditor'}).load();
   
    
    </script>
  </body>

</html>