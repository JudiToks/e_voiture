package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Statement;

@Entity
public class Marque
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_marque;
    private String nom;

    //    getters & setters
    public int getId_marque() {
        return id_marque;
    }
    public void setId_marque(int id_marque) {
        this.id_marque = id_marque;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    //    constructor
    protected Marque() {}
    public Marque(int id_marque, String nom) {
        this.id_marque = id_marque;
        this.nom = nom;
    }

    //    utils
    public String toString()
    {
        return String.format("Marque[id_marque=%d, nom=%s]", id_marque, nom);
    }

    public static void update(Connection connection, int id_marque, String nom)
    {
        boolean isOuvert = false;
        String query = "update marque set id_marque = "+id_marque+" where nom = '"+nom+"';";
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
            System.out.println("Manque update issues");
            e.printStackTrace();
        }
    }
}

