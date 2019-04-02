package security.servlets;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import security.helper.SqlQuery;
import security.helper.CookieHelper;
/**
 * Simple servlet to handle the insertion of a new review into the database.
 * This handles the /submitreview route and should either redirect to /index.html
 * or /home
 * 
 * @author Evan Kivolowitz
 */
public class SubmitReviewServlet extends HttpServlet {
    
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Error checking
	    if(!CookieHelper.checkCookies(request)) {
		System.out.println("Cookies are invalid from SubmitReview Post Request");
		response.sendRedirect("/index.html");
		return;
	    }

	    String username = CookieHelper.getUsernameCookieName(request);
	    if(username == null) {
		System.out.println("Session is not null but username is");
		response.sendRedirect("/index.html");
		return;
	    }

	    String review = (String) request.getParameter("review");
	    if(review.equals("")) {
		response.sendRedirect("/home");
		return;
	    }
	    // end error checking

	    // Gathers the four elements of a review and sends it to the insert method of
	    // the sql class. Upon success, or failure, the user is silently redirected to home. 
	    String id = UUID.randomUUID().toString();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    String sqlQuery = "INSERT INTO REVIEWS VALUES (?,?,?,?)";
	    String[] values = {id, username, dateFormat.format(new Date()), review};

	    SqlQuery sql = new SqlQuery();
	    try{
		sql.insert(sqlQuery, values);
		response.sendRedirect("/home");
	    } catch (SQLException e) {
		request.setAttribute("error", e.toString());
		request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	    }
	    sql.close();

	}
}

											    
