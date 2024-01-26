package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Transmission;
import org.springframework.data.repository.CrudRepository;

public interface TransmissionRepository extends CrudRepository<Transmission, Integer>
{
    Transmission findById(int id_transmission);
}

