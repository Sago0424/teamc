package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ここに科目登録の処理を記述します

        // subject_create.jspにフォワード
        request.getRequestDispatcher("subject_create.jsp").forward(request, response);
    }
}
