<%@ page import="java.util.ArrayList, com.wipro.bus.bean.ScheduleBean" %>
<%
    ArrayList<ScheduleBean> scheduleList = (ArrayList<ScheduleBean>) request.getAttribute("scheduleList");
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head><title>Schedule Details</title></head>
<body>
<%
    if (message != null) {
%>
    <p><%= message %></p>
<%
    } else if (scheduleList != null && !scheduleList.isEmpty()) {
%>
    <table border="1">
        <tr>
            <th>Schedule ID</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Start Time</th>
            <th>Arrival Time</th>
        </tr>
        <%
            for (ScheduleBean s : scheduleList) {
        %>
        <tr>
            <td><%= s.getScheduleId() %></td>
            <td><%= s.getSource() %></td>
            <td><%= s.getDestination() %></td>
            <td><%= s.getStartTime() %></td>
            <td><%= s.getArrivalTime() %></td>
        </tr>
        <%
            }
        %>
    </table>
<%
    } else {
%>
    <p>No matching schedules exists! Please try again!</p>
<%
    }
%>
</body>
</html>