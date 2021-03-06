package scriptures;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scriptures.model.FileScriptureHandler;
import scriptures.model.HardCodedScriptureHandler;
import scriptures.model.ScriptureDataHandler;

/**
 * Servlet implementation class ShowList
 */
@WebServlet("/ShowList")
public class ShowList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ScriptureDataHandler handler = new FileScriptureHandler("c:\\temp\\list.txt");
		
		// Pass as attribute
		request.setAttribute("scriptures", handler.getFavoriteScriptures());
		
		// Transfer control
		request.getRequestDispatcher("scriptureList.jsp").forward(request, response);
	}
}
