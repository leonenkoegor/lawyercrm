package webfusion.lawyercrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
