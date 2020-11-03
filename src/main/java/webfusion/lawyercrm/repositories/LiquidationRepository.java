package webfusion.lawyercrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.Liquidation;

@Repository
public interface LiquidationRepository extends JpaRepository<Liquidation, Long> {
}
