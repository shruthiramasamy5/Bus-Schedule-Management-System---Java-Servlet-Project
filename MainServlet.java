package com.wipro.bus.servlets;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.bus.bean.ScheduleBean;
import com.wipro.bus.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public String addSchedule(HttpServletRequest request) {
        ScheduleBean scheduleBean = new ScheduleBean();

        String source      = request.getParameter("source");
        String destination = request.getParameter("destination");
        String startTime   = request.getParameter("startTime");
        String arrivalTime = request.getParameter("arrivalTime");

        scheduleBean.setSource(source);
        scheduleBean.setDestination(destination);
        scheduleBean.setStartTime(startTime);
        scheduleBean.setArrivalTime(arrivalTime);

        Administrator admin = new Administrator();
        return admin.addSchedule(scheduleBean);
    }

    public ArrayList<ScheduleBean> viewSchedule(HttpServletRequest request) {
        String source      = request.getParameter("source");
        String destination = request.getParameter("destination");

        Administrator admin = new Administrator();
        return admin.viewSchedule(source, destination);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("newSchedule".equals(operation)) {
            String result = addSchedule(request);
            if ("SUCCESS".equals(result)) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("errorInserting.html");
            }

        } else if ("viewSchedule".equals(operation)) {
            ArrayList<ScheduleBean> list = viewSchedule(request);
            if (list == null || list.isEmpty()) {
                request.setAttribute("message", "No matching schedules exists! Please try again!");
                request.getRequestDispatcher("displaySchedule.jsp").forward(request, response);
            } else {
                request.setAttribute("scheduleList", list);
                request.getRequestDispatcher("displaySchedule.jsp").forward(request, response);
            }
        }
    }
}