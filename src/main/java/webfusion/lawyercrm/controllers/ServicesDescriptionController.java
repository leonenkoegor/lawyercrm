package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webfusion.lawyercrm.services.ServicesDescriptionsService;

@RestController
@RequestMapping("/api/servicesdescription")
public class ServicesDescriptionController {

    private ServicesDescriptionsService servicesDescriptionsService;

    @Autowired
    public ServicesDescriptionController(ServicesDescriptionsService servicesDescriptionsService) {
        this.servicesDescriptionsService = servicesDescriptionsService;
    }

    @GetMapping
    public Object getAll() {
        return new ResponseEntity<>(servicesDescriptionsService.findAll(), HttpStatus.OK);
    }

}
