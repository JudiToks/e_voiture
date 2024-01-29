package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Statement;

@Entity
public class Couleur {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id_Couleur ;
    private String nom;

    public Couleur(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return String.format(
                "Couleur[Id_Couleur=%d, nom='%s']",
                Id_Couleur, nom);
    }

    protected Couleur(){}

    public int getId_Couleur() {
        return Id_Couleur;
    }

    public void setId_Couleur(int id_Couleur) {
        Id_Couleur = id_Couleur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static void update(Connection connection, int id_couleur, String nom)
    {
        boolean isOuvert = false;
        String query = "update couleur set nom = '"+nom+"' where id_couleur = "+id_couleur+";";
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
            System.out.println("Couleur update issues");
            e.printStackTrace();
        }
    }

}
