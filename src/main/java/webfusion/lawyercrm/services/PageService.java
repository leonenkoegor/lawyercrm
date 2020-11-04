package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.Page;
import webfusion.lawyercrm.repositories.PageRepository;
import webfusion.lawyercrm.services.exceptions.PageNotFoundException;

import java.util.Optional;

@Service
public class PageService {
    @Autowired
    private PageRepository pageRepository;

    public Page findById(String name) throws PageNotFoundException {
        Optional<Page> liquidation = pageRepository.findById(name);
        if (!liquidation.isPresent()) {
            throw new PageNotFoundException();
        }
        return liquidation.get();
    }

    public void update(Page page) {
        pageRepository.save(page);
    }
}
