package security.helper;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Class full of static methods to make dealing with cookies easier.
 * Contains methods for writing cookies to files, checking if they're valid,
 * getting the username cookie, and checking the cookies to see if the session is valid.
 *
 * @author kivolowitz
 */

public class CookieHelper {

    // Constant declaring the conversion between milliseconds and seconds.
    // System time is in milliseconds while cookie timeouts are in seconds. 
    public static final long MS_TO_SEC = 1000l;

    /*
     * (non-Javadoc)
     *
     * Write cookie is a helper function to write the contents of the cookie to
     * a file for the server to read. Cookies are stored in the /WEB-INF/cookies/
     * directory with the filename being the current user's username. For example,
     * if Cher, Bono, and Enya were the only three users using the site, the cookies
     * directory would look like 
     * cookies/
     * ├── Bono.txt
     * ├── Cher.txt
     * └── Enya.txt
     * 
     * The three components of a cookie that we use are: Name|Value|Expiration
     * 
     * @param Cookie cookie - cookie to write to the server.
     * @param String username - username of the current user to create the cookie file.
     */
    public static void writeCookie(Cookie cookie, String username) {
	try {
	    File f = new File("webapps/root/WEB-INF/cookies/" + username + ".txt");
	    if(!f.exists())
		f.createNewFile();

	    PrintWriter writer = new PrintWriter(f);
	    long currTime = System.currentTimeMillis() / MS_TO_SEC;
	    long expirationTime = (long) cookie.getMaxAge() + currTime;

	    // Pipes are used to delimit the three important components of a cookie in the text
	    // file. 
	    writer.println(cookie.getName() + "|" + cookie.getValue() + "|" + expirationTime);
	    writer.close();
	} catch (Exception e) {
	    System.out.println("Failed to write" + e.toString());
	}
    }

    /*
     * (non-Javadoc) 
     * This is a simple method that checks if a cookie is valid. By valid,
     * that means that the cookie sent by the request matches the corresponding 
     * 1st value in the file named cookies/<username>.txt, as well the expiration date
     * being greater than the current time. It is unlikely that you will get an
     * invalid due to expiration, as the browser deletes the cookie when it expires.
     *
     * @param Cookie cookie - the cookie to be checked
     * @param String username - username of the client to be checked
     * @return boolean True if valid, otherwise False
     */
    public static boolean isCookieValid(Cookie cookie, String username) {
	// cookie values are client side
	boolean valid = false;
	try {
	    File f = new File("webapps/root/WEB-INF/cookies/" + username + ".txt");
	    Scanner sc = new Scanner(f);
	    String line = sc.nextLine();
	    sc.close();
	    System.out.println(line);
	    String[] values = line.split("\\|");
	    String cookieValue = values[1];
	    long expirationDate = Long.parseLong(values[2]);

	    if(expirationDate > System.currentTimeMillis() / MS_TO_SEC && cookie.getValue().equals(cookieValue))
		valid = true;
	    if(expirationDate < System.currentTimeMillis() / MS_TO_SEC)
		System.out.println("Invalid due to expiration");
	    if(!cookie.getValue().equals(cookieValue))
		System.out.println("Invalid due to invalid cookie value");
	} catch(Exception e) {
	    System.out.println("Failed");
	    System.out.println(e.toString());
	}
	return valid;

    }

    /*
     * (non-Javadoc)
     * This method simply finds a cookie named "username" and returns its value.
     * @param HttpServletRequest request - request with the cookies attached
     * @return String username if the cookie exists, otherwise null.
     *
     */ 
    public static String getUsernameCookieName(HttpServletRequest request) {
	Cookie[] cookie = request.getCookies();
	if(cookie != null) {
	    for(Cookie c : cookie) {
		if(c.getName().equals("username"))
		    return c.getValue();
	    }
	}
	return null;
    }

    /*
     * (non-Javadoc)
     * This method checks all the cookies for a request to see if there is a valid session
     * cookie. To enforce this, it finds the username of the logged in user (via the cookie "username").
     * Then the method will look for a cookie with the name in cookieUsername and check if that cookie
     * is valid. It is important to default to returning false. If there is no username, this will return
     * false.
     *
     * @param HttpServletRequest request - request with the cookies attached to it.
     * @return boolean True iff the session cookie "username" has a valid corresponding valid session.
     *         Otherwise false.
     */
    public static boolean checkCookies(HttpServletRequest request) {
	Cookie[] cookies = request.getCookies();
	String sessionUsername = getUsernameCookieName(request);
	if(sessionUsername == null)
	    return false;
	if(cookies != null) {
	    System.out.println("Cookies length: " + cookies.length);
	    for(Cookie c : cookies) {
		String cookieUsername = (String) c.getName();
		if(sessionUsername.equals(cookieUsername)) {
		    if(CookieHelper.isCookieValid(c, sessionUsername)) {
			System.out.println("checkCookies cookie is valid " + c.getName());
			return true;
		    }
		}
		else System.out.println("Cookie is invalid: " +
					c.getName() + " " + c.getValue());
	    }
	}
	if(cookies == null) System.out.println("Cookies were null");
	return false;
    }

}
