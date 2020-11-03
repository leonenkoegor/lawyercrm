package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webfusion.lawyercrm.services.ServicesService;

@RestController
@RequestMapping("api/services")
public class ServicesController {
    @Autowired
    private ServicesService servicesService;

    @GetMapping
    public Object getAll() {
        return servicesService.findAll();
    }
}
