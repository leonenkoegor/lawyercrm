package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webfusion.lawyercrm.services.PageService;
import webfusion.lawyercrm.services.exceptions.PageNotFoundException;

@RestController
@RequestMapping("api/pages")
public class PageController {

    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/{page}")
    public Object get(@PathVariable String page) throws PageNotFoundException {
        return new ResponseEntity<>(pageService.findById(page).getText(), HttpStatus.OK);
    }

    @ExceptionHandler
    public Object pageNotFoundHandler(PageNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
