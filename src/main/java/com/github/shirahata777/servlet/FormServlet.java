package com.github.shirahata777.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.shirahata777.db.operation.DataOperation;
import com.github.shirahata777.model.FormDataQuery;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FormServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/view/form.jsp");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis() - 1000 * 60 * 60 * 24);
		FormDataQuery formQuery = new FormDataQuery();

		formQuery.setName(request.getParameter("name"));
		formQuery.setEmail(request.getParameter("email"));
		formQuery.setContent(request.getParameter("content"));
		formQuery.setCreatedAt(timestamp);
		formQuery.setUpdatedAt(timestamp);

		DataOperation.insertFromData(formQuery);
		System.out.println("Insert OK!");

//		String url = "/ServletForm4j/ResultServlet";
		String url = "/ServletForm4j/CompletionServlet";

		response.sendRedirect(url);

	}

}
