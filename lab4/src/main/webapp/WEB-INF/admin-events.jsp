<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8"%> 
<html>
			<head>"
				<meta charset=\"UTF-8\">"
			<title>Incremental Game Framework</title>
				<link rel=\"stylesheet\" type=\"text/css\" href=\"../custom.css\">
			</head>
			<body>
				<div class=\"main\">
					<h1 class=\"pageTitle\">Incremental Game Framework</h1>
					<nav class=\"navBar\">
						<a href=\"admin-info.html\">Game Information</a>
						<a href=\"admin-generators.html\"> Generators</a>
						<a href=\"admin-events.html\">
		   				<div class=\"navTitle\">Events</div>
						</a>
					</nav>
					</div>
					<div class = \"container\">
						<div class = \"genForm\">
							<form method = \"POST\">
								<label for = \"event_name\">Event Name:</label>
		
											<input type=\"text\" id = \"event_name\" name = \"event_name\" value=\"\" required>
								<label for = \"description\">Event Description</label>");
									<textarea id = \"description\" name=\"description\" value=\"\" required></textarea>
								<label for = \"trigger\">Trigger at</label>");
								<input type=\"number\" id=\"trigger\" name=\"trigger\" value=\"\" required>
									<input type=\"submit\" name=\"status\" value=\"add\"></input>
							</form>
						</div>
						<table>
							<tr>");
								<th>Name</th>
								<th>Description</th>
								<th>Trigger At</th>
							</tr>
		for (Event event : events) {
								<form method = \"POST\">
									<tr>
									<td>" + event.getName() + "</td>
									<td>" + event.getDescription() + "</td>
									<td>" + event.getTriggerAt() + "</td>
									<td> <a href='../admin/events/delete?id=" + event.getId() +
												 "'>Delete</a> <a role=\"button\" href='../admin/events/edit?id=" + event.getId() + "'>Edit</a>
									</tr>
								</form>
		}
							</table>
						</div>
					</div>
				</div>
			</body>
		</html>