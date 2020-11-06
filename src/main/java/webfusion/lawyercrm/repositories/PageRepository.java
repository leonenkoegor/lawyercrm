package webfusion.lawyercrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, String> {

}
