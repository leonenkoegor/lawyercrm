package webfusion.lawyercrm.views.services;

import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import webfusion.lawyercrm.models.Services;
import webfusion.lawyercrm.services.ServicesService;

import java.util.stream.Stream;

public class ServicesDataProvider extends AbstractBackEndDataProvider<Services, CrudFilter> {

    private final ServicesService servicesService;

    public ServicesDataProvider(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @Override
    protected Stream<Services> fetchFromBackEnd(Query<Services, CrudFilter> query) {
        return servicesService.findAll().stream();
    }

    @Override
    protected int sizeInBackEnd(Query<Services, CrudFilter> query) {
        return servicesService.findAll().size();
    }

}
