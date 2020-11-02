package webfusion.lawyercrm.views.services;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.services.ServicesService;
import webfusion.lawyercrm.views.main.MainView;

@Route(value = "admin/services", layout = MainView.class)
@PageTitle("Services | CRM")
public class ServicesView extends VerticalLayout {
    public ServicesView(@Autowired ServicesService servicesService) {
    }
}
