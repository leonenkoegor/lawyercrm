package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.Advices;
import webfusion.lawyercrm.repositories.AdvicesRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvicesService {

    private final AdvicesRepository advicesRepository;

    @Autowired
    public AdvicesService(AdvicesRepository advicesRepository) {
        this.advicesRepository = advicesRepository;
    }

    public List<Advices> findAll() {
        List<Advices> advicesList = new LinkedList<>();
        advicesRepository.findAll().forEach(advicesList::add);
        return advicesList;
    }

    public Page<Advices> findAll(PageRequest pageRequest) {
        return advicesRepository.findAllOrderedByDateDesc(pageRequest);
    }

    public Optional<Advices> findById(Integer id) {
        return advicesRepository.findById(id);
    }

    public void update(Advices advices) {
        advicesRepository.save(advices);
    }

    public void delete(Advices advices) {
        advicesRepository.delete(advices);
    }

}
