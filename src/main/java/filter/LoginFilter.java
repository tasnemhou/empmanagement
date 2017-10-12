package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	private String paths[];
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) res;

  		String user = (String)request.getSession().getAttribute("user");
		
		//��½������Ҫ����
		for(String path:paths) {
				if(path.equals(request.getServletPath())) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		if(user==null) {
			//��ԱΪ�գ�˵����Աû�е�½ ���ض��򵽵�½
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			//�ǿգ��ѵ�½������ִ��
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
		String path = cfg.getInitParameter("ignore");
		paths = path.split(";");
	}

}
