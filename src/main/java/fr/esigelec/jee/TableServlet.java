package fr.esigelec.jee;

import java.io.IOException;
import java.util.ArrayList;

import fr.esigelec.jee.dao.TableDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Table
 */
@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//initialise la dao créée pour la gestion de la table
		TableDAO tableDAO = new TableDAO();
		ArrayList<VoitureClass> listVoiture = new ArrayList<VoitureClass>();
		
		
		listVoiture = tableDAO.getTable(null,null);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("listVoiture", listVoiture);
		request.getRequestDispatcher("/TablePage.jsp").forward(request, response);
		
	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//initialise la dao créée pour la gestion de la table
		TableDAO tableDAO = new TableDAO();
		ArrayList<VoitureClass> listVoiture = new ArrayList<VoitureClass>();
		
		/*récupère la valeur du champ de recherche et de l'échelle de sélection 
		  basée sur le nom de la colonne*/
		String echelle = request.getParameter("scale");
		String recherche = request.getParameter("recherche");
		
		System.out.println(echelle + " " + recherche);
		
		listVoiture = tableDAO.getTable(echelle,recherche);
				
				
		HttpSession session = request.getSession();
		session.setAttribute("listVoiture", listVoiture);
		request.getRequestDispatcher("/TablePage.jsp").forward(request, response);
				
	
	
	}
	
	
  
}
