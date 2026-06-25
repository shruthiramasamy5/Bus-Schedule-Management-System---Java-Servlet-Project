package com.wipro.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.bus.bean.ScheduleBean;
import com.wipro.bus.util.DBUtil;

public class ScheduleDAO {

    public String createSchedule(ScheduleBean scheduleBean) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getDBConnection();
            String sql = "INSERT INTO SCHEDULE_TBL (SCHEDULEID, SOURCE, DESTINATION, STARTTIME, ARRIVALTIME) "
                       + "VALUES (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, scheduleBean.getScheduleId());
            ps.setString(2, scheduleBean.getSource());
            ps.setString(3, scheduleBean.getDestination());
            ps.setString(4, scheduleBean.getStartTime());
            ps.setString(5, scheduleBean.getArrivalTime());

            int rows = ps.executeUpdate();
            if (rows > 0) return "SUCCESS";

        } catch (SQLException e) {
            e.printStackTrace();
            return "FAIL";
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "FAIL";
    }

    public String generateID(String source, String destination) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // First two letters of source and destination in uppercase
            String srcCode  = source.substring(0, 2).toUpperCase();
            String destCode = destination.substring(0, 2).toUpperCase();

            // Get next value from sequence
            con = DBUtil.getDBConnection();
            String sql = "SELECT SCHEDULE_SEQ.NEXTVAL FROM DUAL";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                int seq = rs.getInt(1);
                return srcCode + destCode + seq;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<ScheduleBean> viewSchedule(String source, String destination) {
        ArrayList<ScheduleBean> list = new ArrayList<ScheduleBean>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getDBConnection();
            String sql = "SELECT SCHEDULEID, SOURCE, DESTINATION, STARTTIME, ARRIVALTIME "
                       + "FROM SCHEDULE_TBL WHERE SOURCE = ? AND DESTINATION = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, source);
            ps.setString(2, destination);
            rs = ps.executeQuery();

            while (rs.next()) {
                ScheduleBean bean = new ScheduleBean();
                bean.setScheduleId(rs.getString("SCHEDULEID"));
                bean.setSource(rs.getString("SOURCE"));
                bean.setDestination(rs.getString("DESTINATION"));
                bean.setStartTime(rs.getString("STARTTIME"));
                bean.setArrivalTime(rs.getString("ARRIVALTIME"));
                list.add(bean);
            }

            if (list.isEmpty()) return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}