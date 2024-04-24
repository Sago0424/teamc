// SubjectListAction.java

package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

            // 科目DAOを初期化
            SubjectDao subjectDao = new SubjectDao();

            // 全科目を取得
            List<Subject> subjects = subjectDao.getAllSubjects();

            // リクエストスコープにセット
            req.setAttribute("subjects", subjects);

            // 科目一覧のJSPにフォワード
            req.getRequestDispatcher("subject_list.jsp").forward(req, res);
        }
    }

