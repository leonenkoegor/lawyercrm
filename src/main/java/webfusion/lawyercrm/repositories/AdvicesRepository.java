package webfusion.lawyercrm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.Advices;

@Repository
public interface AdvicesRepository extends PagingAndSortingRepository<Advices, Integer> {

    @Query("SELECT n FROM advices n ORDER BY n.date DESC")
    Page<Advices> findAllOrderedByDateDesc(Pageable pageable);

}
