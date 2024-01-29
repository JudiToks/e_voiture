package cloudy.e_voiture.models;

import cloudy.e_voiture.models.connect.Connect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsAnnonce
{
    private int id_annonce;
    private String[] urls;

        public void insert(Connection connection) throws SQLException {
        boolean isOuvert = false;
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            String sql = "INSERT INTO details_annonce (id_annonce, url_image) VALUES ";
            for (String url: getUrls()){
                sql+="("+getId_annonce()+",'"+url+"'),";
            }
            sql=sql.substring(0,sql.length()-1);
            System.out.println(sql);
            Statement statement= connection.createStatement();
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            System.out.println("Insertion "+this.getClass().getSimpleName()+" issues");
            e.printStackTrace();
        }
        finally
        {
            if (isOuvert)
            {
                connection.close();
            }
        }
    }

    public DetailsAnnonce getAllDetailsByIdAnnonce(Connection connection, int id_annonce)
    {
        boolean isOuvert = false;
        DetailsAnnonce detailsAnnonce= new DetailsAnnonce();
        String query = "select * from v_image where id_annonce="+id_annonce;
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<String> urls= new ArrayList<>();
            while (resultSet.next())
            {
                String url= resultSet.getString("url_image");
                urls.add(url);
            }
            detailsAnnonce.setUrls(urls.toArray(new String[0]));
            resultSet.close();
            statement.close();
            if (isOuvert)
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return detailsAnnonce;
    }
}
