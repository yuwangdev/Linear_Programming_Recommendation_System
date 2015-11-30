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

<title>Course Recommendation System</title>


<link
	href="resource/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link href="resource/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<link href="resource/dist/css/sb-admin-2.css" rel="stylesheet">

<link
	href="resource/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Illegal Authentification </h3>
					</div>
					<div class="panel-body">
						<form role="form" action="loginProcess.jsp" , method="POST">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="Your Studeng ID"
										name="id" type="number" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Password"
										name="password" type="password" value="">
								</div>
								<button type="submit" class="btn btn-primary">Sign In</button>
								<button type="reset" class="btn btn-default">Reset</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="resource/bower_components/jquery/dist/jquery.min.js"></script>
	<script
		src="resource/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script src="resource/bower_components/metisMenu/dist/metisMenu.min.js"></script>
	<script src="resource/dist/js/sb-admin-2.js"></script>

</body>

</html>