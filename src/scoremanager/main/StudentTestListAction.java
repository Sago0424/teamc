package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;


public class StudentTestListAction extends Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String studentno = "";
		LocalDate todaysDate = LocalDate.now();//LcalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao();
		List<Subject> subjects = null;//科目のリスト（空
		Student student = new Student();
		TestListStudentDao testStuDao = new TestListStudentDao();
		StudentDao sDao = new StudentDao();

		studentno = request.getParameter("f4");

		//DBからデータ取得３

		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> clist = cNumDao.filter(teacher.getSchool());

		//ログインユーザーの学校コードを基に科目の一覧を取得
		subjects = subDao.filter(teacher.getSchool());

		//リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		//10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}

		student.setNo(studentno);
		List<TestListStudent> stulist = testStuDao.filter(student);
		Student students = sDao.get(studentno);

		//レスポンス値をセット６
		request.setAttribute("stulist", stulist);
		request.setAttribute("student", students);
		request.setAttribute("f4", studentno);
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", clist);
		request.setAttribute("subject_set", subjects);

		//JSPへフォワード７
		request.getRequestDispatcher("test_list_student.jsp").forward(request, response);

	}
}