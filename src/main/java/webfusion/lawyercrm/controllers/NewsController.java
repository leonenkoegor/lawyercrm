package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webfusion.lawyercrm.controllers.responses.Response;
import webfusion.lawyercrm.services.NewsService;

@RestController
@RequestMapping("api/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public Object getAll() {
        return new Response("GOOD", "Get all news", newsService.findAll());
    }

}
