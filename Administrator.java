package com.wipro.bus.service;

import java.util.ArrayList;

import com.wipro.bus.bean.ScheduleBean;
import com.wipro.bus.dao.ScheduleDAO;
import com.wipro.bus.util.InvalidInputException;

public class Administrator {

    public String addSchedule(ScheduleBean scheduleBean) {

        // Check 1: null bean
        try {
            if (scheduleBean == null) {
                throw new InvalidInputException();
            }

            // Check 2: empty source or destination
            if (scheduleBean.getSource() == null || scheduleBean.getSource().equals("")
                    || scheduleBean.getDestination() == null || scheduleBean.getDestination().equals("")) {
                throw new InvalidInputException();
            }

            // Check 3: source or destination less than 2 characters
            if (scheduleBean.getSource().length() < 2 || scheduleBean.getDestination().length() < 2) {
                throw new InvalidInputException();
            }

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }

        // Check 4: same source and destination
        if (scheduleBean.getSource().equalsIgnoreCase(scheduleBean.getDestination())) {
            return "Source and Destination Same";
        }

        // Generate schedule ID
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        String scheduleId = scheduleDAO.generateID(scheduleBean.getSource(), scheduleBean.getDestination());

        if (scheduleId == null) {
            return "FAIL";
        }

        // Set ID and create schedule
        scheduleBean.setScheduleId(scheduleId);
        return scheduleDAO.createSchedule(scheduleBean);
    }

    public ArrayList<ScheduleBean> viewSchedule(String source, String destination) {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        return scheduleDAO.viewSchedule(source, destination);
    }
}