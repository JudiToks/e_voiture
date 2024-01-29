package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Statement;

@Entity
public class Carburant {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id_Carburant ;
    private String nom;

    public Carburant(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return String.format(
                "Carburant[Id_Carburant=%d, nom='%s']",
                Id_Carburant, nom);
    }

    protected Carburant(){}

    public int getId_Carburant() {
        return Id_Carburant;
    }

    public void setId_Carburant(int id_Carburant) {
        Id_Carburant = id_Carburant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static void update(Connection connection, int id_carburant, String nom)
    {
        boolean isOuvert = false;
        String query = "update carburant set id_carburant = "+id_carburant+" where nom = '"+nom+"';";
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
            System.out.println("Carburant update issues");
            e.printStackTrace();
        }
    }

}
