package cloudy.e_voiture.models;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pct_commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id_pct_commission;
    private int pourcentage;
    private Timestamp date_maj;



    protected Pct_commission() {
    }

    public Pct_commission(int pourcentage, Timestamp date_maj) {
        this.pourcentage = pourcentage;
        this.date_maj = date_maj;
    }


    @Override
    public String toString() {
        return String.format("Pct_commission[Id_Pct_commission=%d,pourcentage=%d,date_maj=%s]",
                Id_pct_commission,pourcentage);
    }

    public int getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }
    public Timestamp getDate_maj() {
        return date_maj;
    }
    public void setDate_maj(Timestamp date_maj) {
        this.date_maj = date_maj;
    }
    public int getId_pct_commission() {
        return Id_pct_commission;
    }
    public void setId_pct_commission(int id_pct_commission) {
        Id_pct_commission = id_pct_commission;
    }


}

