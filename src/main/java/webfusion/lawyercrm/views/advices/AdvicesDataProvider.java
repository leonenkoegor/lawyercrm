package webfusion.lawyercrm.views.advices;

import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import webfusion.lawyercrm.models.Advices;
import webfusion.lawyercrm.services.AdvicesService;

import java.util.stream.Stream;

public class AdvicesDataProvider extends AbstractBackEndDataProvider<Advices, CrudFilter> {
    private final AdvicesService advicesService;

    public AdvicesDataProvider(AdvicesService advicesService) {
        this.advicesService = advicesService;
    }

    @Override
    protected Stream<Advices> fetchFromBackEnd(Query<Advices, CrudFilter> query) {
        return advicesService.findAll().stream();
    }

    @Override
    protected int sizeInBackEnd(Query<Advices, CrudFilter> query) {
        return advicesService.findAll().size();
    }
}
