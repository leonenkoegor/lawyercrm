package webfusion.lawyercrm.views.advices;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudGrid;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.models.Advices;
import webfusion.lawyercrm.services.AdvicesService;
import webfusion.lawyercrm.views.Localization;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;

@Route(value = "admin/advices", layout = MainView.class)
@PageTitle("Советы | CRM")
@NoArgsConstructor
public class AdvicesView extends VerticalLayout {

    @Autowired
    private AdvicesService advicesService;

    @PostConstruct
    public void init() {
        getThemeList().set("dark", true);
        setSizeFull();
        CrudGrid<Advices> crudGrid = new CrudGrid<>(Advices.class, false);
        Crud<Advices> crud = new Crud<>(Advices.class, crudGrid, createCrudEditor());
        crud.setDataProvider(new AdvicesDataProvider(advicesService));
        crud.setI18n(Localization.getCrudLocalization());

        crud.addSaveListener((event) -> advicesService.update(event.getItem()));
        crud.addDeleteListener((event) -> advicesService.delete(event.getItem()));

        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setSortableColumns();
        crud.getGrid().removeColumnByKey("id");

        add(crud);
    }

    public CrudEditor<Advices> createCrudEditor() {
        TextField title = new TextField("Заголовок");
        DatePicker date = new DatePicker("Дата");
        RichTextEditor text = new RichTextEditor("Текст");
        TextArea description = new TextArea("Описание");
        description.setMaxLength(255);
        FormLayout form = new FormLayout(date, title, text, description);

        Binder<Advices> binder = new Binder<>(Advices.class);
        binder.bind(title, Advices::getTitle, Advices::setTitle);
        binder.bind(text, Advices::getText, Advices::setText);
        binder.bind(description, Advices::getDescription, Advices::setDescription);
        binder.bind(date, Advices::getDate, Advices::setDate);

        return new BinderCrudEditor<>(binder, form);
    }

}
