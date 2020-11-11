package webfusion.lawyercrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.ServicesDescription;

import java.util.List;

@Repository
public interface ServicesDescriptionRepository extends CrudRepository<ServicesDescription, Integer> {

    List<ServicesDescription> findAll();

}
