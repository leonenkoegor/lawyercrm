package webfusion.lawyercrm.views.users;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.crud.CrudGrid;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import webfusion.lawyercrm.models.User;
import webfusion.lawyercrm.services.UsersService;
import webfusion.lawyercrm.views.Localization;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;

@Route(value = "admin/users", layout = MainView.class)
@PageTitle("Пользователи | CRM")
@NoArgsConstructor
public class UsersView extends VerticalLayout {

    @Autowired
    private UsersService usersService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        getThemeList().set("dark", true);
        setSizeFull();
        CrudGrid<User> crudGrid = new CrudGrid<>(User.class, false);
        Crud<User> crud = new Crud<>(User.class, crudGrid, createCrudEditor());
        crud.setDataProvider(new UsersDataProvider(usersService));
        crud.setI18n(Localization.getCrudLocalization());

        crud.addSaveListener((event) -> usersService.update(event.getItem()));
        crud.addDeleteListener((event) -> usersService.delete(event.getItem()));

        crud.getGrid().setColumnReorderingAllowed(true);
        crud.getGrid().setSortableColumns();
        crud.getGrid().removeColumnByKey("id");
        crud.getGrid().removeColumnByKey("password");

        add(crud);
    }

    public CrudEditor<User> createCrudEditor() {
        TextField usernameField = new TextField("Логин");
        PasswordField passwordField = new PasswordField("Пароль");
        passwordField.setPlaceholder("Перезаполни!");
        Checkbox enabledField = new Checkbox("Включён?");
        TextField firstnameField = new TextField("Имя");
        TextField lastnameField = new TextField("Фамилия");
        FormLayout form = new FormLayout(usernameField, passwordField, enabledField, firstnameField, lastnameField);

        Binder<User> binder = new Binder<>(User.class);
        binder.bind(usernameField, User::getUsername, User::setUsername);
        binder.bind(passwordField, (ignore) -> "", (user, password) -> user.setPassword(bCryptPasswordEncoder.encode(password)));
        binder.bind(enabledField, User::getEnabled, User::setEnabled);
        binder.bind(firstnameField, User::getFirstname, User::setFirstname);
        binder.bind(lastnameField, User::getLastname, User::setLastname);

        return new BinderCrudEditor<>(binder, form);
    }

}
