<%@ page language="java" contentType="text/html; charset=utf-8"%>

<%@ page import="com.wc.blog.bean.Blog"%>
<%@ page import="java.util.*"%>
<%@ page import="com.wc.blog.bean.PageModel"%>
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
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
</head>


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


				<%
					PageModel model = (PageModel) request.getAttribute("model");
					List<Blog> blogs = model.getList();

					for (int i = 0; i < blogs.size(); i++) {
						Blog blog = blogs.get(i);
				%>

				<div class="blog-post">
					<h2 class="blog-post-title">
					    <a href="<%=basePath %>show.do?id=<%=blog.getId()%>">
					    	<%=blog.getTitle()%>
					    </a>
						
					</h2>
					<p class="blog-post-meta">
						<%=blog.getPublishTime()%> by <a href="#">汪臣</a> 
					</p>
					
					<input type="hidden" class="form-control"
						id="content<%=blog.getId()%>" name="content"
						value="<%=blog.getContent()%>">
					<div id="epiceditor<%=blog.getId()%>" class="editor"></div>
				</div>
				<!-- /.blog-post -->
				<%
					}
				%>


				<!-- <nav>
					<ul class="pager">
						<li><a href="#">Previous</a></li>
						<li><a href="#">Next</a></li>
					</ul>
				</nav> -->
				
				 <nav aria-label="Page navigation">
        <ul class="pagination" id="pagination"></ul>
    </nav>

			</div>
			<!-- /.blog-main -->

			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="sidebar-module sidebar-module-inset">
					<h4>About</h4>
					<p>
						Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras
						mattis consectetur purus sit amet fermentum. Aenean lacinia
						bibendum nulla sed consectetur.
					</p>
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
			</div>
			<!-- /.blog-sidebar -->

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->

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
	<script src="js/jquery.twbsPagination.js"></script>
	<script>
		//var editor = new EpicEditor({basePath: '/static/lib/epiceditor'}).load();
		$(document).ready(function() {
			$(".editor").each(function(index, domEle) {
				//var content = $("#" + editor).val();
				var name = $(domEle).attr("id");
				//alert(name);
				var opts = {
					container : name,
					textarea : null,
					basePath : '',
					clientSideStorage : true,
					localStorageName : 'epiceditor',
					useNativeFullscreen : true,
					parser : marked,
					file : {
						name : 'epiceditor',
						defaultContent : '',
						autoSave : false
					},
					theme : {
						base : './themes/base/epiceditor.css',
						preview : './themes/preview/github.css',
						editor : './themes/editor/epic-dark.css'
					},
					button : {
						preview : true,
						fullscreen : false,
						bar : "hide"
					},
					focusOnLoad : false,
					shortcut : {
						modifier : 118,
						fullscreen : 70,
						preview : 80
					},
					string : {
						togglePreview : 'Toggle Preview Mode',
						toggleEdit : 'Toggle Edit Mode',
						toggleFullscreen : 'Enter Fullscreen'
					},
					autogrow : true
				}

				var editor = new EpicEditor(opts).load();
				var value = name.replace(/[^0-9]/ig, "");
				//alert(value);   
				var content = $("#content" + value).val();
				//alert(content);
				editor.importFile(value, content);
				editor.open(value);

				editor.preview();

			});
			
			
			   window.pagObj = $('#pagination').twbsPagination({
		            totalPages: <%=model.getTotalPages() %>,
		            visiblePages: 10,
		            startPage: <%=model.getPageNo() %>,
		            onPageClick: function (event, page) {
		                console.info(page + ' (from options)');
		            }
		        }).on('page', function (event, page) {
		            window.location.href="<%=basePath %>pages?id=" + page;   
		            console.info(page + ' (from event listening)');
		        });

		});

		function initEditor(elementName) {

			//var content = $("#" + editor).val();

			var editor = new EpicEditor({
				container : elementName
			}).load();
			/*   editor.importFile(editor, content);
			  editor.open(editor); */
		}
		function loadContent(editor, content) {
			var content = $("#" + editor).val();
			editor.importFile(editor, content);
			editor.open(editor);
		}
	</script>
</body>

</html>