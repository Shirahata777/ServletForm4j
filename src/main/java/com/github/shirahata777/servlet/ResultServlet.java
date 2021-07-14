package com.github.shirahata777.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.shirahata777.db.operation.DataOperation;

/**
 * Servlet implementation class ResultServlet
 */
@WebServlet("/result")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResultServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ResultSet rs = null;

		List<List<String>> formDataList = DataOperation.getAllFromData();

		if (Objects.isNull(formDataList)) {
			response.setStatus(500);
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/view/500.jsp");
			dispatch.forward(request, response);
		} else {
			request.setAttribute("formDataList", formDataList);
			request.setAttribute("formDataNum", formDataList.size());
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/view/result.jsp");
			dispatch.forward(request, response);
		}

	}

}
