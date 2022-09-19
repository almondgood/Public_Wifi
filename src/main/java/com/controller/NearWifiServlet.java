package com.controller;

import com.config.WifiConfig;
import com.service.history.HistoryService;
import com.service.wifi.WifiService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;

@WebServlet(name = "nearWifiServlet", urlPatterns = "/result")
public class NearWifiServlet extends HttpServlet {

    private WifiService wifiService;
    private HistoryService historyService;

    @Override
    public void init() {
        wifiService = WifiConfig.wifiService();
        historyService = WifiConfig.wifiHistoryService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double myLat = Double.parseDouble(request.getParameter("lat"));
        double myLnt = Double.parseDouble(request.getParameter("lnt"));

        request.setAttribute("NearWifiMap", new TreeMap<>(wifiService.getNearWifiMap(myLat, myLnt)));

        historyService.insertHistory(myLat, myLnt, now());

        String viewPath = "/WEB-INF/views/result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private String now() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
    }
}
