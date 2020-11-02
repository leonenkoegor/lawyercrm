package webfusion.lawyercrm.views.news;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.crudui.crud.impl.GridCrud;
import webfusion.lawyercrm.models.News;
import webfusion.lawyercrm.services.NewsService;
import webfusion.lawyercrm.views.main.MainView;

@Route(value = "admin/news", layout = MainView.class)
@PageTitle("News | CRM")
public class NewsView extends HorizontalLayout {
    public NewsView(@Autowired NewsService newsService) {
        GridCrud<News> crud = new GridCrud(News.class);
        crud.setFindAllOperation(newsService::findAll);
        crud.setUpdateOperation(newsService::update);
        crud.setAddOperation(newsService::update);
        crud.setDeleteOperation(newsService::update);

        crud.getCrudFormFactory().setVisibleProperties("title", "text", "date");
        add(crud);
    }
}
