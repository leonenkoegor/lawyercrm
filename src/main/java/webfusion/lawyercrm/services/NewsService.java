package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.News;
import webfusion.lawyercrm.repositories.NewsRepository;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public void update(News news) {
        newsRepository.save(news);
    }

    public void delete(News news) {
        newsRepository.delete(news);
    }
}
