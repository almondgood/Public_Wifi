package com.controller;

import com.config.WifiConfig;
import com.dao.history.HistoryDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "historyServlet", urlPatterns = "/history")
public class HistoryServlet extends HttpServlet {

    private HistoryDao historyDao;

    @Override
    public void init() {
        historyDao =  WifiConfig.historyDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("HistoryList", historyDao.selectHistoryList());

        String viewPath = "/WEB-INF/views/history.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("deleteRow"));

        historyDao.deleteHistory(id);
        request.setAttribute("HistoryList", historyDao.selectHistoryList());

        String viewPath = "/WEB-INF/views/history.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
