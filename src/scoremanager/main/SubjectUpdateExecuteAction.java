package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String cd = request.getParameter("cd"); // 科目コードのパラメータ名を修正
        String name = request.getParameter("name");

        // セッションからログインユーザー（教員）を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user"); // キャストを追加

        // Subjectオブジェクトを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool()); // ユーザーの所属学校をセット

        // SubjectDaoを使用してデータベースに教科情報を更新
        SubjectDao subjectDao = new SubjectDao();
        boolean success = false;
        try {
            // データベースにSubjectオブジェクトを保存
            success = subjectDao.update(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 更新成功または失敗に応じてリダイレクト
        if (success) {
            // 更新成功時の処理
            response.sendRedirect("subject_update_done.jsp");
        } else {
            // 更新失敗時の処理
            response.sendRedirect("subject_update.jsp?error=failed");
        }
    }
}
