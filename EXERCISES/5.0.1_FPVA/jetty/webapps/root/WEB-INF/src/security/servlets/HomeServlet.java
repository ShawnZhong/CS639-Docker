package security.servlets;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import security.helper.CookieHelper;
import security.helper.SqlQuery;
/**
 *  Class to handle the /home route. 
 *  @author kivolowitz
 */
public class HomeServlet extends HttpServlet {
    
	@Override
    	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Session checking
	    if(!CookieHelper.checkCookies(request)) {
		System.out.println("Cookies are invalid HomeServlet");
		response.sendRedirect("/index.html");
		return;
	    }
	    
	    String username = CookieHelper.getUsernameCookieName(request);

	    if(username == null) {
		System.out.println("Username cookie doesn't exist");
		response.sendRedirect("/index.html");
		return;
	    }
	    // end session checking

	    // Reviews are returned as an ArrayList of ArrayList<strings> for simplicity with the front end.
	    // { {"reviewID1", "reviewer1", "review1", "timestamp1"},
	    //   {"reviewID2", "reviewer2", "review2", "timestamp2"} }
	    ArrayList<ArrayList<String>> reviews = new ArrayList<ArrayList<String>>();
	    SqlQuery sql = new SqlQuery();
	    try {
		String sqlQuery = "SELECT * FROM REVIEWS";

		
		ResultSet results = sql.query(sqlQuery);

		while(results.next()) {
		    ArrayList<String> review = new ArrayList<String>();
		    review.add(results.getString("reviewID"));
		    review.add(results.getString("reviewer"));
		    review.add(results.getString("review"));
		    review.add(results.getString("tstamp"));
		    reviews.add(review);
		}
		sql.close();
	    } catch (Exception e) {
		request.setAttribute("error", e.toString());
		request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		return;
	    }

	    // Set the attributes of the request and forward the request to the jsp page
	    // for rendering. 
	    request.setAttribute("reviews", reviews);
	    request.setAttribute("username", username);
	    request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);	    
	}
    	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Unlikely that someone would post, but handled. 
	    doGet(request, response);
	}

}
