<!DOCTYPE html>
<html>
<head><title>Add Schedule</title></head>
<body>
    <h2>Enter Schedule Details</h2>
    <form action="MainServlet" method="post">
        <input type="hidden" name="operation" value="newSchedule">
        Source:       <input type="text" name="source"/><br/>
        Destination:  <input type="text" name="destination"/><br/>
        Start Time:   <input type="text" name="startTime"/><br/>
        Arrival Time: <input type="text" name="arrivalTime"/><br/>
        <input type="submit" value="Create Schedule"/>
    </form>
</body>
</html>