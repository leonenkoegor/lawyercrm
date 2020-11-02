package webfusion.lawyercrm.repositories;

import org.springframework.data.repository.CrudRepository;
import webfusion.lawyercrm.models.News;

import java.util.Collection;

public interface NewsRepository extends CrudRepository<News, Integer> {
    Collection<News> findAll();
}
