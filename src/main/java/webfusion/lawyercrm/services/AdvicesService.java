package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.Advices;
import webfusion.lawyercrm.repositories.AdvicesRepository;

import java.util.List;

@Service
public class AdvicesService {

    private final AdvicesRepository advicesRepository;

    @Autowired
    public AdvicesService(AdvicesRepository advicesRepository) {
        this.advicesRepository = advicesRepository;
    }

    public List<Advices> findAll() {
        return advicesRepository.findAll();
    }

    public void update(Advices advices) {
        advicesRepository.save(advices);
    }

    public void delete(Advices advices) {
        advicesRepository.delete(advices);
    }

}
