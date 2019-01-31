package wuds.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wuds.dao.UserDao;
import wuds.entity.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//user不为空表明用户已经登录，重定向到首页。否则请求转发到login.jsp
		if(user != null) {
			//重定向到首页
			response.sendRedirect(request.getContextPath() + "/space/home");
		} else {
			//请求转发到login.jsp
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = (new UserDao()).login(username, password);
		//user不为空表明用户名密码正确
		if(user != null) {
			//设置session
			request.getSession().setAttribute("user", user);
			//重定向到首页
			response.sendRedirect(request.getContextPath() + "/space/home");
		} else {
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("username", username);
			//请求转发到login.jsp
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
