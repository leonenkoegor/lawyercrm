package webfusion.lawyercrm.views.settings;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import webfusion.lawyercrm.models.File;
import webfusion.lawyercrm.services.FilesService;
import webfusion.lawyercrm.views.Localization;
import webfusion.lawyercrm.views.main.MainView;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Route(value = "admin/settings", layout = MainView.class)
@PageTitle("Настройки | CRM")
@NoArgsConstructor
public class SettingsView extends VerticalLayout {

    @Autowired
    private FilesService filesService;

    private Accordion accordion = new Accordion();

    @PostConstruct
    public void init() {
        getThemeList().set("dark", true);
        setSizeFull();

        accordion.setSizeFull();

        createSection("Загрузить фото на главную страницу", uploadMainImageFile());

        add(accordion);
    }

    private void createSection(String sectionName, Component content) {
        accordion.add(sectionName, content);
    }

    private Component uploadMainImageFile() {
        Div mainImageContainer = new Div();
        mainImageContainer.setWidthFull();
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload mainImageField = new Upload(memoryBuffer);

        mainImageField.setI18n(Localization.getUploadLocalization());

        mainImageField.setMaxFiles(1);
        mainImageField.addSucceededListener(succeededEvent -> {
            File mainImageFile = filesService.findByName("mainImage").orElseGet(File::new);

            InputStream is = memoryBuffer.getInputStream();
            byte[] data = new byte[0];
            try {
                data = new byte[is.available()];
                is.read(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mainImageFile.setName("mainImage");
            mainImageFile.setMime(memoryBuffer.getFileData().getMimeType());
            mainImageFile.setData(data);
            filesService.save(mainImageFile);
        });
        mainImageContainer.add(mainImageField);
        return mainImageContainer;
    }

}
