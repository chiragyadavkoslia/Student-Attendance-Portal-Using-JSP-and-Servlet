import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // JDBC credentials
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/SchoolDB";
    private final String JDBC_USER = "root"; // Replace with your DB username
    private final String JDBC_PASSWORD = "password"; // Replace with your DB password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Read form data
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Insert attendance record
            String sql = "INSERT INTO Attendance (StudentID, AttendanceDate, Status) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setDate(2, java.sql.Date.valueOf(date));
            ps.setString(3, status);

            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                out.println("<html><body>");
                out.println("<h2>Attendance recorded successfully!</h2>");
                out.println("<a href='attendance.jsp'>Mark Another Attendance</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h2>Failed to record attendance. Please try again.</h2>");
                out.println("</body></html>");
            }

            ps.close();
            conn.close();

        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            out.println("</body></html>");
            e.printStackTrace(out);
        }

        out.close();
    }
}
