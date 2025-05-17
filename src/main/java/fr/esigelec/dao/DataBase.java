package fr.esigelec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.InputStream;
import java.util.Properties;

import fr.esigelec.java.Voiture;

public class DataBase {
    private static String url;
    private static String login;
    private static String password;

    // Chargement des propriétés au démarrage
    static {
        loadDatabaseProperties();
    }

    private static void loadDatabaseProperties() {
        try (InputStream input = DataBase.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            
            Properties prop = new Properties();
            
            if (input == null) {
                throw new RuntimeException("Fichier database.properties non trouvé");
            }
            
            prop.load(input);
            
            // Récupération des propriétés
            url = prop.getProperty("db.url");
            login = prop.getProperty("db.username");
            password = prop.getProperty("db.password");
            
            // Chargement du driver
            Class.forName(prop.getProperty("db.driver"));
            
        } catch (Exception e) {
            throw new RuntimeException("Erreur de chargement de la configuration de la base de données", e);
        }
    }

    public Map<String, List<Voiture>> connection(boolean v_elec, boolean v_gaz) {
        System.out.println("v_elec = " + v_elec + " and v_gaz = " + v_gaz);
        Connection con = null;
        List<Voiture> cars = new ArrayList<>();
        Map<String, List<Voiture>> hash = new HashMap<String, List<Voiture>>();
        
        try {
            con = DriverManager.getConnection(url, login, password);
            System.out.println("you are connected");
            
            String requete;
            if(v_elec == true && v_gaz == false) {
                requete = "WHERE VOITURES.NB_VP_RECH_ELEC != 0";
            }
            else if(v_gaz == true && v_elec == false) {
                requete="WHERE VOITURES.NB_VP_RECH_GAZ != 0";
            }
            else if(v_elec == true && v_gaz == true) {
                requete="WHERE VOITURES.NB_VP_RECH_ELEC != 0 or VOITURES.NB_VP_RECH_GAZ != 0";
            }
            else {
                requete="";
            }
            
            Statement state = con.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM VOITURES INNER JOIN COORDONNEE ON VOITURES.CODGEO = COORDONNEE.code_commune_INSEE " + requete + " LIMIT 3000");
            
            while (result.next()) {
                String codePostale = result.getString("CODGEO");
                String nomVille = result.getString("LIBGEO");
                String lattitude = result.getString("lattitude");
                String longitude = result.getString("longitude");
                
                Voiture voit = new Voiture(codePostale, nomVille, lattitude, longitude);
                
                if(hash.containsKey(nomVille)) { 
                    hash.get(nomVille).add(voit);
                } else {
                    List<Voiture> code = new ArrayList<>();
                    code.add(voit);
                    hash.put(nomVille, code);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        }
        return hash;
    }
}