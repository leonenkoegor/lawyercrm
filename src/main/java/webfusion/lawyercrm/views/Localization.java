package webfusion.lawyercrm.views;

import com.vaadin.flow.component.crud.CrudI18n;
import com.vaadin.flow.component.upload.UploadI18N;

public class Localization {
    public static CrudI18n getCrudLocalization() {
        CrudI18n crudI18n = new CrudI18n();
        crudI18n.setCancel("Отмена");
        crudI18n.setDeleteItem("Удалить");
        crudI18n.setEditItem("Изменить");
        crudI18n.setNewItem("Создать");
        crudI18n.setSaveItem("Сохранить");
        crudI18n.setEditLabel("Изменить лэйбел");
        CrudI18n.Confirmations confirmations = new CrudI18n.Confirmations();
        CrudI18n.Confirmations.Confirmation confirmation = new CrudI18n.Confirmations.Confirmation();
        confirmation.setTitle("Действительно удалить?");
        confirmation.setContent("Удаленное невозможно вернуть");
        CrudI18n.Confirmations.Confirmation.Button button = new CrudI18n.Confirmations.Confirmation.Button();
        button.setConfirm("Удалить");
        button.setDismiss("Отмена");
        confirmation.setButton(button);
        confirmations.setDelete(confirmation);
        crudI18n.setConfirm(confirmations);
        return crudI18n;
    }

    public static UploadI18N getUploadLocalization() {
        UploadI18N uploadI18N = new UploadI18N();
        UploadI18N.AddFiles addFiles = new UploadI18N.AddFiles();
        addFiles.setOne("Добавить файл");
        addFiles.setMany("Добавить файлы");
        uploadI18N.setAddFiles(addFiles);
        uploadI18N.setCancel("Отмена");
        UploadI18N.DropFiles dropFiles = new UploadI18N.DropFiles();
        dropFiles.setOne("Перетащи файл");
        dropFiles.setMany("Перетащи файлы");
        uploadI18N.setDropFiles(dropFiles);
        UploadI18N.Error error = new UploadI18N.Error();
        error.setFileIsTooBig("Файл очень большой");
        error.setIncorrectFileType("Несовместимый тип файла");
        error.setTooManyFiles("Слишком много файлов");
        uploadI18N.setError(error);
        UploadI18N.Uploading uploading = new UploadI18N.Uploading();
        UploadI18N.Uploading.Error error1 = new UploadI18N.Uploading.Error();
        error1.setServerUnavailable("Сервер недоступен");
        error1.setUnexpectedServerError("Неожиданная ошибка сервера");
        error1.setForbidden("Потерян");
        uploading.setError(error1);
        UploadI18N.Uploading.RemainingTime remainingTime = new UploadI18N.Uploading.RemainingTime();
        remainingTime.setPrefix("Осталось");
        remainingTime.setUnknown("Неизместно");
        uploading.setRemainingTime(remainingTime);
        UploadI18N.Uploading.Status status = new UploadI18N.Uploading.Status();
        status.setConnecting("Подключение");
        status.setHeld("Удержание");
        status.setProcessing("В процессе");
        status.setStalled("Остановился");
        uploading.setStatus(status);
        uploadI18N.setUploading(uploading);
        return uploadI18N;
    }
}
