package security.servlets;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.sql.SQLException;

import security.helper.SqlQuery;
import security.helper.CookieHelper;
/**
 * @author Evan Kivolowitz
 */
public class DeleteReviewServlet extends HttpServlet {
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Error checking
	    SqlQuery sql = new SqlQuery();
	    try{
		String id = request.getParameter("reviewID");
		String SqlQuery = "DELETE FROM REVIEWS WHERE reviewID = '"+ id + "'";
		sql.delete(SqlQuery);
		response.sendRedirect("/home");
	    } catch (SQLException e) {
		request.setAttribute("error", e.toString());
		request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	    }
	    sql.close();
	}
}
