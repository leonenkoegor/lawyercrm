package webfusion.lawyercrm.views.news;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.services.NewsService;
import webfusion.lawyercrm.views.main.MainView;

@Route(value = "admin/news", layout = MainView.class)
@PageTitle("News | CRM")
public class NewsView extends HorizontalLayout {
    public NewsView(@Autowired NewsService newsService) {
    }
}
