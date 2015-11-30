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
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="Computing.*"%>



<%
	String name = session.getAttribute("name").toString();
	String email = session.getAttribute("email").toString();
	String id = session.getAttribute("id").toString();
	String star = (new Dashboard()).getStartingSemester(session.getAttribute("starting").toString());
	String spe = (new Dashboard()).getSpecialty(session.getAttribute("specialty").toString());

	int stuId = Integer.parseInt(id);
	int semId = 3;

	HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

	if (request.getParameter("id1") != null && !request.getParameter("id1").isEmpty()
			&& request.getParameter("p1") != null && !request.getParameter("p1").isEmpty()) {
		hm.put(Integer.parseInt(request.getParameter("id1").toString()),
				Integer.parseInt(request.getParameter("p1").toString()));

	}

	if (request.getParameter("id2") != null && !request.getParameter("id2").isEmpty()
			&& request.getParameter("p2") != null && !request.getParameter("p2").isEmpty()) {
		hm.put(Integer.parseInt(request.getParameter("id2").toString()),
				Integer.parseInt(request.getParameter("p2").toString()));

	}

	if (request.getParameter("id3") != null && !request.getParameter("id3").isEmpty()
			&& request.getParameter("p3") != null && !request.getParameter("p3").isEmpty()) {
		hm.put(Integer.parseInt(request.getParameter("id3").toString()),
				Integer.parseInt(request.getParameter("p3").toString()));

	}

	if (request.getParameter("id4") != null && !request.getParameter("id4").isEmpty()
			&& request.getParameter("p4") != null && !request.getParameter("p4").isEmpty()) {
		hm.put(Integer.parseInt(request.getParameter("id4").toString()),
				Integer.parseInt(request.getParameter("p4").toString()));

	}

	//response.sendRedirect("InvalidLogin.jsp");
	HashMap<String, Integer> coursePreferenceWithPrioirity = new HashMap<String, Integer>();

	for (int key : hm.keySet()) {
		coursePreferenceWithPrioirity.put((new CourseServ()).getCourseAbbre(key), hm.get(key));
	}

	Core core = new Core(false, stuId, semId);
	core.loadThisStudentPreference(coursePreferenceWithPrioirity);
	core.initialize();

	try {
		core.solve();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	core.postTreatData();
	ArrayList<String> recList = core.getFinalRecLists();
	String mark = core.getMark();
	int cap = core.getRawCourseCapacityByComputed();
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
										email to Admin</a></strong> <span
									class="pull-right text-muted"> </span>
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
					<h1 class="page-header">View Recommendations</h1>


					<hr>
					<table class="table">
						<thead>
							<tr>
								<td><b>Preference</b></td>
								<td><b>Priority</b></td>
							</tr>
						</thead>
						<%
							CourseServ cs = new CourseServ();
							for (String course : coursePreferenceWithPrioirity.keySet()) {
								out.print("<tr>");

								out.print("<td>" + cs.getCourseFullName(course) + " (" + course.toUpperCase() + ")</td>");
								out.print("<td>" + cs.getTextPrio(coursePreferenceWithPrioirity.get(course)) + "</td>");

								out.print("</tr>");

							}
						%>
					</table>
					<br>

					<hr>
					<table class="table">
						<thead>
							<tr>
								<td><b>Recommendations</b></td>

							</tr>
						</thead>
						<%
							for (int i = 0; i < recList.size(); i++) {
								out.print("<tr>");

								out.print(
										"<td>" + cs.getCourseFullName(recList.get(i)) + " (" + recList.get(i).toUpperCase() + ")</td>");

								out.print("</tr>");

							}

							out.print("<tr><td><b>Mark: </b>" + mark + "</td></tr>");
						%>


					</table>
					<br>


					<button type="button" onclick="location.href = 'compute.jsp';"
						id="myButton" class="btn btn-success">Compute Again</button>






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