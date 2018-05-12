package edu.csula.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;

@WebServlet("/admin/generators/remove")
public class AdminRemoveGeneratorServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		UsersDAO userDao = new UsersDAOImpl(request.getSession());

		
		if (!userDao.getAuthenticatedUser().isPresent()) {
			response.sendRedirect("../../admin/auth");
		}

		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		Collection<Generator> generators = dao.getAll();

		
		int id = Integer.parseInt(request.getParameter("id"));
		dao.remove(id);

		
		for (Generator g : generators) {
			if (g.getId() >= id) {
				g.setId(g.getId() - 1);
			}
		}

		
		response.sendRedirect("../../admin/generators");
	}
}