package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Pct_commission;
import org.springframework.data.repository.CrudRepository;

public interface Pct_commissionRepository extends CrudRepository<Pct_commission,Integer> {

    Pct_commission findById(int id_Pct_commission);

}

