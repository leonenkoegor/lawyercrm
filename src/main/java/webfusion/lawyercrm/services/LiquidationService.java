package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.Liquidation;
import webfusion.lawyercrm.repositories.LiquidationRepository;
import webfusion.lawyercrm.services.exceptions.LiquidationPageNotFoundException;

import java.util.Optional;

@Service
public class LiquidationService {
    @Autowired
    private LiquidationRepository liquidRepository;

    public Liquidation findById(Long id) throws LiquidationPageNotFoundException {
        Optional<Liquidation> liquidation = liquidRepository.findById(id);
        if (!liquidation.isPresent()) {
            throw new LiquidationPageNotFoundException();
        }
        return liquidation.get();
    }

    public Liquidation update(Liquidation liquidation) {
        return liquidRepository.save(liquidation);
    }

    public void delete(Liquidation liquidation) {
        liquidRepository.delete(liquidation);
    }
}
