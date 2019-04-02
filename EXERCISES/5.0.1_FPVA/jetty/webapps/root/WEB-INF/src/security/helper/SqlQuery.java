package security.helper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 * This is a helper class to make using sql easier. It should be used whenever interacting with the database.
 * @author kivolowitz
 */
public class SqlQuery {

    private Connection c;
    private Statement statement;
    private ResultSet results;
    private static final String DB_URL = "jdbc:sqlite:/home/user/Desktop/EXERCISES/5.0.1_FPVA/jetty/webapps/root/WEB-INF/db/application.db";
   
    public SqlQuery() {
	this.c = null;
	this.statement = null;
	this.results = null;
    }

    /*
     * (non-Javadoc)
     * Method for querying the database. Simply takes the query as is from the caller
     * and executes it and returns the result.
     * @param String query - query to run
     * @return ResultSet results - result from the executed query
     */
    public ResultSet query(String query) throws SQLException{
	c = DriverManager.getConnection(DB_URL);
	statement = c.createStatement();
	results = statement.executeQuery(query);
	return results;
    }

    /*
     * (non-Javadoc)
     * Method for inserting into the database. Separated from query(String query) for 
     * simplicity.
     * @param String query - the query string
     * @param values - values to fill the query string in with in a Prepared Statement.
     * @return void
     */
    public void insert(String query, String[] values) throws SQLException{
	c = DriverManager.getConnection(DB_URL);
	PreparedStatement s = c.prepareStatement(query);

	// Note that preparedStatements index from 1, not 0. 
	for(int i = 1; i <= values.length; i++)
	    s.setString(i, values[i - 1]);
	s.executeUpdate();
    }

    public void delete(String query) throws SQLException {
	System.out.println(query);
	c = DriverManager.getConnection(DB_URL);
	PreparedStatement s = c.prepareStatement(query);
	s.executeUpdate();

    }
    
    /*
     * (non-Javadoc)
     * Method to close the Sql object. Every sql object should have a corresponding close() call
     * to avoid resource leaks.
     */
    public void close() {
	try {
	    results.close();
	} catch (Exception e) {
	}
	try {
	    statement.close();
	} catch (Exception e) {
	}
	try {
	    c.close();
	} catch (Exception e) {
	}
    }
}
