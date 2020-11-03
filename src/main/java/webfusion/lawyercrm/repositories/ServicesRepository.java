package webfusion.lawyercrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.Services;

import java.util.LinkedList;

@Repository
public interface ServicesRepository extends CrudRepository<Services, Integer> {
    LinkedList<Services> findAll();
}
