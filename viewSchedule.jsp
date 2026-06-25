<!DOCTYPE html>
<html>
<head><title>View Schedule</title></head>
<body>
    <h2>Search Schedule</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="viewSchedule">
        Enter Source:      <input type="text" name="source"/><br/>
        Enter Destination: <input type="text" name="destination"/><br/>
        <input type="submit" value="Search Schedule"/>
    </form>
</body>
</html>