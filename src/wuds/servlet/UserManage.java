package wuds.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wuds.dao.UserDao;
import wuds.entity.User;

@WebServlet("/user/manage")
public class UserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserManage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		List<User> users = (new UserDao()).findByGrade(user.getGrade());
		request.setAttribute("users", users);
		request.getRequestDispatcher("/user/manage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String find = request.getParameter("find");
		User user = (User) request.getSession().getAttribute("user");
		List<User> users = (new UserDao()).findByUN(find, user.getGrade());
		if(users.isEmpty()) {
			request.setAttribute("msg", "没有找到");
		}
		request.setAttribute("users", users);
		request.getRequestDispatcher("/user/manage.jsp").forward(request, response);
	}

}
