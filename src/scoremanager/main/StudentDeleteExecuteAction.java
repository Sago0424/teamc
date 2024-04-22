package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信された学生番号を取得
        String studentNo = request.getParameter("no");

        // 学生情報を削除する処理を行う
        StudentDao studentDao = new StudentDao();
        boolean success = false;
        try {
            // 学生オブジェクトを取得
            Student student = studentDao.get(studentNo);
            if (student != null) {
                // 学生が存在する場合のみ削除を試行
                success = studentDao.delete(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // エラーが発生した場合は失敗扱いとする
            success = false;
        }

        if (success) {
            // 削除成功時の処理
            response.sendRedirect("StudentDelete_done.jsp"); // 削除後の画面にリダイレクト
        } else {
            // 削除失敗時の処理
            response.sendRedirect("StudentDelete.jsp?error=failed"); // 失敗した場合は元のページに戻す
        }
    }
}
