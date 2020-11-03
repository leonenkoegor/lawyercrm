package webfusion.lawyercrm.views.news;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
public class NewsView extends HorizontalLayout {
    @Autowired
    private NewsService newsService;

    @PostConstruct
    public void init() {
        Crud<News> crud = new Crud<>(News.class, createCrudEditor());
        crud.setDataProvider(new NewsDataProvider(newsService));

        crud.addSaveListener((event) -> newsService.update(event.getItem()));
        crud.addDeleteListener((event) -> newsService.delete(event.getItem()));

        Grid.Column<News> editColumn = crud.getGrid().getColumnByKey("vaadin-crud-edit-column");
        Grid.Column<News> idColumn = crud.getGrid().getColumnByKey("id");
        Grid.Column<News> titleColumn = crud.getGrid().getColumnByKey("title");
        Grid.Column<News> textColumn = crud.getGrid().getColumnByKey("text");
        Grid.Column<News> dateColumn = crud.getGrid().getColumnByKey("date");
        crud.getGrid().setColumnOrder(editColumn, idColumn, dateColumn, titleColumn, textColumn);

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
