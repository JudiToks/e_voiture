package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnnonceUser
{
    private String sql;
    private int id_annonce;
    private String description;
    private int nbr_place;
    private int nbr_porte;
    private String nom_etat;
    private double kilometrage;
    private double conso;
    private Date date_annonce;
    private int annee;
    private String user;
    private String carburant;
    private String transmission;
    private String moteur;
    private String categorie;
    private String couleur;
    private String modeles;
    private String marque;
    private double prix;
    private DetailsAnnonce detailsAnnonce;
    private int etat;

//    getters & setters
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public int getNbr_porte() {
        return nbr_porte;
    }

    public void setNbr_porte(int nbr_porte) {
        this.nbr_porte = nbr_porte;
    }

    public String getNom_etat() {
        return nom_etat;
    }

    public void setNom_etat(String nom_etat) {
        this.nom_etat = nom_etat;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public double getConso() {
        return conso;
    }

    public void setConso(double conso) {
        this.conso = conso;
    }

    public Date getDate_annonce() {
        return date_annonce;
    }

    public void setDate_annonce(Date date_annonce) {
        this.date_annonce = date_annonce;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCarburant() {
        return carburant;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getMoteur() {
        return moteur;
    }

    public void setMoteur(String nom_moteur) {
        this.moteur = nom_moteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getModeles() {
        return modeles;
    }

    public void setModeles(String modeles) {
        this.modeles = modeles;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public DetailsAnnonce getDetailsAnnonce() {
        return detailsAnnonce;
    }

    public void setDetailsAnnonce(DetailsAnnonce detailsAnnonce) {
        this.detailsAnnonce = detailsAnnonce;
    }

    public static List<AnnonceUser> getAllAnnonceUserByIdUser(Connection connection, int id_user)
    {
        boolean isOuvert = false;
        List<AnnonceUser> valiny = new ArrayList<>();
        String query = "select * from v_annonces where id_user="+id_user;
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
            System.out.println("AnnonceUser getAllAnnonceUserByIdUser issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<AnnonceUser> findAllAnnonceValider(Connection connection)
    {
        boolean isOuvert = false;
        List<AnnonceUser> valiny = new ArrayList<>();
        String query = "select * from v_annonces where etat between 10 and 19";
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
            System.out.println("AnnonceUser findAllAnnonceValider issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<AnnonceUser> findAllAnnonceNonValider(Connection connection)
    {
        boolean isOuvert = false;
        List<AnnonceUser> valiny = new ArrayList<>();
        String query = "select * from v_annonces where etat between 0 and 9 order by date_annonce;";
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
            System.out.println("AnnonceUser findAllAnnonce issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<AnnonceUser> findAllAnnonceNonValiderById(Connection connection, int id_annonce)
    {
        boolean isOuvert = false;
        List<AnnonceUser> valiny = new ArrayList<>();
        String query = "select * from v_annonces where id_annonce = "+id_annonce+";";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next())
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
            System.out.println("AnnonceUser findAllAnnonce issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<AnnonceUser> recherche(Connection connection, String marque, String model, String carburant, String moteur, String transmission, String couleur)
    {
        boolean isOuvert = false;
        List<AnnonceUser> valiny = new ArrayList<>();
        String query = "create or replace view v_recherche as " +
                "select\n" +
                "    *\n" +
                "from v_annonces\n" +
                "    where nom_marque ilike '%"+marque+"%'\n" +
                "      and nom_modele ilike '%"+model+"%'\n" +
                "      and nom_carburant ilike '%"+carburant+"%'\n" +
                "      and nom_moteur ilike '%"+moteur+"%'\n" +
                "      and nom_transmission ilike '%"+transmission+"%'\n" +
                "      and nom_couleur ilike '%"+couleur+"%'";
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
            System.out.println("AnnonceUser recherche issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static List<AnnonceUser> rechercheAvance(Connection connection, double[] tranche_prix, String categorie, double[] annees)
    {
        boolean isOuvert = false;
        List<AnnonceUser> valiny = new ArrayList<>();
        String query = "with resultSearch as (\n" +
                "    select\n" +
                "        *\n" +
                "    from v_recherche\n" +
                ")\n" +
                "select\n" +
                "    *\n" +
                "from resultSearch\n" +
                "where (prix between "+tranche_prix[0]+" and "+tranche_prix[1]+") \n" +
                "  and nom_categorie ilike '%"+categorie+"%' \n" +
                "  and (annee between "+annees[0]+" and "+annees[1]+");";
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
            System.out.println("AnnonceUser rechercheAvance issues");
            e.printStackTrace();
        }
        return valiny;
    }


}
