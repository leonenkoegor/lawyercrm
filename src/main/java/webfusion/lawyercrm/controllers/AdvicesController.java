package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webfusion.lawyercrm.models.Advices;
import webfusion.lawyercrm.services.AdvicesService;

import java.util.Optional;

@RestController
@RequestMapping("api/advices")
public class AdvicesController {

    private final AdvicesService advicesService;

    @Autowired
    public AdvicesController(AdvicesService advicesService) {
        this.advicesService = advicesService;
    }

    @GetMapping("{page}")
    public Object getAll(@PathVariable Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 5);
        return new ResponseEntity<>(advicesService.findAll(pageRequest), HttpStatus.OK);
    }

    @GetMapping
    public Object getAdvicesById(@RequestParam Integer id) {
        Optional<Advices> advice = advicesService.findById(id);
        if(advice.isPresent()) {
            return new ResponseEntity<>(advice.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
