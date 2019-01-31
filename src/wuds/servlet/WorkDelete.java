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

@WebServlet("/manage/delete")
public class WorkDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String workPath = "/work";//文件保存目录
    public WorkDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String course = request.getParameter("course");
		String expNo = request.getParameter("expNo");
		WorkDao workDao = new WorkDao();
		workDao.deleteWork(course, expNo, user.getGrade());
		String workFilePath = request.getServletContext().getRealPath(workPath) +
				File.separator + user.getGrade() + course + expNo;//作业文件路径
		File zipFile = new File(workFilePath + ".zip");
		zipFile.delete();
		//删除文件夹及其子文件
		File folder = new File(workFilePath);
		if(folder.exists()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				file.delete();
			}
			folder.delete();
		}
		response.sendRedirect(request.getContextPath() + "/manage/workpanel");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
