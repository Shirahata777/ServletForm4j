package com.github.shirahata777.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
@WebServlet("/ResultServlet")
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

		ArrayList<ArrayList<String>> formDataList = DataOperation.getAllFromData();

		request.setAttribute("formDataList", formDataList);
		request.setAttribute("formDataNum", formDataList.size());

//		for (Map map : formDataList) {
//			System.out.print(map.get("id"));
//			System.out.print(map.get("name"));
//			System.out.print(map.get("email"));
//			System.out.print(map.get("content"));
//		}

		RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/view/result.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}

}
