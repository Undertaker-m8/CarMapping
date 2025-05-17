<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="fr.esigelec.jee.VoitureClass"%>
<%@ page import="fr.esigelec.jee.TableServlet"%>

<%@ page import="java.util.ArrayList"%>
    
    
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #fff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .navbar-brand, .nav-link {
            color: #333;
            font-weight: bold;
        }
        .dashboard-container {
            max-width: 1200px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .dashboard-header {
            text-align: center;
            margin-bottom: 30px;
        }
        .dashboard-header h1 {
            color: #333;
            font-size: 2.5rem;
        }
        .filter-section {
            margin-bottom: 30px;
        }
        .filter-section label {
            font-weight: bold;
            margin-right: 10px;
        }
        .filter-section select, .filter-section input {
            padding: 5px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        .dashboard-table {
            margin-top: 30px;
        }
        .dashboard-table h2 {
            color: #333;
            font-size: 1.8rem;
            margin-bottom: 20px;
        }
        .table {
            width: 100%;
            border-collapse: collapse;
        }
        .table th, .table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .table th {
            background-color: #f8f9fa;
        }
    </style>
</head>

<body>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
			 <img src="logo_projet_map.jpg" alt="Logo" style="height : 50px;">	
			</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                       <form  action="Table" method="GET">
                       		<a class="nav-link" href="TableServlet">Tableaux de bord</a>
                       </form>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Map">Map</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="dashboard-container">
        <div class="dashboard-header">
            <h1>Tableau de bord</h1>
        </div>
        
        <form action="TableServlet" method="POST">
        <div class="filter-section">
            <label for="scale">Sélection de l'échelle:</label>
            <select id="scale" name="scale">
                <option value="LIBGEO">Commune</option>
                <option value="LIBEPCI">Communauté de commune</option>
            </select>
            <span style="margin-left: 20px;">Recherche par : Ville</span>
            <input type="text" placeholder="Recherche..." id="recherche" name="recherche" style="margin-left: 20px;">
        
        	<button type="submit">Rechercher</button>
        </div>
        
        </form>
        
        <% ArrayList<VoitureClass> listVoiture = (ArrayList<VoitureClass>) session.getAttribute("listVoiture");
		   if(listVoiture == null) {
			   listVoiture = new ArrayList<VoitureClass>();
		   }
 		%>
 		<p><%= listVoiture.size()%></p>
 		
        <div class="dashboard-table">
            <h2>Tableau du bord</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>CODGEO</th>
                        <th>LIBGEO</th>
                        <th>EPCI</th>
                        <th>LIBEPCI</th>
                        <th>DATE_ARRETE</th>
                        <th>NB_VP_RECH_ELEC</th>
                        <th>NB_VP_RECH_GAZ</th>
                        <th>NB_VP</th>
                    </tr>
                </thead>
                <tbody>
                    <% if(listVoiture.size()>0){
                        	for(int i=0;i<100;i++) {
                    %>
                    <tr>
	                        <th><%= listVoiture.get(i).getCodgeo()%></th>
	                        <th><%= listVoiture.get(i).getLibgeo()%></th>
	                        <th><%= listVoiture.get(i).getEpci()%></th>
	                        <th><%= listVoiture.get(i).getLibepci()%></th>
	                        <th><%= listVoiture.get(i).getDate_arrete()%></th>
	                        <th><%= listVoiture.get(i).getNb_vp_rech_elec()%></th>
	                        <th><%= listVoiture.get(i).getNb_vp_rech_gaz()%></th>
	                        <th><%= listVoiture.get(i).getNb_vp()%></th> 	 
                    </tr>
                    
                    <%}
                    	
                    }%>
                    
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>