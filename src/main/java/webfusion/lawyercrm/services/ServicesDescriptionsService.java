package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.ServicesDescription;
import webfusion.lawyercrm.repositories.ServicesDescriptionRepository;

import java.util.List;

@Service
public class ServicesDescriptionsService {

    private ServicesDescriptionRepository servicesDescriptionRepository;

    @Autowired
    public ServicesDescriptionsService(ServicesDescriptionRepository servicesDescriptionRepository) {
        this.servicesDescriptionRepository = servicesDescriptionRepository;
    }

    public List<ServicesDescription> findAll() {
        return servicesDescriptionRepository.findAll();
    }

    public void update(ServicesDescription servicesDescription) {
        servicesDescriptionRepository.save(servicesDescription);
    }

    public void delete(ServicesDescription servicesDescription) {
        servicesDescriptionRepository.delete(servicesDescription);
    }

}
