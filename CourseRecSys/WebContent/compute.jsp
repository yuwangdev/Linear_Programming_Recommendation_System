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

<title>Course Recommendations</title>

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
<%@page import="Computing.*"%>



<%
	/* 	String name = session.getAttribute("name").toString();
		String email = session.getAttribute("email").toString();
		String id = session.getAttribute("id").toString();
		String star = (new Dashboard()).getStartingSemester(session.getAttribute("starting").toString());
		String spe = (new Dashboard()).getSpecialty(session.getAttribute("specialty").toString()); */

	OpenCourseList o = new OpenCourseList();
	o.dig();
	ArrayList<Integer> num = o.getNum();

	ArrayList<String> cid = o.getCid();
	ArrayList<String> funda = o.getFunda();
	ArrayList<String> prof = o.getProf();
	ArrayList<String> ta = o.getTa();
	ArrayList<String> cname = o.getName();
	ArrayList<Integer> cd = o.getCd();
	ArrayList<Integer> ps = o.getPs();
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
					<h1 class="page-header">Submit My Preference</h1>

					<h>Enter the course ID that you plan to take with specific
					priporities. Number 3, 2, 1, 0 represent Urgent, High, Normal and
					Low priority, respectively. </h>
					<hr>
					<table>
						<tr>
							<td>Course ID</td>
							<td>Priority</td>
						</tr>
						<form role="form" action="computeResult.jsp" method="POST">
							<tr>
								<td><input class="form-control" placeholder="Course ID"
									name="id1" type="number" autofocus></td>
								<td><input class="form-control" placeholder="Prioirity"
									name="p1" type="number" autofocus></td>
							</tr>
							<tr>
								<td><input class="form-control" placeholder="Course ID"
									name="id2" type="number" autofocus></td>
								<td><input class="form-control" placeholder="Prioirity"
									name="p2" type="number" autofocus></td>
							</tr>
							<tr>
								<td><input class="form-control" placeholder="Course ID"
									name="id3" type="number" autofocus></td>
								<td><input class="form-control" placeholder="Prioirity"
									name="p3" type="number" autofocus></td>
							</tr>
							<tr>
								<td><input class="form-control" placeholder="Course ID"
									name="id4" type="number" autofocus></td>
								<td><input class="form-control" placeholder="Prioirity"
									name="p4" type="number" autofocus></td>
							</tr>
					</table>
					<br>

					<button type="submit" class="btn btn-primary">Submit for
						Computing</button>

					</form>
					<hr>



					<table class="table">
						<p>Available courses for the upcoming semester:</p>
						<tbody>
						<thead>
							<tr>

								<th>ID</th>
								<th>Abbreviation</th>
								<th>Name</th>
								<th>Fundamental</th>
								<th>Instructor</th>
								<th>T.A.</th>
								<th>Current Demand</th>
								<th>Preset Capacity</th>


							</tr>
						</thead>





						<%
							//String t = "<input list='prio' name='prio'><datalist id='prio'><option value='3'></option><option value='2'></option><option value='1'></option><option value='0'></option></datalist>";
							for (int i = 0; i < num.size(); i++) {
								out.print("<tr>");
								//out.print("<td> <input type='checkbox' name='pref' value=" + num.get(i).toString() + "></td>");
								//out.print("<td>" + t + "</td>");
								out.print("<td>" + num.get(i).toString() + "</td>");
								out.print("<td>" + cid.get(i).toString().toUpperCase() + "</td>");
								out.print("<td>" + cname.get(i).toString() + "</td>");
								out.print("<td>" + funda.get(i).toString() + "</td>");
								out.print("<td>" + prof.get(i).toString() + "</td>");
								out.print("<td>" + ta.get(i).toString() + "</td>");
								out.print("<td>" + cd.get(i).toString() + "</td>");
								out.print("<td>" + ps.get(i).toString() + "</td>");
								out.print("</tr>");

							}
						%>

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
