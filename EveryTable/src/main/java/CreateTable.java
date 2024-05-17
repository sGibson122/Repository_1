
/**
 * @file InsertGibson.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateTable")
public class CreateTable extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public CreateTable() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String taskName = request.getParameter("taskName");
      String dueDate = request.getParameter("dueDate");
      String people = request.getParameter("peopleTask");
      String details = request.getParameter("details");
      String userName = request.getParameter("userName");
      String passWord = request.getParameter("passWord");


      Connection connection = null;
      String insertSql = " INSERT INTO EveryTablePrimary (id, TASK_NAME, DUE_DATE, PEOPLE, DETAILS) values (default, ?, ?, ?, ?)";

      try {
    	 DBConnectionTables.getDBConnectionTables();
         connection = DBConnectionTables.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, taskName);
         preparedStmt.setString(2, dueDate);
         preparedStmt.setString(3, people);
         preparedStmt.setString(4, details);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
    
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<form action='EveryTableMain'>" +
            "<h2>Table Succesfully Added!</h2>" +
			"<input type='hidden' name='userName' value='" + userName + "'>" +
			"<input type='hidden' name='passWord' value='" + passWord + "'>" +
            "<input type='submit' value='Return To Main Page.'>" +
            "</form>" +

            "</ul>\n");

      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
