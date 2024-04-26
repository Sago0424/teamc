package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// セッションから現在のユーザー（教員）を取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String entYearStr="";// 入力された入学年度
		String classNum = "";//入力されたクラス番号
		String subjectStr="";//入力された科目
		String numStr="";//入力された回数
		int entYear = 0;// 入学年度
		List<Test> tests = null;//Testリスト
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		TestDao tDao = new TestDao();//TestDao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		SubjectDao bDao = new SubjectDao();//SubjectDao

		//リクエストパラメーターの取得
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		subjectStr = request.getParameter("f3");
		numStr = request.getParameter("f4");

		//DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		// 入学年度が送信されていた場合
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		if (entYear != 0 && !classNum.equals("0")) {
			// 入学年度とクラス番号を指定
			tests = tDao.filter(entYear, classNum);
		} else if (entYear != 0 && classNum.equals("0")) {
			// 入学年度のみ指定
			tests = tDao.filter(teacher.getSchool(), entYear);
		} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			// 指定なしの場合
			// 全学生情報を取得
			tests = tDao.filter(teacher.getSchool());
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			request.setAttribute("errors", errors);
			// 全学生情報を取得
			tests = tDao.filter(teacher.getSchool());
		}

		// TestDaoオブジェクトを作成
		TestDao testDao = new TestDao();

		try {
			// 全テスト情報を取得
			List<Test> test = testDao.getAllTests();

			// テスト情報をリクエスト属性にセット
			request.setAttribute("tests", test);

			// test_regist.jspにフォワード
			request.getRequestDispatcher("test_regist.jsp").forward(request, response);
		} catch (Exception e) {
			// エラーが発生した場合の処理を記述
			e.printStackTrace();
			// エラーページにリダイレクトまたは他の適切な処理を行う
		}
	}
}