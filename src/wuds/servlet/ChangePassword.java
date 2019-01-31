package wuds.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wuds.dao.UserDao;
import wuds.entity.User;


@WebServlet("/space/change_password")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChangePassword() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/space/change_password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oldPassword = request.getParameter("oldpwd");
		String newPassword = request.getParameter("newpwd");
		User user = (User) request.getSession().getAttribute("user");
		UserDao userDao = new UserDao();
		if(userDao.changePassword(user.getUsername(), oldPassword, newPassword) == 1) {
			request.getRequestDispatcher("/space/logout").forward(request, response);
		} else {
			request.setAttribute("msg", "旧密码错误，请重新输入！");
			request.getRequestDispatcher("/space/change_password.jsp").forward(request, response);
		}
	}

}
