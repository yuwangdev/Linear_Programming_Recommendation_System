<%@page import="com.sun.org.apache.bcel.internal.generic.GETSTATIC"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<%@ page language="java" import="java.sql.*" errorPage=""%>

	<%
		final String ADMIN_USER = "0001";
		final String ADMIN_PASSWORD = "admin123";
		String dbpath = "jdbc:postgresql://localhost:5432/project4";
		String user = "postgres";
		String password = "admin";
		String id = request.getParameter("id");
		String psw = request.getParameter("password");
		//String psw = "psw0";
		//String id = "260510650";
		String correctPs = "";

		String name = "";
		String email = "";

		String starting = "";
		String specialty = "";

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			String q = "SELECT student.id, student.password, student.name, student.starting, student.specialty, student.email FROM public.student WHERE student.id="
					+ id + ";";
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(q);
			while (rs.next()) {
				correctPs = rs.getString("password");
				name = rs.getString("name");
				starting = rs.getString("starting");
				email = rs.getString("email");
				id = rs.getString("id");
				specialty = rs.getString("specialty");

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		if (psw.equals(ADMIN_PASSWORD) && id.equals(ADMIN_USER)) {

			response.sendRedirect("dashboardAdmin.jsp");
		} else {
			if (psw.equals(correctPs)) {
				session.setAttribute("name", name);
				session.setAttribute("email", email);
				session.setAttribute("id", id);
				session.setAttribute("starting", starting);
				session.setAttribute("specialty", specialty);
				/* out.print("Yes"); */
				response.sendRedirect("dashboard.jsp");

			} else {
				response.sendRedirect("InvalidLogin.jsp");
				//out.print("No");

			}

		}
	%>


</body>
</html>