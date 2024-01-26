package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, Integer>
{
    Model findById(int id_model);
}