package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webfusion.lawyercrm.controllers.responses.Response;
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
    public Object get(@PathVariable String page) {
        try {
            return new Response("GOOD", "Page content", pageService.findById(page).getText());
        } catch (PageNotFoundException e) {
            return new Response("FAILED", "Page doesn't fill", null);
        }
    }
}
