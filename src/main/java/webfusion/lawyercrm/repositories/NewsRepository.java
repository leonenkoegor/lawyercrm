package webfusion.lawyercrm.repositories;

import org.springframework.data.repository.CrudRepository;
import webfusion.lawyercrm.models.News;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Integer> {
    List<News> findAll();
}
