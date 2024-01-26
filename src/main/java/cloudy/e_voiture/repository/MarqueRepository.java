package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Marque;
import org.springframework.data.repository.CrudRepository;

public interface MarqueRepository extends CrudRepository<Marque, Integer>
{
    Marque findById(int id_marque);
}
