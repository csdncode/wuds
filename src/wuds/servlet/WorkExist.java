package wuds.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wuds.entity.User;

@WebServlet("/space/workexist")
public class WorkExist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String workPath = "/work";
    public WorkExist() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String course = request.getParameter("course");
		String expNo = request.getParameter("expNo");
		User user = (User) request.getSession().getAttribute("user");
		String fileName = user.getUsername() + user.getName() + course + expNo;
		String workFilePath = request.getServletContext().getRealPath(workPath) +
				File.separator + user.getGrade() + course + expNo;//作业文件路径
		File file = new File(workFilePath);
		String[] files = file.list();
		for (String string : files) {
			if(fileName.equals(string.substring(0, string.lastIndexOf('.')))) {
				response.getWriter().append("{\"success\":\"true\"}");
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
