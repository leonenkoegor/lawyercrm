package webfusion.lawyercrm.views.services;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
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
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;

@Route(value = "admin/services", layout = MainView.class)
@PageTitle("Services | CRM")
@NoArgsConstructor
public class ServicesView extends VerticalLayout {
    @Autowired
    ServicesService servicesService;

    @PostConstruct
    public void init() {
        Crud<Services> crud = new Crud<>(Services.class, createCrudEditor());
        crud.setDataProvider(new ServicesDataProvider(servicesService));

        crud.addSaveListener((event) -> servicesService.update(event.getItem()));
        crud.addDeleteListener((event) -> servicesService.delete(event.getItem()));

        add(crud);
    }

    public CrudEditor<Services> createCrudEditor() {
        TextField serviceName = new TextField("Services Name");
        NumberField costs = new NumberField("Costs");
        FormLayout form = new FormLayout(serviceName, costs);

        Binder<Services> binder = new Binder<>(Services.class);
        binder.bind(serviceName, Services::getServiceName, Services::setServiceName);
        binder.bind(costs, Services::getCosts, Services::setCosts);

        return new BinderCrudEditor<>(binder, form);
    }
}
