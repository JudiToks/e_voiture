package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Statement;

@Entity
public class Transmission
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_transmission;
    private String nom;

    //    getters & setters
    public int getId_transmission() {
        return id_transmission;
    }
    public void setId_transmission(int id_transmission) {
        this.id_transmission = id_transmission;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    //    constructor
    protected Transmission() {}
    public Transmission(int id_transmission, String nom) {
        this.id_transmission = id_transmission;
        this.nom = nom;
    }

    //    utils
    public String toString()
    {
        return String.format("Transmission[id_transmission=%d, nom=%s]", id_transmission, nom);
    }

    public static void update(Connection connection, int id_transmission, String nom)
    {
        boolean isOuvert = false;
        String query = "update transmission set nom = '"+nom+"' where id_transmission = "+id_transmission+";";
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
            System.out.println("Transmission update issues");
            e.printStackTrace();
        }
    }
}

