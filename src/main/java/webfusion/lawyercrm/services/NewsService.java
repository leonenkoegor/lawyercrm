package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.News;
import webfusion.lawyercrm.repositories.NewsRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> findAll() {
        List<News> newsList = new LinkedList<>();
        newsRepository.findAll().forEach(newsList::add);
        return newsList;
    }

    public void update(News news) {
        newsRepository.save(news);
    }

    public void delete(News news) {
        newsRepository.delete(news);
    }

    public Page<News> findAll(PageRequest pageRequest) {

        return newsRepository.findAllOrderedByDateDesc(pageRequest);
    }

    public Optional<News> findById(Integer id) {
        return newsRepository.findById(id);
    }

}
