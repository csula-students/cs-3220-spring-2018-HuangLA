package edu.csula.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;

import edu.csula.storage.mysql.Database;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		UsersDAO userDao = new UsersDAOImpl(request.getSession());

		
		if (!userDao.getAuthenticatedUser().isPresent()) {
			response.sendRedirect("../admin/auth");
		}

		// TODO: render the generators page HTML
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		Collection<Generator> generators = dao.getAll();

		
		request.setAttribute("generators", generators);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin-generators.jsp");
		dispatcher.forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name = request.getParameter("generator_name");
		String description = request.getParameter("description");
		int rate = Integer.parseInt(request.getParameter("generator_rate"));
		int baseCost = Integer.parseInt(request.getParameter("base_cost"));
		int unlockAt = Integer.parseInt(request.getParameter("unlock_at"));

		
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		Collection<Generator> generators = dao.getAll();
		dao.add(new Generator(generators.size(), name, description, rate, baseCost, unlockAt));

		
		response.sendRedirect("../admin/generators");
	}
}