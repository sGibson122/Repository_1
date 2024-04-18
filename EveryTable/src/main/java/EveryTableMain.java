

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EveryTableMain
 */
@WebServlet("/EveryTableMain")
public class EveryTableMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EveryTableMain() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String user = request.getParameter("userName");
		String password = request.getParameter("passWord");
		
		Date currentDate = new Date();
		String currentDateActual = ("" + currentDate.getMonth() + 1) + "/" + currentDate.getDate() + "/" + (currentDate.getYear() + 1900);
		
		
		if(2024 == (currentDate.getYear() +1900))
		{
			if(04 > (currentDate.getMonth() + 1))
			{
				if(17 > currentDate.getDate())
				{
					//Task still active
				}
				else
				{
					//Task overdue
				}
			}
			else
			{
				//Task overdue
			}
		}
		else if(2024 > (currentDate.getYear() +1900))
		{
			//Task still active
		}
		else if(2024 < (currentDate.getYear() +1900))
		{
			// Task overdue
		}
		
		String homePage = "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>EveryTable Main Page</title>\r\n"
				+ "<link rel=\"stylesheet\" href=\"StylesMain.css\">\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<form>\r\n"
				+ "	\r\n"
				+ "		<a href='Index.html'><p>Back to login page<p></a>"
				+ "		<h1>EveryTable</h1>\r\n"
				+ "		<h2>Organize all of your tasks with this dynamic web tool!</h2>\r\n"
				+ "		\r\n"
				+ "		<table>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><button><a href=\"CreateTable.html\">Create New Table...</button></a></td>"
				+ "				<td><input type=\"button\" value=\"Delete Table\"></td>\r\n"
				+ "				<td><input type=\"button\" value=\"Table Settings\"></td>\r\n" //Table settings will include features such as resizing tables and recoloring tables
				+ "             <td><p style='color:red;'>Current User:</p></td>"
				+ "             <td><p style='color:red; text-decoration: underline;'>" + user + "</p></td>"
				+ "				\r\n"
				+ "			</tr>\r\n"
				+ "		</table>	\r\n"
				+ "		<hr>\r\n";
		
		
		//User validation
		
	      Connection connection = null;
	      PreparedStatement preparedStatement = null;
	      try {
	         DBConnectionTables.getDBConnectionTables();
	         connection = DBConnectionTables.connection;

            String selectSQL = "SELECT * FROM EveryTableUsers WHERE USER_NAME = ? AND PASSWORD = ?";
            //String theEmail = "%" + keyword + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

	         ResultSet rs = preparedStatement.executeQuery();
	         
	         
	         //if(rs == null)
	        	 //System.out.println("There are no results.");
	         
	         if(rs.next() == false)
	         {
	        	 out.println("<html>"
	        			+ "<h2>Incorrect Password or UserName, please <a href='Index.html'>try again</a></h2>"
	        	 		+ "</html>");
	        	 user = "";
	        	 //System.exit(0);
	         }
	         else
	     		out.println(homePage);

	         
	         /*
	         while (rs.next()) {
	        	 //System.out.println(rs.next());
	        	 //System.out.println("This is the result HERE ----> '" + user + "'");
	            //user = rs.getString("USER_NAME").trim();
	            //password = rs.getString("PASSWORD").trim();
	            break;

	         }
	         */
	         
	         rs.close();
	         preparedStatement.close();
	         connection.close();
	      } catch (SQLException se) {
	         se.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (preparedStatement != null)
	               preparedStatement.close();
	         } catch (SQLException se2) {
	         }
	         try {
	            if (connection != null)
	               connection.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
		
		
		
		
		
		// *Page Creation*

		//if(Date.after(currentDate))

		//String user = request.getParameter("userName");
		
		//out.println("<p>User is " + user + "</p>");
	
		
	      Connection connection2 = null;
	      PreparedStatement preparedStatement2 = null;
	      try {
	         DBConnectionTables.getDBConnectionTables();
	         connection2 = DBConnectionTables.connection;


            String selectSQL = "SELECT * FROM EveryTablePrimary";
            preparedStatement2 = connection2.prepareStatement(selectSQL);
            //preparedStatement.setString();

	         ResultSet rs = preparedStatement2.executeQuery();

	         while (rs.next()) {
	             String taskName = rs.getString("TASK_NAME").trim();
	             String dueDate = rs.getString("DUE_DATE").trim();
	             String people = rs.getString("PEOPLE").trim();
	             String details = rs.getString("DETAILS").trim();
	             
	             String[] individualPeople = people.split(","); //here
	             
	             //out.println(createTables(taskName, dueDate, individualPeople[2], details));
	             
	             
	             for(int i = 0; i < individualPeople.length; i++)
	             {
	            	 if(user.equals(individualPeople[i]))
	            		 out.println(createTables(taskName, dueDate, people, details));
	             }
	             
	     		
	        	 
	         }

	         rs.close();
	         preparedStatement2.close();
	         connection2.close();
	      } catch (SQLException se) {
	         se.printStackTrace();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (preparedStatement != null)
	               preparedStatement.close();
	         } catch (SQLException se2) {
	         }
	         try {
	            if (connection != null)
	               connection.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
	      
	      out.println("		\r\n"
					+ "	</form>\r\n"
					+ "</body>");
		
		
		/*
		out.println("<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>EveryTable Main Page</title>\r\n"
				+ "<link rel=\"stylesheet\" href=\"StylesMain.css\">\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<form>\r\n"
				+ "	\r\n"
				+ "		<a href='Index.html'><p>Back to login page<p></a>"
				+ "		<h1>EveryTable</h1>\r\n"
				+ "		<h2>Organize all of your tasks with this dynamic web tool!</h2>\r\n"
				+ "		\r\n"
				+ "		<table>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><a href=\"CreateTable.html\">Create New Table...</a></td>"
				+ "				<td><input type=\"button\" value=\"Delete Table\"></td>\r\n"
				+ "				<td><input type=\"button\" value=\"Table Settings\"></td>\r\n"
				+ "				\r\n"
				+ "			</tr>\r\n"
				+ "		</table>	\r\n"
				+ "		<hr>\r\n"
				+ "		\r\n"
				+ "		<table>\r\n"
				+ "		<tr>\r\n"
				+ "			<th colspan=\"5\">*Example Table*</th>\r\n"
				+ "		</tr>		\r\n"
				+ "		<tr>\r\n"
				+ "			<td>*Task Name*</td>\r\n"
				+ "			<td>*Task Due Date*</td>\r\n"
				+ "			<td>*People Assigned To Task*</td>\r\n"
				+ "			<td>*Task Status*</td>\r\n"
				+ "			<td><input type=\"button\" value=\"Finish Task\"> <input type=\"button\" value=\"Delete task\"></td>\r\n"
				+ "		</tr>\r\n"
				+ "		<tr>\r\n"
				+ "		<td colspan=\"5\"><textarea placeholder=\"*Task Details*\"></textarea></td>\r\n"
				+ "		</tr>\r\n"
				+ "\r\n"
				+ "		</table>\r\n"
				+ "		\r\n"
				+ "	</form>\r\n"
				+ "</body>");
		
*/
		
		//Query like Get table * where people includes user
		
		

	}
	
	private String createTables(String taskName, String dueDate, String people, String details)
	{
		//taskName = "Groceries";
		//Date currentDate = new Date();
		//String currentDateActual = ("" + currentDate.getMonth() + 1) + "/" + currentDate.getDate() + "/" + (currentDate.getYear() + 1900);
		
		Date currentDate = new Date();
		String[] dateParts = dueDate.split("/");
		Boolean overdue = false;
		String status = "";
		
		//String currentDateActual = ("" + currentDate.getMonth() + 1) + "/" + currentDate.getDate() + "/" + (currentDate.getYear() + 1900);
		
		/*
		if(Integer.parseInt(dateParts[2]) == (currentDate.getYear() +1900))
		{
			System.out.println("|Boolean #1|");
			System.out.println(Integer.parseInt(dateParts[2]) == (currentDate.getYear() +1900));
			if((Integer.parseInt(dateParts[1]) > (currentDate.getMonth() + 1)))
			{
				
				System.out.println("|Boolean #2|");
				System.out.println((Integer.parseInt(dateParts[1]) > (currentDate.getMonth() + 1)));
				if(Integer.parseInt(dateParts[0]) > currentDate.getDate())
				{
					System.out.println("|Boolean #3|");
					System.out.println(Integer.parseInt(dateParts[0]) > currentDate.getDate());
					overdue = false;
					status = "On Time";
				}
				else
				{
					overdue = true;
					status = "Overdue";
				}
			}
			else
			{
				overdue = true;
				status = "Overdue";
			}
		}
		else if(Integer.parseInt(dateParts[2]) > (currentDate.getYear() +1900))
		{
			System.out.println("|Boolean #4|");
			System.out.println(Integer.parseInt(dateParts[2]) > (currentDate.getYear() +1900));
			overdue = false;
			status = "On Time";
		}
		else if(Integer.parseInt(dateParts[2]) < (currentDate.getYear() +1900))
		{
			System.out.println("|Boolean #5|");
			System.out.println(Integer.parseInt(dateParts[2]) < (currentDate.getYear() +1900));
			overdue = true;
			status = "Overdue";
		}
		
		*/
	
		String format = "<table>\r\n"
				+ "		<tr>\r\n"
				+ "			<th colspan=\"5\">"
				+ taskName
				+ "</th>\r\n"
				+ "		</tr>"
				+ "		<tr>"
				+ "			<td>"
				+ dueDate
				+ "</td>\r\n"
				+ "			<td>"
				+ people
				+ "</td>\r\n"
				+ "			<td><p name='statusText'>"
				+ status
				+ "</p></td>\r\n"
				+ "			<td><input type=\"button\" value=\"Finish Task\"> <input type=\"button\" value=\"Open Task\"></td>\r\n"
				+ "		</tr>\r\n"
				+ "		<tr>\r\n"
				+ "		<td colspan=\"5\"><textarea placeholder=\"*Task Details*\" readonly='yes'>"
				+ details
				+ "</textarea></td>\r\n"
				+ "		</tr>\r\n"
				+ "\r\n"
				+ "		</table>\r\n"
				+ "&nbsp";
		
		
		return format;
		/*
		out.println(example);
			
			
		for(int i = 0; i < 3; i++)
		{
			out.println(example);
		}
		*/
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}


