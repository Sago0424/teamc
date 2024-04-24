package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // リクエストから教科コードを取得
        String subjectCd = request.getParameter("cd");

        // セッションから現在のユーザー（教員）を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 教科情報を取得するためのSubjectDaoオブジェクトを作成
        SubjectDao subjectDao = new SubjectDao();

        // 教科コードに基づいて教科情報を取得
        Subject subject = subjectDao.get(subjectCd);

        // JSPファイルに教科情報を渡すためにリクエスト属性にセット
        request.setAttribute("subject", subject);

        // subject_update.jspページにフォワード
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
