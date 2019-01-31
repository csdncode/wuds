package wuds.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wuds.entity.User;


@WebFilter({"/manage/*","/work/*"})
public class Manage implements Filter {


    public Manage() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if(user != null) {
			if(user.getRole() == User.WORK_ADMINISTRATOR) {
				chain.doFilter(request, response);
			} else {
				httpServletResponse.sendError(404);
			}
		} else {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
