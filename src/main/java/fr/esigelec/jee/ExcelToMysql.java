package fr.esigelec.jee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class ExcelToMysql {
    public static void main(String[] args) {
        // Charger la configuration depuis le fichier properties
        Properties config = loadConfig();
        
        String cheminFichier = config.getProperty("csv.path");
        String jdbcURL = config.getProperty("db.url");
        String username = config.getProperty("db.username");
        String password = config.getProperty("db.password");

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {

            // Charger le pilote JDBC MySQL
            Class.forName(config.getProperty("db.driver"));

            String ligne;
            boolean firstLine = true;
            String sql = "INSERT INTO voitures (CODGEO, LIBGEO, EPCI, LIBEPCI, DATE_ARRETE, NB_VP_RECH_ELEC, NB_VP_RECH_GAZ, NB_VP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            int batchSize = 0;
            final int MAX_BATCH_SIZE = 1000;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                connection.setAutoCommit(false); // Désactiver l'auto-commit

                while ((ligne = br.readLine()) != null) {
                    if (firstLine) { 
                        firstLine = false;
                        continue;
                    }

                    String[] colonnes = ligne.split(";");
                    if (colonnes.length < 8) {
                        System.err.println("Ligne ignorée: " + ligne);
                        continue;
                    }

                    try {
                        preparedStatement.setString(1, colonnes[0].trim());
                        preparedStatement.setString(2, colonnes[1].trim());
                        preparedStatement.setString(3, colonnes[2].trim());
                        preparedStatement.setString(4, colonnes[3].trim());
                        preparedStatement.setString(5, colonnes[4].trim());
                        preparedStatement.setInt(6, Integer.parseInt(colonnes[5].trim()));
                        preparedStatement.setInt(7, Integer.parseInt(colonnes[6].trim()));
                        preparedStatement.setInt(8, Integer.parseInt(colonnes[7].trim()));

                        preparedStatement.addBatch();
                        batchSize++;

                        // Exécuter le batch par lots
                        if (batchSize % MAX_BATCH_SIZE == 0) {
                            preparedStatement.executeBatch();
                            connection.commit();
                            System.out.println("Lot de " + MAX_BATCH_SIZE + " lignes insérées");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Erreur de format dans la ligne: " + ligne);
                    }
                }

                // Exécuter le reste des requêtes
                preparedStatement.executeBatch();
                connection.commit();
                System.out.println("Import terminé avec succès !");

            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Erreur lors de l'import - rollback effectué");
                throw e;
            }

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Properties loadConfig() {
        Properties prop = new Properties();
        try (InputStream input = ExcelToMysql.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            
            if (input == null) {
                throw new RuntimeException("Fichier database.properties non trouvé");
            }
            prop.load(input);
            return prop;
            
        } catch (IOException e) {
            throw new RuntimeException("Erreur de lecture du fichier de configuration", e);
        }
    }
}