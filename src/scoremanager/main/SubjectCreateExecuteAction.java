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

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信されたデータを取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // セッションからログインユーザー（教員）を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user"); // キャストを追加

        // Subjectオブジェクトを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        // 科目コードが既に存在するかチェック
        SubjectDao subjectDao = new SubjectDao();
        boolean exists = false;
        try {
            exists = subjectDao.exists(cd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (exists) {
            // 科目コードが既に存在する場合はエラーメッセージをセットして元のページに戻る
            request.setAttribute("errorMessage", "科目コードが重複しています。");
            request.setAttribute("cd", cd); // 科目コードを再度入力させるためにパラメータを設定
            request.setAttribute("name", name); // 科目名を再度入力させるためにパラメータを設定
            request.getRequestDispatcher("SubjectCreate.action").forward(request, response);
            return;
        }

        // 科目コードが存在しない場合は登録を試みる
        boolean success = false;
        try {
            success = subjectDao.save(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (success) {
            // 登録成功時の処理
            response.sendRedirect("subject_create_done.jsp"); // 登録後の画面にリダイレクト
        } else {
            // 登録失敗時の処理
            response.sendRedirect("subject_create.jsp?error=failed"); // 失敗した場合は元のページに戻す
        }
    }
}