package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションから現在のユーザー（教員）を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // TestDaoオブジェクトを作成
        TestDao testDao = new TestDao();

        try {
            // 全テスト情報を取得
            List<Test> tests = testDao.getAllTests();

            // テスト情報をリクエスト属性にセット
            request.setAttribute("tests", tests);

            // test_regist.jspにフォワード
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
        } catch (Exception e) {
            // エラーが発生した場合の処理を記述
            e.printStackTrace();
            // エラーページにリダイレクトまたは他の適切な処理を行う
        }
    }
}
