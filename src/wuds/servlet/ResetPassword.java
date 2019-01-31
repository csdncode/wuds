package wuds.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wuds.dao.UserDao;
import wuds.entity.User;

@WebServlet("/user/resetpassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResetPassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		String username = request.getParameter("username");
		User user = (User) request.getSession().getAttribute("user");
		int count = (new UserDao()).resetPassword(username, user.getGrade());
		if(count == 1) {
			response.getWriter().append("{\"success\":\"ture\"}");
		} else {
			response.getWriter().append("{\"success\":\"false\"}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
