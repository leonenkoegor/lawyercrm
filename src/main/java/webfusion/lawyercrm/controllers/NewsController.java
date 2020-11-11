package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webfusion.lawyercrm.models.News;
import webfusion.lawyercrm.services.NewsService;

import java.util.Optional;

@RestController
@RequestMapping("api/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("{page}")
    public Object getAll(@PathVariable Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return new ResponseEntity<>(newsService.findAll(pageRequest), HttpStatus.OK);
    }

    @GetMapping
    public Object getNewsById(@RequestParam Integer id) {
        Optional<News> news = newsService.findById(id);
        if(news.isPresent()) {
            return new ResponseEntity<>(news.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
