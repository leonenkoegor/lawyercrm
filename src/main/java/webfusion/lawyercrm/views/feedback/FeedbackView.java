package webfusion.lawyercrm.views.feedback;

import com.vaadin.flow.component.crud.*;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.models.Feedback;
import webfusion.lawyercrm.services.FeedbackService;
import webfusion.lawyercrm.views.Localization;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;

@Route(value = "admin/feedback", layout = MainView.class)
@PageTitle("Обратная связь | CRM")
@NoArgsConstructor
public class FeedbackView extends VerticalLayout {

    @Autowired
    private FeedbackService feedbackService;

    @PostConstruct
    public void init() {
        getThemeList().set("dark", true);
        setSizeFull();
        CrudGrid<Feedback> crudGrid = new CrudGrid<>(Feedback.class, false);
        Crud<Feedback> crud = new Crud<>(Feedback.class, crudGrid, createCrudEditor());
        crud.setDataProvider(new FeedbackDataProvider(feedbackService));
        crud.setI18n(Localization.getCrudLocalization());

        crud.addSaveListener((event) -> feedbackService.update(event.getItem()));
        crud.addDeleteListener((event) -> feedbackService.delete(event.getItem()));

        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setSortableColumns();
        crud.getGrid().removeColumnByKey("id");

        add(crud);
    }

    public CrudEditor<Feedback> createCrudEditor() {
        TextField name = new TextField("Имя");
        TextField email = new TextField("Почта");
        TextField question = new TextField("Вопрос");
        TextField text = new TextField("Текст");
        FormLayout form = new FormLayout(name, email, question, text);

        Binder<Feedback> binder = new Binder<>(Feedback.class);
        binder.bind(name, Feedback::getName, Feedback::setName);
        binder.bind(email, Feedback::getEmail, Feedback::setEmail);
        binder.bind(question, Feedback::getQuestion, Feedback::setQuestion);
        binder.bind(text, Feedback::getText, Feedback::setText);

        return new BinderCrudEditor<>(binder, form);
    }

}
