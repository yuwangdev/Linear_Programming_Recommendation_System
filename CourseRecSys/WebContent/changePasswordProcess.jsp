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
		String dbpath = "jdbc:postgresql://localhost:5432/project4";
			String user = "postgres";
			String password = "admin";
			String id = session.getAttribute("id").toString();

			if (request.getParameter("newpsw") != null && !request.getParameter("newpsw").isEmpty()) {
		String newpsw = "'" + request.getParameter("newpsw") + "'";

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			stmt.executeUpdate("UPDATE public.student SET password=" + newpsw + " WHERE student.id=" + "'" + id
					+ "'" + ";");

			stmt.close();
			c.close();

			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
			}
			response.sendRedirect("dashboard.jsp");
	%>


</body>
</html>