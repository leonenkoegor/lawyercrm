package webfusion.lawyercrm.views.liquidation;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.richtexteditor.RichTextEditor;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.models.Liquidation;
import webfusion.lawyercrm.services.LiquidationService;
import webfusion.lawyercrm.services.exceptions.LiquidationPageNotFoundException;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;

@Route(value = "admin/liquidation", layout = MainView.class)
@PageTitle("Liquidation | CRM")
@NoArgsConstructor
public class LiquidationView extends VerticalLayout {
    @Autowired
    private LiquidationService liquidationService;

    @PostConstruct
    public void init() {
        FormLayout form = new FormLayout();
        RichTextEditor text = new RichTextEditor();

        try {
            text.setValue(liquidationService.findById(1L).getText());
        } catch (LiquidationPageNotFoundException ignored) {
        }

        Button save = new Button("Save");

        save.addClickListener((event) -> {
            Liquidation liquidation = new Liquidation();
            liquidation.setId(1L);
            liquidation.setText(text.getValue());
            liquidationService.update(liquidation);
        });

        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("10em", 1),
                new FormLayout.ResponsiveStep("20em", 4)
        );

        form.add(text, 4);
        form.add(save, 1);

        add(form);
    }
}
