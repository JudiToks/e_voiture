package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import jakarta.persistence.*;

import java.sql.Connection;
import java.sql.Statement;

@Entity
@Table(name = "modeles")
public class Model
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_model;
    private String nom;

    //    getters & setters
    public int getId_model() {
        return id_model;
    }
    public void setId_model(int id_model) {
        this.id_model = id_model;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    //    constructor
    protected Model() {}
    public Model(int id_model, String nom) {
        this.id_model = id_model;
        this.nom = nom;
    }

    //    utils
    public String toString()
    {
        return String.format("Model[id_model=%d, nom=%s]", id_model, nom);
    }

    public static void update(Connection connection, int id_model, String nom)
    {
        boolean isOuvert = false;
        String query = "update modeles set nom = '"+nom+"' where id_model = "+id_model+";";
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
            System.out.println("Model update issues");
            e.printStackTrace();
        }
    }
}