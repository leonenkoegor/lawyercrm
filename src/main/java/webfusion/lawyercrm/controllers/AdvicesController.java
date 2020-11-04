package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webfusion.lawyercrm.controllers.responses.Response;
import webfusion.lawyercrm.services.AdvicesService;

@RestController
@RequestMapping("api/advices")
public class AdvicesController {
    private final AdvicesService advicesService;

    @Autowired
    public AdvicesController(AdvicesService advicesService) {
        this.advicesService = advicesService;
    }

    @GetMapping
    public Object getAll() {
        return new Response("GOOD", "Get all advices", advicesService.findAll());
    }
}
