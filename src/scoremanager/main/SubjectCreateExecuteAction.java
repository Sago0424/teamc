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

        // 科目コードが3文字でない場合、エラーメッセージを表示して元のページに戻す
        //if (cd.length() != 3) {
          //  response.sendRedirect("subject_create.jsp?error=invalid_cd");
            //return;
        //}

        // Subjectオブジェクトを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());



        // SubjectDaoを使用して新しい科目をデータベースに登録
        SubjectDao subjectDao = new SubjectDao();
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
