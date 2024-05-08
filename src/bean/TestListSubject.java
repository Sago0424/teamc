package bean;

import java.io.Serializable;

public class TestListSubject implements Serializable {
	private int entYear;
	private String classNum;
	private String no;
	private String name;
	private int point1;
	private int point2;
	private School school;
	private String subjectname;

	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint1() {
	    return point1;
	}

	public void setPoint1(int point1) {
	    this.point1 = point1;
	}

	public int getPoint2() {
	    return point2;
	}

	public void setPoint2(int point2) {
	    this.point2 = point2;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public void putPoint(int no, int point) {
	    if (no == 1) {
	        this.point1 = point;
	    } else if (no == 2) {
	        this.point2 = point;
	    }
	}
}