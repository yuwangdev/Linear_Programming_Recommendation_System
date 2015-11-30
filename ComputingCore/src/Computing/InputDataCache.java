/**
 * 
 */
package Computing;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yuwang
 * 
 *         Input data cache for gurobi computing part
 *
 */
public class InputDataCache {
	public static HashMap<String, Integer> coursePreference = new HashMap<>();
	public static ArrayList<String> openCourseList = new ArrayList<>();
	public static HashMap<Integer, String[]> entirePreference = new HashMap<>();
	public static int maxCourseNum;
	public static int totalPreferedStudent;
	public static HashMap<String, Integer> courseNumberLableMapping = new HashMap<>();
	public static int semesterId;
	public static String timeStamp;
	public static int totalNumSemester;
	public static ArrayList<ArrayList<String>> convertedPrefCourseList;
	public static ArrayList<ArrayList<Integer>> enumFinalPrefCourseList;

}
