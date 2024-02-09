package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Favoris
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_favoris;
    private int id_annonce;
    private int id_user;

//    getters & setters
    public int getId_favoris() {
        return id_favoris;
    }

    public void setId_favoris(int id_favoris) {
        this.id_favoris = id_favoris;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

//    function
    public static List<AnnonceUser> getFavoris(Connection connection, int id_annonce, int id_user)
    {
        boolean isOuvert = false;
        List<AnnonceUser> valiny = new ArrayList<>();
        String query = "select\n" +
                "    *\n" +
                "from Favoris\n" +
                "    join v_annonces va on Favoris.Id_Annonce = va.Id_Annonce and Favoris.Id_User = va.Id_User\n" +
                "where Favoris.Id_Annonce = "+id_annonce+" and Favoris.Id_User = "+id_user+";";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                AnnonceUser temp = new AnnonceUser();
                temp.setId_annonce(resultSet.getInt("id_annonce"));
                temp.setDescription(resultSet.getString("description"));
                temp.setDate_annonce(resultSet.getDate("date_annonce"));
                temp.setConso(resultSet.getDouble("conso"));
                temp.setAnnee(resultSet.getInt("annee"));
                temp.setKilometrage(resultSet.getDouble("kilometrage"));
                temp.setNbr_place(resultSet.getInt("nbr_place"));
                temp.setNbr_porte(resultSet.getInt("nbr_porte"));
                temp.setCarburant(resultSet.getString("nom_carburant"));
                temp.setCategorie(resultSet.getString("nom_categorie"));
                temp.setCouleur(resultSet.getString("nom_couleur"));
                temp.setMarque(resultSet.getString("nom_marque"));
                temp.setModeles(resultSet.getString("nom_modele"));
                temp.setMoteur(resultSet.getString("nom_moteur"));
                temp.setTransmission(resultSet.getString("nom_transmission"));
                temp.setNom_etat(resultSet.getString("nom_etat"));
                temp.setPrix(resultSet.getDouble("prix"));
                temp.setEtat(resultSet.getInt("etat"));
                DetailsAnnonce temp1 = new DetailsAnnonce();
                temp.setDetailsAnnonce(temp1.getAllDetailsByIdAnnonce(connection, temp.getId_annonce()));
                valiny.add(temp);
            }
            resultSet.close();
            statement.close();
            if (isOuvert)
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("Favoris getFavoris issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
