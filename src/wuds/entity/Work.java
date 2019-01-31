package wuds.entity;


/**作业类<BR/>
 * 属性包括作业课程名、实验号、班级、截止时间、提交数量
 * @author liulei
 *
 */
public class Work {
	private String course;
	private String expNo;
	private String grade;
	private long endTime;
	private int count;
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getExpNo() {
		return expNo;
	}
	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
