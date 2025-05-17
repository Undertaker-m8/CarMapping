package fr.esigelec.jee;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import java.util.List;

import fr.esigelec.dao.DataBase;
import fr.esigelec.java.Voiture;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MapServlet
 */
@WebServlet("/Map")
public class MapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] selectVoitureElec = request.getParameterValues("v_elec");
		String[] selectVoitureGaz = request.getParameterValues("v_gaz");
		DataBase data = new DataBase();
		boolean isElec = selectVoitureElec != null && selectVoitureElec.length >0 ? true : false;
		boolean isGaz = selectVoitureGaz != null && selectVoitureGaz.length >0 ? true : false;
		System.out.println("v_elec = " + Arrays.toString(selectVoitureElec) + " and v_gaz = " + Arrays.toString(selectVoitureGaz));
		Map<String,List<Voiture>> cars = data.connection(isElec,isGaz);
		request.setAttribute("cars", cars);
		//System.out.println("hello");
		request.getRequestDispatcher("Map.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
		

}
