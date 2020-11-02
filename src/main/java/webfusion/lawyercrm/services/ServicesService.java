package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.Services;
import webfusion.lawyercrm.repositories.ServicesRepository;

import java.util.LinkedList;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    public LinkedList<Services> findAll() {
        return servicesRepository.findAll();
    }

    public Services update(Services service) {
        return servicesRepository.save(service);
    }

    public void delete(Services service) {
        servicesRepository.delete(service);
    }
}
