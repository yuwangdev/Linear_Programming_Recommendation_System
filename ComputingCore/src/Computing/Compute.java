/**
 * 
 */
package Computing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;

/**
 * @author yuwang Core function of gurobi computing The Computing part of the
 *         backend recommendations
 *
 */
public class Compute {

	// use the db class always, easy for maintainence
	static String dbpath = (new DatabaseConnection()).getDbpath();
	static String user = (new DatabaseConnection()).getUser();
	static String password = (new DatabaseConnection()).getPassword();

	public void computing() throws Exception {

		int num_of_students = InputDataCache.totalPreferedStudent; // 1239
		int num_of_courses = InputDataCache.openCourseList.size(); // 36
		int num_of_max_course = InputDataCache.maxCourseNum; // 3
		int num_of_semesters = InputDataCache.totalNumSemester; // 18 // Hard
																// coded
		int total_number_courses = 63;

		ArrayList<ArrayList<Integer>> loadedStudentCourse = InputDataCache.enumFinalPrefCourseList;
		// temp var for development

		ArrayList<Integer> c = new ArrayList<>();
		// subset of temp loadedStudentCourses

		if (num_of_students == 0) {
			throw new Exception("Student total number is 0");
		}

		GRBEnv env = new GRBEnv();
		env.set(GRB.IntParam.LogToConsole, 0);
		// no output to console
		env.set(GRB.IntParam.OutputFlag, 0);
		// no output to file

		GRBModel model = new GRBModel(env);

		GRBVar[][] vars = new GRBVar[num_of_students + 1][total_number_courses + 1];

		// create vars
		for (int i = 1; i <= num_of_students; i++) {
			for (int j = 1; j <= total_number_courses; j++) {

				vars[i][j] = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "Y_" + i + "_" + j);

			}
		}

		GRBVar x = model.addVar(0.0, num_of_students, 1.0, GRB.INTEGER, "x");
		model.update();

		GRBLinExpr expr = new GRBLinExpr();
		expr.addTerm(1.0, x);
		model.setObjective(expr, GRB.MINIMIZE);

		// each course can only be taken once
		for (int i = 1; i <= num_of_students; i++) {
			c = loadedStudentCourse.get(i - 1); // the specific preference of a
												// student
			HashSet<Integer> uq = new HashSet<>(c);
			if (uq.size() == c.size()) {
				for (int m = 1; m <= uq.size(); m++) {
					int j = c.get(m - 1); // course number
					expr = new GRBLinExpr();

					expr.addTerm(1.0, vars[i][j]);

					model.addConstr(expr, GRB.LESS_EQUAL, x, "c1" + "_" + i + "_" + j);

				}
			}

		}

		// the course max limitation for each student as per the policy
		for (int i = 1; i <= num_of_students; i++) {
			c = loadedStudentCourse.get(i - 1);
			HashSet<Integer> uq = new HashSet<>(c);
			if (uq.size() == c.size()) {

				expr = new GRBLinExpr();
				for (int m = 1; m <= c.size(); m++) {
					int j = c.get(m - 1);
					expr.addTerm(1.0, vars[i][j]);

					model.addConstr(expr, GRB.LESS_EQUAL, num_of_max_course, "c2" + "_" + i);

				}

			}

		}

		// open course == 1, close course == 0
		for (int j = 1; j <= num_of_courses; j++) {
			int open = chechOpen(j);
			if (open == 0) {
				expr = new GRBLinExpr();
				for (int i = 1; i <= num_of_students; i++) {
					expr.addTerm(1.0, vars[i][j]);
				}
				model.addConstr(expr, GRB.EQUAL, 0, "c3" + "_" + j);

			}

		}

		// object value x will be always less than total student number, or the
		// stuednt that taken course number
		for (int j = 1; j <= num_of_courses; j++) {
			expr = new GRBLinExpr();
			for (int i = 1; i <= num_of_students; i++) {
				expr.addTerm(1.0, vars[i][j]);
			}
			model.addConstr(expr, GRB.LESS_EQUAL, x, "c4" + "_" + j);

		}

		model.optimize();
		model.write("model.lp");
		OutputDataCache.rawCourseCapacityByComputed = (int) model.get(GRB.DoubleAttr.ObjVal);

	}

	// helper function to check if the course is open
	private int chechOpen(int j) {
		int result = 0;

		ArrayList<Integer> temp = new ArrayList<>();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbpath, user, password);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT course.num FROM public.course WHERE course.isopen=1;");
			while (rs.next()) {
				temp.add(rs.getInt("num"));

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		if (temp.contains(j)) {
			result = 1;
		}

		return result;

	}

}
