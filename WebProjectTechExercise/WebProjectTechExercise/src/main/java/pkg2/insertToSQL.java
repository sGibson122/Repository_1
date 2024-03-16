package pkg2;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertToSQL
 */
@WebServlet("/insertToSQL")
public class insertToSQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertToSQL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	      String userName = request.getParameter("userName");
	      String desired_field = request.getParameter("desiredField");
	      String desiredHours = request.getParameter("desiredHours");
	      String estimatedIncome = request.getParameter("estimatedIncome");


	      Connection connection = null;
	      String insertSql = " INSERT INTO MyTableGibson (id, USER_NAME, DESIRED_FIELD, DESIRED_HOURS, ESTIMATED_REVENUE) values (default, ?, ?, ?, ?)";

	
	      try {
	    	 DBConnectionGibson.getDBConnection();
	         connection = DBConnectionGibson.connection;
	         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
	         preparedStmt.setString(1, userName);
	         preparedStmt.setString(2, desired_field);
	         preparedStmt.setString(3, desiredHours);
	         preparedStmt.setString(4, estimatedIncome);
	         preparedStmt.execute();
	         //connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      

	      try {
	      // Set response content type
	      response.setContentType("text/html");
         String selectSQL = "SELECT * FROM MyTableGibson;";
         PreparedStatement preparedStmt = connection.prepareStatement(selectSQL);
         preparedStmt.execute();
         
         ResultSet rs = preparedStmt.executeQuery();
         
         response.getWriter().append("<H1>Database Entries</H1>");
         
         while (rs.next()) {
        	 String Output = new String();
        	 
             int id = rs.getInt("id");
             String userName2 = rs.getString("USER_NAME").trim();
             String desiredField2 = rs.getString("DESIRED_FIELD").trim();
             String desiredHours2 = rs.getString("DESIRED_HOURS").trim();
             String estimatedIncome2 = rs.getString("ESTIMATED_REVENUE").trim();
             
             
             

             System.out.println("ID: " + id + ", ");
             System.out.println("User Name: " + userName2 + ", ");
             System.out.println("Desired Field: " + desiredField2 + ", ");
             System.out.println("Desired Hoursd: " + desiredHours2 + "<br>");
             System.out.println("Estimated Income: " + estimatedIncome2 + " per week<br>");
             
             //Print lines
             
             Output += "\n<p>User iD:" + id + "; User Name: " + userName2 + "; Desired Field: " + desiredField2 + "; Desired Hours: " + desiredHours2 + "; Estimated income: " + estimatedIncome2 + "</p>";
             response.getWriter().append(Output);
         }
         connection.close();
	      } catch(Exception e)
	      {
	    	  e.printStackTrace();
	      }
         
    response.getWriter().append("<h3>Congratulations! You are now registered in our database.</h3>");
    response.getWriter().append("<a href='Index.html'>Return To Revenue Calculator</a>");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
