package security.servlets;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import security.helper.CookieHelper;
/**
 * This servlet is the landing servlet when you visit localhost:8080.
 * @author kivolowitz
 */
public class RootServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Unlikely that someone would post to the root, but handled. 
	    doGet(request, response);
	}
	@Override
    	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Determine if they have a session already or not and response
	    // accordingly.
	    if(!CookieHelper.checkCookies(request)) {
		System.out.println("Cookies are invalid");
		response.sendRedirect("/index.html");
	    } else response.sendRedirect("/home");
	}
}
