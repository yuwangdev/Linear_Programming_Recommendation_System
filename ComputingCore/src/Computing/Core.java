/**
 * 
 */
package Computing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author yuwang
 * 
 *         Core class for computing
 *
 */
public class Core {

	private boolean isAdminMode;
	private int studentId;
	private int semesterId;

	private int rawCourseCapacityByComputed;
	// the value directly from gurobi computing

	private ArrayList<String> finalRecLists;
	private String mark;

	// Tested
	public Core(boolean isAdminMode) {
		super();
		this.isAdminMode = isAdminMode;

	}

	// Tested
	public Core(boolean isAdminMode, int stundetid, int semesterId) {
		super();
		this.isAdminMode = isAdminMode;
		this.studentId = stundetid;
		this.semesterId = semesterId;

	}

	// Tested
	public void loadThisStudentPreference(HashMap<String, Integer> coursePreference) {
		InputDataCache.coursePreference = coursePreference;
		InputDataCache.timeStamp = "'" + (new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz")).format(new Date())
				+ "'";
		DbHelperForCore.putThisCoursePreferenceIntoDb(this.studentId, coursePreference);
		DbHelperForCore.AddCoursePreferenceIntoDbOnDemand(coursePreference);

	}

	// Tested
	public void initialize() {
		// put every value into temp data cache
		InputDataCache.openCourseList = DbHelperForCore.loadOpenCourseListFromDb();
		InputDataCache.entirePreference = DbHelperForCore.loadEntirePreferenceListFromDb();
		InputDataCache.maxCourseNum = DbHelperForCore.calcualteMaxCourseNumber(this.studentId, this.semesterId);
		InputDataCache.totalPreferedStudent = DbHelperForCore.calcualteTotalPreferedStudentNumber();
		InputDataCache.courseNumberLableMapping = DbHelperForCore.makeCourseLabelNumberMapping();
		InputDataCache.semesterId = this.semesterId;
		InputDataCache.totalNumSemester = 18;
		InputDataCache.convertedPrefCourseList = DbHelperForCore.convertToArrayList(InputDataCache.entirePreference);
		InputDataCache.enumFinalPrefCourseList = DbHelperForCore.getEnumFinalPrefCourseList(
				InputDataCache.convertedPrefCourseList, InputDataCache.courseNumberLableMapping);

	}

	public void solve() throws Exception {
		Compute compute = new Compute();
		compute.computing();
		rawCourseCapacityByComputed = OutputDataCache.rawCourseCapacityByComputed;

	}

	// Tested
	public void postTreatData() {
		// treat the suggested course lists as per suggested capacity and real
		// situation

		if (!isAdminMode) {
			postTreatment pt = new postTreatment(InputDataCache.timeStamp, this.studentId,
					this.rawCourseCapacityByComputed, InputDataCache.coursePreference);
			pt.doPostTreat();
			pt.putRecListIntoDb();
			pt.putRecSizeIntoDb();
			finalRecLists = OutputDataCache.finalRecLists;
			mark = OutputDataCache.finalMark;

		}

	}

	// Tested
	public int getRawCourseCapacityByComputed() {
		return DbHelperForCore.getRD();
	}

	// Tested
	public ArrayList<String> getFinalRecLists() {
		return finalRecLists;
	}

	// Tested
	public String getMark() {
		return mark;
	}

}
