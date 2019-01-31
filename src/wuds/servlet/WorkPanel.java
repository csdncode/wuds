package wuds.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wuds.dao.WorkDao;
import wuds.entity.User;
import wuds.entity.Work;

@WebServlet("/manage/workpanel")
public class WorkPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WorkPanel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		List<Work> works = (new WorkDao()).findByGrade(user.getGrade());
		request.setAttribute("nowTime", Calendar.getInstance().getTimeInMillis());
		request.setAttribute("works", works);
		request.getRequestDispatcher("/manage/workpanel.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
