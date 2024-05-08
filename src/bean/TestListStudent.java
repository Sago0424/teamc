package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {
	private String name;
	private String cd;
	private int no;
	private int point;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public void putPoint(int i, int int1) {
		// TODO 自動生成されたメソッド・スタブ
	}
}