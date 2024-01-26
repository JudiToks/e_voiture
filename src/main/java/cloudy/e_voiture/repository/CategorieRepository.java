package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Categorie;
import org.springframework.data.repository.CrudRepository;

public interface CategorieRepository extends CrudRepository<Categorie,Integer> {

    Categorie findById(int id_Categorie);

}
