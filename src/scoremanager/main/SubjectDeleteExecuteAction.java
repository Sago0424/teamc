// SubjectDeleteExecuteAction.java
package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // フォームから送信された科目コードを取得
        String subjectCd = request.getParameter("cd");

        if (subjectCd != null && !subjectCd.isEmpty()) {
            // 科目情報を削除する処理を行う
            SubjectDao subjectDao = new SubjectDao();
            boolean success = false;
            try {
                // 科目が存在する場合の削除を試行
                success = subjectDao.delete(subjectCd);
            } catch (Exception e) {
                e.printStackTrace();
                // エラーが発生した場合は失敗扱いとする
                success = false;
            }

            if (success) {
                // 削除成功時の処理
                response.sendRedirect("subject_delete_done.jsp"); // 削除後の画面にリダイレクト
            } else {
                // 削除失敗時の処理
                response.sendRedirect("subject_delete.jsp?error=failed"); // 失敗した場合は元のページに戻す
            }
        } else {
            // エラー処理（科目コードが取得できない場合）
            response.sendRedirect("error_page.jsp"); // エラーページにリダイレクト
        }
    }
}
