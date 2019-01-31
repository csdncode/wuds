package wuds.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wuds.dao.WorkDao;
import wuds.entity.User;
import wuds.entity.Work;
import wuds.util.CalendarUtil;

@WebServlet("/manage/addwork")
public class AddWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String workPath = "/work";
    public AddWork() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		User user = (User) request.getSession().getAttribute("user");
		String course = request.getParameter("course");
		String expNo = request.getParameter("expNo");
		String endTime = request.getParameter("endTime");
		String grade = user.getGrade();
		Work work = new Work();
		work.setCourse(course);
		work.setExpNo(expNo);
		work.setEndTime(CalendarUtil.StringToCalendar(endTime).getTimeInMillis());
		work.setGrade(grade);
		work.setCount(0);
		WorkDao workDao = new WorkDao();
		int count = workDao.add(work);
		if(count == 1) {
			String workFilePath = request.getServletContext().getRealPath(workPath) +
					File.separator + user.getGrade() + course + expNo;//每项作业的存储路径
			File folder = new File(workFilePath);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			response.getWriter().append("{\"success\":\"true\",\"msg\":\"添加成功!\"}");
		} else {
			response.getWriter().append("{\"success\":\"false\",\"msg\":\"添加失败，可能已经存在此作业!\"}");
		}
	}

}
