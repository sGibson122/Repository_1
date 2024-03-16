package pkg2;

import java.io.PrintWriter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class calculateIncome
 */
@WebServlet("/calculateIncome")
public class calculateIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calculateIncome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String desiredField = request.getParameter("field");
		int desiredHours = Integer.parseInt(request.getParameter("hours"));
		
		int estimatedIncomeHours, estimatedIncomeDays, estimatedIncomeWeeks;
		
		estimatedIncomeHours = 0;
		estimatedIncomeDays = 0;
		estimatedIncomeWeeks = 0;
		
		
		String[] listrFields = {"Botany", "Dentistry", "Paleontology", "Anesthesiology", "Optimology", "Audiology"};
		int[] incomes = {14, 17, 23, 7, 12, 15};
		
		for(int i = 0; i < listrFields.length; i++)
		{
			
			//response.getWriter().append("Try #" + i);
			//response.getWriter().append("" + desiredField + listrFields[i]);
			//response.getWriter().append(String.valueOf(desiredField == listrFields[i]));
			if(desiredField.equals(listrFields[i]))
			{
				
				estimatedIncomeHours = incomes[i];
				break;
			}
		}
		
		estimatedIncomeDays = estimatedIncomeHours * desiredHours;
		estimatedIncomeWeeks = estimatedIncomeDays * 5;
		
		// Make sure the above works
		// Use the rest of this space to create a page that displays the information.
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>"
				+ "<head>"
				+ "<body>"
				+ "<form action='insertToSQL' method='POST'>"
				+ "<h3>Good evening " + userName + ", your estimated revenue for your field in " + desiredField + " after working " + desiredHours 
				+ " hours will be $" + estimatedIncomeHours + " for every hour, $" + estimatedIncomeDays + " for every day, and $"
				+ estimatedIncomeWeeks + " for every week. \nIs this to your liking? If so, please input your information into the database to finalize your position.</h3>");
		
		out.println("<br>User Name: <input type='text' name='userName' value='" + userName + "' readonly='true'>\n"
				+ "Desired Field: <input type='text' name='desiredField' value='" + desiredField + "' readonly='true'>\n"
				+ "Desired Hours: <input type='text' name='desiredHours' value='" + desiredHours + "' readonly='true'>\n"
				+ "Estimated Weekly Income: <input type='text' name=estimatedIncome value='" + estimatedIncomeWeeks + "' readonly='true'>\n"
				+ "<input type='submit' value='Input Information Into Database!'>"
				+ "</form>"
				+ "</body>"
				+ "</head>"
				+ "</html>"
				
				);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
