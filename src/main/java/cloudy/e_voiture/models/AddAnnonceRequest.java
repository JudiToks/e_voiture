package cloudy.e_voiture.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAnnonceRequest
{
    private List<Marque> marque;
    private List<Model> model;
    private List<Couleur> couleur;
    private List<Categorie> categorie;
    private List<Moteur> moteur;
    private List<Transmission> transmission;
    private List<Carburant> carburant;
}
