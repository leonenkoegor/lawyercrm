package webfusion.lawyercrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.News;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<News, Integer> {

    List<News> findAll();

}
