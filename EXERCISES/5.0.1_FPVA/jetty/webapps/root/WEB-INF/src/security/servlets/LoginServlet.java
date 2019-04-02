package security.servlets;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.UUID;

import security.helper.SqlQuery;
import security.helper.Config;
import security.helper.CookieHelper;
/**
 * This class handles the login logic in the /login route. 
 * @author kivolowitz
 */
public class LoginServlet extends HttpServlet {

    /*
     * (non-Javadoc)
     * This method is invoked whenever a get request is sent to jetty with a url of /login.
     * It will check for existing cookies. If there are some and they are valid, then the user
     * is redirected to their home page. If the cookies are invalid or missing, they are redirected
     * to sign in. 
     * @param HttpServletRequest request - request to be handled
     * @param HttpServletResponse response - response to be returned
     */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if(CookieHelper.checkCookies(request))
		response.sendRedirect("/home");
	    else response.sendRedirect("/index.html");
	}
    /*
     * (non-Javadoc)
     * Most likely this servlet will be activated via a post request. The submit button on index.html triggers a post
     * request which should have username and password data. If the username or password are null or empty strings, the 
     * user will be prompted to reattempt logging in. 
     * 
     * A simple sql query checks whether or not the user should be authenticated. The database is stored in /WEB-INF/db/application.db.
     * The usernames and passwords are stored as plaintext in the USERS table of the database. 
     *
     * A successful login will create two cookies, a cookie named "username" with the value being the username, and a 
     * cookie named the value of username, with a random UUID as the session token. Those together form the authentication
     * method for this application. The details of the session ID (the cookie containing the UUID) will be written into
     * the server's filesystem under /WEB-INF/cookies/<username>.txt. Those cookies are then added to the response and 
     * returned to the user, redirecting them to /home which will then check the validity of the cookies. 
     *
     * @param HttpServletRequest request - request to be handled
     * @param HttpServletResponse response - response to be returned
     */
 	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    if(username == null || username.equals("") || password == null || password.equals("")) {
		response.sendRedirect("/index.html");
		return;
	    }

	    String sqlQuery = "SELECT COUNT(*) AS count FROM USERS WHERE USERNAME == '"
		+ username + "' AND password == '" + password + "'";

	    boolean login = false;
	    SqlQuery sql = new SqlQuery();
	    
	    try{
		ResultSet results = sql.query(sqlQuery);
		if(results.getInt("count") > 0)
		    login = true;
	    } catch(SQLException e) {
		request.setAttribute("error", e.toString());
		request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		return;
	    }
	    if(login) {
		try {
		    Cookie cookieSession = new Cookie(username, UUID.randomUUID().toString());
		    Cookie cookieUsername = new Cookie("username", username);
		    cookieSession.setMaxAge(Config.TIMEOUT);
		    cookieUsername.setMaxAge(Config.TIMEOUT);
		    response.addCookie(cookieSession);
		    response.addCookie(cookieUsername);
		    CookieHelper.writeCookie(cookieSession, username);
		    response.sendRedirect("/home");
		    System.out.println("Logged in successfully");
		} catch(Exception e) {
		    request.setAttribute("error", e.toString());
		    request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		    return;
		}
	    }
	    else {
		response.sendRedirect("/index.html");
	    }
	    sql.close();
	}
}
