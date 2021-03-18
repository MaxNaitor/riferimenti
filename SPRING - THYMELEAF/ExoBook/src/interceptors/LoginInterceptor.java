package interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		boolean utenteAttivo = session.getAttribute("utenteAttivo") != null;
		boolean adminAttivo = session.getAttribute("adminAttivo") != null;
		boolean logView = (request.getRequestURI().endsWith("login") || request.getRequestURI().endsWith("index")
				|| request.getRequestURI().endsWith("Accedi") || request.getRequestURI().endsWith("Registrati")
				|| request.getRequestURI().endsWith("accesso"));
		String ctx = request.getContextPath();

		if (!utenteAttivo && !adminAttivo && !logView) {
			response.sendRedirect(ctx + "/index");
			return false; // return false solleva il controller dalla gestione della richiesta
		}

		if (request.getMethod().equalsIgnoreCase("get")
				&& !(request.getRequestURI().endsWith("index") || request.getRequestURI().endsWith("indietroUtente")
						|| request.getRequestURI().endsWith("indietro"))) {
			if (utenteAttivo) {
				response.sendRedirect(ctx + "/indietroUtente");
				return false;
			} else if (adminAttivo) {
				response.sendRedirect(ctx + "/admin/indietro");
				return false;
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("sono nel post Handle");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("=== AFTER COMPLETION ===");
//		System.out.println("Response ContentType: " + response.getContentType());
	}

}
