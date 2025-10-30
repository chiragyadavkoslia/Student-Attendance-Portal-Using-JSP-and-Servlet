<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Attendance</title>
</head>
<body>
    <h2>Mark Attendance</h2>
    <form action="AttendanceServlet" method="post">
        <label for="studentId">Student ID:</label>
        <input type="number" id="studentId" name="studentId" required><br><br>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required><br><br>

        <label for="status">Status:</label>
        <select id="status" name="status" required>
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br><br>

        <input type="submit" value="Submit Attendance">
    </form>
</body>
</html>
