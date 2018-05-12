package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		doDelet(request,response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user-authentication.jsp");
		dispatcher.forward(request, response);
		PrintWriter out = response.getWriter();
		
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle login
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsersDAO dao = new UsersDAOImpl(request.getSession());
		if (dao.authenticate(username, password)) {
			response.sendRedirect("../admin/events");
		}
		else {
			
			response.sendRedirect("../admin/auth");
		}
	}

    @Override
    public void doDelete( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle logout
        userDAO dao= new UsersDAOImpl(request.getSession());
        dao.logout();
    }
}
