package com.revature.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

	public static void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher redis = req.getRequestDispatcher("/index.html");
		redis.forward(req, resp);
		
	}

	public static void homePageReset(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher redis = req.getRequestDispatcher("/index.html");
		redis.forward(req, resp);
		
	}

}
