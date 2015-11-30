<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Change Passwords</title>

<!-- Bootstrap Core CSS -->
<link
	href="resource/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="resource/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="resource/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="resource/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="resource/bower_components/morrisjs/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="resource/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


</head>

<%@page import="Servelet.*"%>
<%@page import="java.util.ArrayList "%>

<%
	String name = session.getAttribute("name").toString();
	String email = session.getAttribute("email").toString();
	String id = session.getAttribute("id").toString();
	String star = (new Dashboard()).getStartingSemester(session.getAttribute("starting").toString());
	String spe = (new Dashboard()).getSpecialty(session.getAttribute("specialty").toString());
%>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">Course Recommendations:
				<%=session.getAttribute("name")%>
			</a>
		</div>
		<!-- /.navbar-header -->

		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="#">
							<div>
								<strong><a href="mailto:admin@project4course.edu">Send
										email to Admin</a></strong> <span class="pull-right text-muted"> </span>
					</a></li>
					<li class="divider"></li>
				</ul> <!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">

					<li><a href="logout.jsp"><i class="fa fa-sign-out fa-fw"></i>
							Logout</a>
					<li><a href="setting.jsp"><i class="fa fa-sign-out fa-fw"></i>
							Change Password</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<li><a href="dashboard.jsp"><i
							class="fa fa-dashboard fa-fw"></i> Quick Facts Dashboard </a></li>
					<li><a href="compute.jsp"><i class="fa fa-dashboard fa-fw"></i>
							Compute Recommendations </a></li>
					<li><a href="history.jsp"><i class="fa fa-dashboard fa-fw"></i>
							View Historical Records </a></li>

				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Change Password</h1>


					<table class="table">
						<tbody>
						<thead>
							<tr>
								<th>Student ID</th>
								<th>Student Name</th>

								<th>New Password</th>


							</tr>
						</thead>
						<tr>
							<form action="changePasswordProcess.jsp" method="POST">
								<%
									out.print("<td>" + id.toString() + "</td>");
									out.print("<td>" + name.toString() + "</td>");
									out.print("<td>" + "<input type='text' name='newpsw' value='New Pin'>" + "</td>");
									out.print("<tr><td>" + "<input type='submit' value='Submit'>" + "</td></tr>");
								%>
								<hr>

							</form>

						</tr>
						</tbody>
					</table>

					<hr>










				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->



		</div>
		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="resource/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="resource/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="resource/bower_components/metisMenu/dist/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="resource/bower_components/raphael/raphael-min.js"></script>
	<script src="resource/bower_components/morrisjs/morris.min.js"></script>
	<script src="resource/js/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="resource/dist/js/sb-admin-2.js"></script>

</body>

</html>
