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

<title>Admin Mode</title>

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
				Admin Mode </a>
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
					<li><a href="dashboardAdmin.jsp"><i
							class="fa fa-dashboard fa-fw"></i> Admin Dashboard </a></li>



				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Enrollment Overview</h1>


					</table>





					<hr>

					<table class="table">
						<tbody>

							<tr>
								<td><b>Total Student:</b></td>
								<td><%=(new AdminDashboard()).getTotalStudent()%></td>
							</tr>

							<tr>
								<td><b>Total Course:</b></td>
								<td><%=(new AdminDashboard()).getTotalCourse()%></td>
							</tr>

							<tr>
								<td><b>Total Open Course for the Upcoming Semester:</b></td>
								<td><%=(new AdminDashboard()).getTotalOpenCourse()%></td>
							</tr>

							<tr>
								<td><b>Total Specialty:</b></td>
								<td><%=(new AdminDashboard()).getTotalSpecialty()%></td>
							</tr>

							<tr>
								<td><b>Total Computing Records:</b></td>
								<td><%=(new AdminDashboard()).getTotalComputing()%></td>
							</tr>
							<tr>
								<td><b>Course Seat Capacity Recommendation:</b></td>
								<td><%=(new AdminDashboard()).getLastRecCap()%></td>
							</tr>


						</tbody>
					</table>



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
