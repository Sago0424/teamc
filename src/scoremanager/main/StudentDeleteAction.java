package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;


public class StudentDeleteAction extends Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String studentNo = request.getParameter("no");
        HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

        // 学生情報を取得する処理
        StudentDao studentDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
List<String> list = cNumDao.filter(teacher.getSchool());
            Student student = studentDao.get(studentNo);
            request.setAttribute("student", student);
            request.setAttribute("class_num_set", list);
            request.getRequestDispatcher("StudentDelete.jsp").forward(request, response);



    }
}
