package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearParam = request.getParameter("entYear"); // 入学年度のパラメータ名を"f1"に変更
        int entYear = 0; // デフォルト値を設定
        HttpSession session = request.getSession();//セッション
        Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

        if (entYearParam != null && !entYearParam.isEmpty()) {
            // entYearパラメータがnullでない場合、および空でない場合にのみ解析を試みる
            entYear = Integer.parseInt(entYearParam);
        }

        String classNum = request.getParameter("classNum"); // クラス番号のパラメータ名を"f2"に変更

        // チェックボックスの状態に応じてisAttendを設定
        boolean isAttend = request.getParameter("attend") != null; // チェックボックスがチェックされている場合はtrue

        // Studentオブジェクトを作成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool());

        // StudentDaoを使ってデータベースに挿入
        StudentDao studentDao = new StudentDao();
        boolean success = false;
        try {
            success = studentDao.save(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (success) {
            // 登録成功時の処理
        	response.sendRedirect("StudentUpdate_done.jsp");
; // 登録後の画面にリダイレクト
        } else {
            // 登録失敗時の処理
            response.sendRedirect("StudentUpdate.jsp?error=failed"); // 失敗した場合は元のページに戻す
        }
    }
}
