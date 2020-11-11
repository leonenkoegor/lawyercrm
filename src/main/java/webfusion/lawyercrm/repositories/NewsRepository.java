package webfusion.lawyercrm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.News;

@Repository
public interface NewsRepository extends PagingAndSortingRepository<News, Integer> {

    @Query("SELECT n FROM news n ORDER BY n.date DESC")
    Page<News> findAllOrderedByDateDesc(Pageable pageable);

}
