package wuds.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wuds.dao.MessageDao;
import wuds.dao.WorkDao;
import wuds.entity.Message;
import wuds.entity.User;


@WebServlet("/space/home")
public class Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Homepage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Message> messages = (new MessageDao()).getAllMessage();
		request.setAttribute("messages", messages);
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("name", user);
		WorkDao workDao = new WorkDao();
		request.setAttribute("nowTime", Calendar.getInstance().getTimeInMillis());
		request.setAttribute("works", workDao.findByGrade(user.getGrade()));
		if(user.getRole() == User.WORK_ADMINISTRATOR) {
			request.setAttribute("manage", "<a href=\"../manage/workpanel\">管理</a>");			
		} else if (user.getRole() == User.USER_ADMINISTRATOR) {
			request.setAttribute("manage", "<a href=\"../user/manage\">管理</a>");
		}
		request.getRequestDispatcher("/space/homepage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
