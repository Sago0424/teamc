// StudentCreateExecuteAction.java

package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoを初期化
        // フォームから送信されたデータを取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearParam = request.getParameter("f1"); // 入学年度のパラメータ名を"f1"に変更
        int entYear = 0; // デフォルト値を設定
        HttpSession session = request.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー

        if (entYearParam != null && !entYearParam.isEmpty()) {
            // entYearパラメータがnullでない場合、および空でない場合にのみ解析を試みる
            entYear = Integer.parseInt(entYearParam);
        }

        String classNum = request.getParameter("f2"); // クラス番号のパラメータ名を"f2"に変更
        boolean isAttend = true;
        // Studentオブジェクトを作成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool());

        // 学生番号が既に存在するかチェック
        StudentDao studentDao = new StudentDao();
        boolean exists = false;
        try {
            exists = studentDao.exists(no);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exists) {
            // 学生番号が既に存在する場合はエラーメッセージを返す
            response.sendRedirect("StudentCreate.jsp?error=exists");
            return;
        }

        // 学生番号が存在しない場合は登録を試みる
        boolean success = false;
        try {
            if (entYear != 0) {
                success = studentDao.save(student);
            } else {
                request.getRequestDispatcher("StudentCreate.action").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (success) {
            // 登録成功時の処理
            response.sendRedirect("StudentCreate_done.jsp"); // 登録後の画面にリダイレクト
        } else {
            // 登録失敗時の処理
            response.sendRedirect("StudentCreate.jsp?error=failed"); // 失敗した場合は元のページに戻す
        }
    }
}
