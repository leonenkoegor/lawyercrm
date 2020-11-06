package webfusion.lawyercrm.views.users;

import com.vaadin.flow.component.crud.CrudFilter;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;
import webfusion.lawyercrm.models.User;
import webfusion.lawyercrm.services.UsersService;

import java.util.stream.Stream;

public class UsersDataProvider extends AbstractBackEndDataProvider<User, CrudFilter> {

    private final UsersService usersService;

    public UsersDataProvider(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    protected Stream<User> fetchFromBackEnd(Query<User, CrudFilter> query) {
        return usersService.findAll().stream();
    }

    @Override
    protected int sizeInBackEnd(Query<User, CrudFilter> query) {
        return usersService.findAll().size();
    }

}
