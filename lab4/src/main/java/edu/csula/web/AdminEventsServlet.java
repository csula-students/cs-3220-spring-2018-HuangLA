package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		System.out.println(events);
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta charset=\"UTF-8\">");
		out.println("		<title>Incremental Game Framework</title>");
		out.println("		<link rel=\"stylesheet\" type=\"text/css\" href=\"../custom.css\">");
		out.println("	</head>");
		out.println("	<body>");
		out.println("		<div class=\"main\">");
		out.println("			<h1 class=\"pageTitle\">Incremental Game Framework</h1>");
		out.println("			<nav class=\"navBar\">");
		out.println("				<a href=\"admin-info.html\">Game Information</a>");
		out.println("				<a href=\"admin-generators.html\"> Generators</a>");
		out.println("				<a href=\"admin-events.html\">");
		out.println("    				<div class=\"navTitle\">Events</div>");
		out.println("				</a>");
		out.println("			</nav>");
		out.println("			</div>");
		out.println("			<div class = \"container\">");
		out.println("				<div class = \"genForm\">");
		out.println("					<form method = \"POST\">");
		out.println("						<label for = \"event_name\">Event Name:</label>");
		out.println(
				"							<input type=\"text\" id = \"event_name\" name = \"event_name\" value=\"\" required>");
		out.println("						<label for = \"description\">Event Description</label>");
		out.println(
				"							<textarea id = \"description\" name=\"description\" value=\"\" required></textarea>");
		out.println("						<label for = \"trigger\">Trigger at</label>");
		out.println(
				"							<input type=\"number\" id=\"trigger\" name=\"trigger\" value=\"\" required>");
		out.println(
				"							<input type=\"submit\" name=\"status\" value=\"add\"></input>");
		out.println("					</form>");
		out.println("				</div>");
		out.println("				<table>");
		out.println("					<tr>");
		out.println("						<th>Name</th>");
		out.println("						<th>Description</th>");
		out.println("						<th>Trigger At</th>");
		out.println("					</tr>");
		for (Event event : events) {
			out.println("					<form method = \"POST\">");
			out.println("						<tr>");
			out.println("						<td>" + event.getName() + "</td>");
			out.println("						<td>" + event.getDescription() + "</td>");
			out.println("						<td>" + event.getTriggerAt() + "</td>");
			out.println(
					"						<td> <a href='../admin/events/delete?id=" + event.getId() +
												 "'>Delete</a> <a role=\"button\" href='../admin/events/edit?id=" + event.getId() + "'>Edit</a>");
			out.println("						</tr>");
			out.println("					</form>");
		}
		out.println("					</table>");
		out.println("				</div>");
		out.println("			</div>");
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
	}
}
