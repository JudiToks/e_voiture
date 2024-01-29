package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Statistique
{
    private String nom;
    private int nombre;
    private double montant;
    private String mois;
    private int annee;

//    getters & setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

//    function
    public static List<Statistique> findBestVendeur(Connection connection)
    {
        boolean isOuvert = false;
        List<Statistique> valiny = new ArrayList<>();
        String query = "select\n" +
                "    u.nom,\n" +
                "    count(v.Id_Annonce) as nombre\n" +
                "from Vendu v\n" +
                "    join Annonce a on a.Id_Annonce = v.Id_Annonce\n" +
                "    join Utilisateur u on u.Id_User = a.Id_User\n" +
                "group by u.nom\n" +
                "order by nombre desc\n" +
                "limit 7;";
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
                Statistique temp = new Statistique();
                temp.setNom(resultSet.getString(1));
                temp.setNombre(resultSet.getInt(2));
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
            System.out.println("Statistique findBestVendeur issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<Statistique> findBestMarqueVendu(Connection connection)
    {
        boolean isOuvert = false;
        List<Statistique> valiny = new ArrayList<>();
        String query = "select\n" +
                "    nom,\n" +
                "    count(v.Id_Annonce) as nombre\n" +
                "from Vendu v\n" +
                "         join Annonce a on a.Id_Annonce = v.Id_Annonce\n" +
                "         join marque m on m.id_marque = a.id_marque\n" +
                "group by nom\n" +
                "order by nombre desc\n" +
                "limit 7;";
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
                Statistique temp = new Statistique();
                temp.setNom(resultSet.getString(1));
                temp.setNombre(resultSet.getInt(2));
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
            System.out.println("Statistique findBestMarqueVendu issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<Statistique> findStatCommission(Connection connection)
    {
        boolean isOuvert = false;
        List<Statistique> valiny = new ArrayList<>();
        String query = "SELECT\n" +
                "    SUM(montant) AS total_montant,\n" +
                "    EXTRACT(MONTH FROM date_recu) AS mois,\n" +
                "    EXTRACT(YEAR FROM date_recu) AS annee\n" +
                "FROM compte_commissionaire\n" +
                "GROUP BY mois, annee\n" +
                "ORDER BY annee, mois\n" +
                "LIMIT 12;";
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
                Statistique temp = new Statistique();
                temp.setMontant(resultSet.getDouble(1));
                temp.setMois(resultSet.getString(2));
                temp.setAnnee(resultSet.getInt(3));
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
            System.out.println("Statistique findStatCommission issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
