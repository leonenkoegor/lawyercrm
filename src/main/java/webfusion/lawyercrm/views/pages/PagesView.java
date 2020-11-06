package webfusion.lawyercrm.views.pages;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.models.Page;
import webfusion.lawyercrm.services.PageService;
import webfusion.lawyercrm.services.exceptions.PageNotFoundException;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Route(value = "admin/pages", layout = MainView.class)
@PageTitle("Pages | CRM")
@NoArgsConstructor
public class PagesView extends VerticalLayout {

    private final Map<Tab, Component> tabToDiv = new HashMap<>();
    private final Tabs tabs = new Tabs();
    private final Div divs = new Div();
    @Autowired
    private PageService pageService;

    @PostConstruct
    public void init() {
        getThemeList().set("dark", true);
        createTabAndContent("Liquidation");
        createTabAndContent("Bankruptcy");
        createTabAndContent("LegalServices");
        createTabAndContent("Specialists");
        createTabAndContent("PriceInformation");
        createTabAndContent("AboutMe");
        configureTabsAndContent();
        setAlignItems(Alignment.CENTER);

        setHeight("100%");
        divs.setWidthFull();
        divs.setHeight("100%");
        divs.setClassName("v-scrollable");
        divs.getStyle().set("overflow-y", "auto");

        tabs.setWidthFull();
        add(tabs, divs);
    }

    private void createTabAndContent(String name) {
        Tab tab = new Tab(name);
        Div div = new Div();
        div.setHeightFull();
        if (tabToDiv.size() != 0) {
            div.setVisible(false);
        }
        tabs.add(tab);
        divs.add(div);
        tabToDiv.put(tab, div);
    }

    private void configureTabsAndContent() {
        tabToDiv.forEach((tab, div) -> {
            FormLayout form = new FormLayout();
            RichTextEditor text = new RichTextEditor();
            text.setMinHeight("70vh");

            try {
                text.setValue(pageService.findById(tab.getLabel()).getText());
            } catch (PageNotFoundException ignored) {
            }

            Button save = new Button("Save");

            save.addClickListener((event) -> {
                Page page = new Page();
                page.setPageName(tab.getLabel());
                page.setText(text.getValue());
                pageService.update(page);
            });

            form.setResponsiveSteps(
                    new FormLayout.ResponsiveStep("10em", 1),
                    new FormLayout.ResponsiveStep("20em", 4)
            );

            form.add(text, 4);
            form.add(save, 1);

            ((Div) div).add(form);
        });

        tabs.addSelectedChangeListener(event -> {
            tabToDiv.values().forEach(page -> page.setVisible(false));
            Component selectedPage = tabToDiv.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });
    }

}
