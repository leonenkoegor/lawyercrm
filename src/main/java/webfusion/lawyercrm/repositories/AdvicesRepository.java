package webfusion.lawyercrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.Advices;

import java.util.List;

@Repository
public interface AdvicesRepository extends CrudRepository<Advices, Integer> {

    List<Advices> findAll();

}
