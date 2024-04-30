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
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao; // 科目Daoのインポートを追加
import dao.TestDao; // TestDaoのインポートを追加
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ローカル変数の宣言
		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー
		String entYearStr = ""; // 入力された入学年度
		String classNum = ""; // 入力されたクラス番号
		String isAttendStr = ""; // 入力された在学フラグ
		int entYear = 0; // 入学年度
		boolean isAttend = false; // 在学フラグ
		List<Student> students = null; // 学生リスト
		LocalDate todaysDate = LocalDate.now(); // LcalDateインスタンスを取得
		int year = todaysDate.getYear(); // 現在の年を取得
		StudentDao sDao = new StudentDao(); // 学生Dao
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao(); // 科目Daoを初期化
		TestDao testDao = new TestDao(); // TestDaoを初期化
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		// リクエストパラメータ―の取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");

		// 在学フラグが送信されていた場合
		if (isAttendStr != null) {
			// 在学フラグを立てる
			isAttend = true;
			// リクエストに在学フラグをセット
			req.setAttribute("f3", isAttendStr);
		}

		// 科目と回数の情報を取得
		List<Subject> subjects = subDao.getAllSubjects(); // 科目のリストを取得
		List<Test> rounds = subDao.getAllRounds(); // 回数のリストを取得

		// DBからデータ取得
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		// 入学年度が送信されていた場合
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}

		if (entYear != 0 && !classNum.equals("0")) {
			// 入学年度とクラス番号を指定
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			// 入学年度のみ指定
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			// 指定なしの場合
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			req.setAttribute("errors", errors);
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}

		// ビジネスロジック
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// DBへデータ保存
		// なし

		// レスポンス値をセット
		// リクエストに入学年度をセット
		req.setAttribute("f1", entYear);
		// リクエストにクラス番号をセット
		req.setAttribute("f2", classNum);
		// リクエストに科目と回数の情報をセット
		req.setAttribute("subjects", subjects);
		req.setAttribute("rounds", rounds);

		// リクエストに学生リストをセット
		req.setAttribute("students", students);
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		// JSPへフォワード
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}
