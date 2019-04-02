<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Review</title>

		<link rel="stylesheet" href="../../main.css">
	</head>
	<body>
		<h1>Welcome, <%= request.getAttribute("username") %></h1>
		<table>
			<tbody>
				<tr>
					<th>ID</th>
					<th>Reviewed By</th>
					<th>Review</th>
					<th>Timestamp</th>
				</tr>
				<%
					ArrayList<ArrayList<String>> reviews = (ArrayList<ArrayList<String>>)request.getAttribute("reviews");
					for(int i = 0; i < reviews.size(); i++) {
						String className = i % 2 == 0 ? "even" : "odd";
						%> <tr class="<%= className %>"> <%
							for(int j = 0; j < reviews.get(i).size(); j++) {
							%> <th> <%= reviews.get(i).get(j) %> </th> <%
							}
						%> </tr> <%
					}
				%>
			</tbody>
		</table>

		<form action="/submitreview" method="POST">
		  <textarea type="text" name="review" width="200px" height="600px"></textarea><br>
		  <input type="submit" value="Submit">
		</form>

		<form action="/delete" method="GET">
		  Delete your reivew<br>
		  <input type="text" name="reviewID" placeholder="Review ID"><br>
		  <input type="submit" value="Submit">
		</form>


	</body>
</html>