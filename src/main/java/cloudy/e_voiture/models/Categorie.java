package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Statement;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id_Categorie ;
    private String nom;

    public Categorie(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return String.format(
                "Categorie[Id_Categorie=%d, nom='%s']",
                Id_Categorie, nom);
    }

    protected Categorie(){}

    public int getId_Categorie() {
        return Id_Categorie;
    }

    public void setId_Categorie(int id_Categorie) {
        Id_Categorie = id_Categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static void update(Connection connection, int id_Categorie, String nom)
    {
        boolean isOuvert = false;
        String query = "update categorie set nom = '"+nom+"' where id_categorie = "+id_Categorie+";";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(query);
            statement.close();
            if (isOuvert)
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("Categorie update issues");
            e.printStackTrace();
        }
    }
}
