package wuds.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import wuds.dao.WorkDao;
import wuds.entity.User;

@WebServlet("/space/upload")
@MultipartConfig(maxFileSize = 10 * 1024 * 1024)
public class WorkUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String workPath = "/work";
	
    public WorkUpload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String course = request.getParameter("course");
		String expNo = request.getParameter("expNo");
		Part part = request.getPart("workfile");
		if(part.getSubmittedFileName().length() != 0) {
			String workFileName = user.getUsername() + user.getName() + course + expNo +
					part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf("."));//作业文件名
			String workFilePath = request.getServletContext().getRealPath(workPath) +
					File.separator + user.getGrade() + course + expNo;//作业文件路径
			File file = new File(workFilePath);
			String[] files = file.list();
			String fileName = user.getUsername() + user.getName() + course + expNo;
			boolean exist = false;
			for (String string : files) {
				if(fileName.equals(string.substring(0, string.lastIndexOf('.')))) {
					exist = true;
				}
			}
			if(!exist) {
				(new WorkDao()).countPP(course, expNo, user.getGrade());
			}
			part.write(workFilePath + File.separator + workFileName);
		}
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append("<script type=\"text/javascript\">alert(\"上传成功，再次上传可覆盖\");window.location.href=\"home\";</script>");
	}

}
