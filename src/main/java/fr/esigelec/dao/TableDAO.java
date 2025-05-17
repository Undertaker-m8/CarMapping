package fr.esigelec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import fr.esigelec.jee.VoitureClass;

public class TableDAO extends ConnectionDAO {
    
    public TableDAO() {
        super();
    }
    public ArrayList<VoitureClass> getTable(String colonneRecherche, String termeRecherche) {
        ArrayList<VoitureClass> listVoiture = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            System.out.println("Connected");

            String query;
            if (termeRecherche == null || termeRecherche.isEmpty()) {
                query = "SELECT CODGEO, LIBGEO, EPCI, LIBEPCI, DATE_ARRETE, NB_VP_RECH_ELEC, NB_VP_RECH_GAZ, NB_VP FROM voitures";
                preparedStatement = conn.prepareStatement(query);
            } else {
                query = "SELECT CODGEO, LIBGEO, EPCI, LIBEPCI, DATE_ARRETE, NB_VP_RECH_ELEC, NB_VP_RECH_GAZ, NB_VP FROM voitures "
                      + "WHERE " + colonneRecherche + " LIKE ?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, "%" + termeRecherche + "%");
            }

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
            	String codgeo = rs.getString("CODGEO");
                String libgeo = rs.getString("LIBGEO");
                String epci = rs.getString("EPCI");
                String libepci = rs.getString("LIBEPCI");
                Date date_arrete = rs.getDate("DATE_ARRETE");
                int nb_vp_rech_elec = rs.getInt("NB_VP_RECH_ELEC");
                int nb_vp_rech_gaz = rs.getInt("NB_VP_RECH_GAZ");
                int nb_vp = rs.getInt("NB_VP");

                VoitureClass voiture = new VoitureClass(codgeo, libgeo, epci, libepci, 
                    date_arrete, nb_vp_rech_elec, nb_vp_rech_gaz, nb_vp);
                listVoiture.add(voiture);
            }
            
       
    
            

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture dans l'ordre inverse de création
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur fermeture ResultSet: " + e.getMessage());
            }
            
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur fermeture PreparedStatement: " + e.getMessage());
            }
            
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur fermeture Connection: " + e.getMessage());
            }
        }
        
        return listVoiture;
    }

}
