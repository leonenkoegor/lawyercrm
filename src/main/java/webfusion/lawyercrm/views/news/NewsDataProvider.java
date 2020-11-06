package webfusion.lawyercrm.views.news;

import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import webfusion.lawyercrm.models.News;
import webfusion.lawyercrm.services.NewsService;

import java.util.stream.Stream;

public class NewsDataProvider extends AbstractBackEndDataProvider<News, CrudFilter> {

    private final NewsService newsService;

    public NewsDataProvider(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    protected Stream<News> fetchFromBackEnd(Query<News, CrudFilter> query) {
        return newsService.findAll().stream();
    }

    @Override
    protected int sizeInBackEnd(Query<News, CrudFilter> query) {
        return newsService.findAll().size();
    }

}
