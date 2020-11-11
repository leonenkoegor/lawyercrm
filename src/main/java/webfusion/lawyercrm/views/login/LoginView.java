package webfusion.lawyercrm.views.login;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Вход | LawyerCRM")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    public LoginView() {
        getThemeList().set("dark", true);
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);
        LoginI18n loginI18n = new LoginI18n();
        LoginI18n.Form form = new LoginI18n.Form();
        form.setUsername("Логин");
        form.setPassword("Пароль");
        form.setSubmit("Войти");
        form.setTitle("LawyerCRM");
        LoginI18n.ErrorMessage errorMessage = new LoginI18n.ErrorMessage();
        errorMessage.setTitle("Ошибка входа");
        errorMessage.setMessage("Введён неправильный логин/пароль");
        loginI18n.setErrorMessage(errorMessage);
        loginI18n.setForm(form);
        login.setI18n(loginI18n);

        add(login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }

}