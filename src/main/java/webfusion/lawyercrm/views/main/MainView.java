package webfusion.lawyercrm.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import webfusion.lawyercrm.services.UsersService;
import webfusion.lawyercrm.views.advices.AdvicesView;
import webfusion.lawyercrm.views.feedback.FeedbackView;
import webfusion.lawyercrm.views.news.NewsView;
import webfusion.lawyercrm.views.pages.PagesView;
import webfusion.lawyercrm.views.services.ServicesView;
import webfusion.lawyercrm.views.servicesDescription.ServicesDescriptionView;
import webfusion.lawyercrm.views.settings.SettingsView;
import webfusion.lawyercrm.views.users.UsersView;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/main-view.css")
public class MainView extends AppLayout {

    @Autowired
    private UsersService usersService;

    private Tabs menu;
    private H1 viewTitle;

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @PostConstruct
    public void init() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);
        H1 userInfo = new H1(getCurrentUserFirstNameAndLastName());
        userInfo.getStyle().set("margin-right", "1vw");
        layout.add(userInfo);
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.getThemeList().set("dark", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        logoLayout.add(new H1("LawyerCRM"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        return new Tab[]{
                createTab("Обратная связь", FeedbackView.class),
                createTab("Новости", NewsView.class),
                createTab("Советы", AdvicesView.class),
                createTab("Услуги", ServicesView.class),
                createTab("Описание услуг", ServicesDescriptionView.class),
                createTab("Фрагменты", PagesView.class),
                createTab("Пользователи", UsersView.class),
                createTab("Настройки", SettingsView.class),
        };
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren()
                .filter(tab -> ComponentUtil.getData(tab, Class.class)
                        .equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }

    private String getCurrentUserFirstNameAndLastName() {
        User userPrincipal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        webfusion.lawyercrm.models.User user = usersService.findByUsername(userPrincipal.getUsername()).get();
        if ((user.getFirstname() == null) || (user.getLastname() == null)) {
            return "Firstname Lastname";
        }
        return user.getFirstname() + " " + user.getLastname();
    }

}
