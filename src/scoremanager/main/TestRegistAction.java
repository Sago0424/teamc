package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session = req.getSession();
	    Teacher teacher = (Teacher) session.getAttribute("user");
	    String entYearStr = "";
	    String classNum = "";
	    String subjects = "";
	    String number = "";
	    int entYear = 0;
	    boolean isAttend = false;
	    List<Student> students = null;
	    LocalDate todaysDate = LocalDate.now();
	    int year = todaysDate.getYear();
	    StudentDao sDao = new StudentDao();
	    ClassNumDao cNumDao = new ClassNumDao();
	    SubjectDao subjectDao = new SubjectDao();
	    TestDao testDao = new TestDao();
	    Map<String, String> errors = new HashMap<>();
	    String subName = ""; // 科目名を取得するロジックを実装
        String testNumber = ""; // 回数を取得するロジックを実装



	    entYearStr = req.getParameter("f1");
	    classNum = req.getParameter("f2");
	    subjects = req.getParameter("f3");
	    number = req.getParameter("f4");

	    if (entYearStr != null) {
	        entYear = Integer.parseInt(entYearStr);
	    }

	    if (entYear != 0 && !classNum.equals("0")) {
	        students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
	    } else if (entYear != 0 && classNum.equals("0")) {
	        students = sDao.filter(teacher.getSchool(), entYear, isAttend);
	    } else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
	        students = sDao.filter(teacher.getSchool(), isAttend);
	    } else {
	        errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
	        req.setAttribute("errors", errors);
	        students = sDao.filter(teacher.getSchool(), isAttend);
	    }

	    List<Integer> entYearSet = new ArrayList<>();
	    for (int i = year - 10; i < year + 1; i++) {
	        entYearSet.add(i);
	    }

	    req.setAttribute("f1", entYear);
	    req.setAttribute("f2", classNum);
	    req.setAttribute("f3", subjects);
	    req.setAttribute("f4", number);


	    req.setAttribute("students", students);
	    req.setAttribute("class_num_set", cNumDao.filter(teacher.getSchool()));
	    req.setAttribute("ent_year_set", entYearSet);
	    // 科目情報をセット
	    req.setAttribute("subjects_set", subjectDao.getAllSubjects()); // すべての科目を取得してセット
	    req.setAttribute("number_set",testDao.setAllno());

	    req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	    req.setAttribute("subName", subName);
        req.setAttribute("testNumber", testNumber);
	}

}