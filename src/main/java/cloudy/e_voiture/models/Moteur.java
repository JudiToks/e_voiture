package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Connection;
import java.sql.Statement;

@Entity
public class Moteur
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_moteur;
    private String nom;

    //    getters & setters
    public int getId_moteur() {
        return id_moteur;
    }
    public void setId_moteur(int id_moteur) {
        this.id_moteur = id_moteur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    //    constructor
    protected Moteur() {}
    public Moteur(int id_model, String nom) {
        this.id_moteur = id_model;
        this.nom = nom;
    }

    //    utils
    public String toString()
    {
        return String.format("Moteur[id_moteur=%d, nom=%s]", id_moteur, nom);
    }

    public static void update(Connection connection, int id_moteur, String nom)
    {
        boolean isOuvert = false;
        String query = "update moteur set nom = '"+nom+"' where id_moteur = "+id_moteur+";";
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
            System.out.println("Moteur update issues");
            e.printStackTrace();
        }
    }
}
