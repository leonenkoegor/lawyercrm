package webfusion.lawyercrm.views.servicesDescription;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudGrid;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.models.ServicesDescription;
import webfusion.lawyercrm.services.ServicesDescriptionsService;
import webfusion.lawyercrm.views.Localization;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;

@Route(value = "admin/servicesdescription", layout = MainView.class)
@PageTitle("Описание услуг | CRM")
@NoArgsConstructor
public class ServicesDescriptionView extends VerticalLayout {

    @Autowired
    ServicesDescriptionsService servicesDescriptionsService;

    @PostConstruct
    public void init() {
        getThemeList().set("dark", true);
        setSizeFull();
        CrudGrid<ServicesDescription> crudGrid = new CrudGrid<>(ServicesDescription.class, false);
        Crud<ServicesDescription> crud = new Crud<>(ServicesDescription.class, crudGrid, createCrudEditor());
        crud.setDataProvider(new ServicesDescriptionDataProvider(servicesDescriptionsService));
        crud.setI18n(Localization.getCrudLocalization());

        crud.addSaveListener((event) -> servicesDescriptionsService.update(event.getItem()));
        crud.addDeleteListener((event) -> servicesDescriptionsService.delete(event.getItem()));

        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setSortableColumns();
        crud.getGrid().removeColumnByKey("id");

        add(crud);
    }

    public CrudEditor<ServicesDescription> createCrudEditor() {
        RichTextEditor serviceDescription = new RichTextEditor("Описание услуг");
        TextField url = new TextField("Ссылка");
        FormLayout form = new FormLayout(url, serviceDescription);

        Binder<ServicesDescription> binder = new Binder<>(ServicesDescription.class);
        binder.bind(serviceDescription, ServicesDescription::getContent, ServicesDescription::setContent);
        binder.bind(url, ServicesDescription::getUrl, ServicesDescription::setUrl);

        return new BinderCrudEditor<>(binder, form);
    }

}
