package webfusion.lawyercrm.views.servicesDescription;

import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import webfusion.lawyercrm.models.ServicesDescription;
import webfusion.lawyercrm.services.ServicesDescriptionsService;

import java.util.stream.Stream;

public class ServicesDescriptionDataProvider extends AbstractBackEndDataProvider<ServicesDescription, CrudFilter> {

    private final ServicesDescriptionsService servicesDescriptionsService;

    public ServicesDescriptionDataProvider(ServicesDescriptionsService servicesDescriptionsService) {
        this.servicesDescriptionsService = servicesDescriptionsService;
    }

    @Override
    protected Stream<ServicesDescription> fetchFromBackEnd(Query<ServicesDescription, CrudFilter> query) {
        return servicesDescriptionsService.findAll().stream();
    }

    @Override
    protected int sizeInBackEnd(Query<ServicesDescription, CrudFilter> query) {
        return servicesDescriptionsService.findAll().size();
    }

}
