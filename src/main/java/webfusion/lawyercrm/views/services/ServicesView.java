package webfusion.lawyercrm.views.services;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudGrid;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.models.Services;
import webfusion.lawyercrm.services.ServicesService;
import webfusion.lawyercrm.views.Localization;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;

@Route(value = "admin/services", layout = MainView.class)
@PageTitle("Услуги | CRM")
@NoArgsConstructor
public class ServicesView extends VerticalLayout {

    @Autowired
    ServicesService servicesService;

    @PostConstruct
    public void init() {
        getThemeList().set("dark", true);
        setSizeFull();
        CrudGrid<Services> crudGrid = new CrudGrid<>(Services.class, false);
        Crud<Services> crud = new Crud<>(Services.class, crudGrid, createCrudEditor());
        crud.setDataProvider(new ServicesDataProvider(servicesService));
        crud.setI18n(Localization.getCrudLocalization());

        crud.addSaveListener((event) -> servicesService.update(event.getItem()));
        crud.addDeleteListener((event) -> servicesService.delete(event.getItem()));

        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setSortableColumns();
        crud.getGrid().removeColumnByKey("id");

        add(crud);
    }

    public CrudEditor<Services> createCrudEditor() {
        TextField serviceName = new TextField("Название услуги");
        NumberField costs = new NumberField("Стоимость");
        FormLayout form = new FormLayout(serviceName, costs);

        Binder<Services> binder = new Binder<>(Services.class);
        binder.bind(serviceName, Services::getServiceName, Services::setServiceName);
        binder.bind(costs, Services::getCosts, Services::setCosts);

        return new BinderCrudEditor<>(binder, form);
    }

}
