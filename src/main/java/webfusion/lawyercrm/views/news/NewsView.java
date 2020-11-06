package webfusion.lawyercrm.views.news;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudGrid;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.models.News;
import webfusion.lawyercrm.services.NewsService;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;

@Route(value = "admin/news", layout = MainView.class)
@PageTitle("News | CRM")
@NoArgsConstructor
public class NewsView extends VerticalLayout {

    @Autowired
    private NewsService newsService;

    @PostConstruct
    public void init() {
        getThemeList().set("dark", true);
        setSizeFull();
        CrudGrid<News> crudGrid = new CrudGrid<>(News.class, false);
        Crud<News> crud = new Crud<>(News.class, crudGrid, createCrudEditor());
        crud.setDataProvider(new NewsDataProvider(newsService));

        crud.addSaveListener((event) -> newsService.update(event.getItem()));
        crud.addDeleteListener((event) -> newsService.delete(event.getItem()));

        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setSortableColumns();
        crud.getGrid().removeColumnByKey("id");

        add(crud);
    }

    public CrudEditor<News> createCrudEditor() {
        TextField title = new TextField("Title");
        DatePicker date = new DatePicker("Date");
        RichTextEditor text = new RichTextEditor();
        FormLayout form = new FormLayout(title, text, date);

        Binder<News> binder = new Binder<>(News.class);
        binder.bind(title, News::getTitle, News::setTitle);
        binder.bind(text, News::getText, News::setText);
        binder.bind(date, News::getDate, News::setDate);

        return new BinderCrudEditor<>(binder, form);
    }

}
