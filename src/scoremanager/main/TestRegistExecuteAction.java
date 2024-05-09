package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action{

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String entYearStr = "";
        String classNum = "";
        int entYear = 0;
        String subjectCd = "";
        String numStr = "";
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        int num = 0;
        String studentNo = null;
        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        ClassNumDao classNumDao = new ClassNumDao();
        TestDao testDao = new TestDao();
        List<String> errors = new ArrayList<>();
        List<Test> tests = null;
        List<Student> students = null;
        int count = 0;
        int p = 0;
        boolean error = true;
        boolean flag = false;
        boolean testSave = false;

        entYearStr = request.getParameter("f1");
        classNum = request.getParameter("f2");
        subjectCd = request.getParameter("f3");
        System.out.println(subjectCd);
        numStr = request.getParameter("f5");
//        subuject = request.getParameter("f6");

        Subject subject = subjectDao.get(subjectCd, teacher.getSchool());

        if (numStr != null) {
            num = Integer.parseInt(numStr);
        }
        if (entYearStr != null) {
            entYear = Integer.parseInt(entYearStr);
        }

        tests = testDao.filter(teacher.getSchool(), entYear, classNum, subject, num);
        int size = tests.size();

        if (size != 0) {
            for (int i = 0; i < size; i++) {
                String pointStr = request.getParameter("point" + tests.get(i).getStudent().getNo());
                boolean isAttend = tests.get(i).getStudent().isAttend();
                if (!isAttend) {
                    continue;
                }
                if (pointStr != null && !pointStr.equals("")) {
                    p = Integer.parseInt(pointStr);
                } else {
                    p = 0;
                }
                if (p > 100 || p < 0) {
                    error = false;
                    studentNo = tests.get(i).getStudent().getNo();
                    break;
                }
                tests.get(i).setPoint(p);
                count++;
            }
        } else {
            students = studentDao.filter(teacher.getSchool(), entYear, classNum, true);
            int stusize = students.size();
            for (int i = 0; i < stusize; i++) {
                boolean isAttend = students.get(i).isAttend();
                if (!isAttend) {
                    continue;
                }
                Test test = new Test();
                String pointStr = request.getParameter("point" + students.get(i).getNo());
                if (pointStr != null && !pointStr.equals("")) {
                    p = Integer.parseInt(pointStr);
                } else {
                    p = 0;
                }
                if (p > 100 || p < 0) {
                    error = false;
                    studentNo = students.get(i).getNo();
                    break;
                }
                test.setSchool(teacher.getSchool());
                test.setClassNum(classNum);
                test.setNo(num);
                test.setPoint(p);
                test.setStudent(students.get(i));
                test.setSubject(subject);
                tests.add(test);
            }
        }

        if (error) {
            testSave = testDao.save(tests);
        } else {
            errors.add("0～100の範囲で入力してください");
            request.setAttribute("pointerrors", errors);
            if (size != 0) {
                request.setAttribute("tests", tests);
            } else {
                students = studentDao.filter(teacher.getSchool(), entYear, classNum, true);
                request.setAttribute("tests", tests);
                request.setAttribute("students", students);
            }
            request.setAttribute("f1", entYear);
            request.setAttribute("f2", classNum);
            request.setAttribute("f3", subjectCd);
            request.setAttribute("f5", numStr);
            request.setAttribute("entYear", entYearStr);
            request.setAttribute("classnum", classNum);
            request.setAttribute("subject", subjectCd);
            request.setAttribute("sub", subject);
            request.setAttribute("num", numStr);
            request.setAttribute("StudentNo", studentNo);
            List<String> cNumList = classNumDao.filter(teacher.getSchool());
            List<Subject> SubList = subjectDao.filter(teacher.getSchool());
            List<Integer> entYearSet = new ArrayList<>();
            for (int i = year - 10; i < year + 1; i++) {
                entYearSet.add(i);
            }
            request.setAttribute("class_num_set", cNumList);
            request.setAttribute("subject_set", SubList);
            request.setAttribute("ent_year_set", entYearSet);
            request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
            return;
        }

        if (testSave) {
            request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
        } else {
            System.out.print("エラー");
        }
    }
}