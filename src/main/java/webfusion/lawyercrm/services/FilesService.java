package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.File;
import webfusion.lawyercrm.repositories.FilesRepository;

import java.util.Optional;

@Service
public class FilesService {

    private final FilesRepository filesRepository;

    @Autowired
    public FilesService(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    public Optional<File> findByName(String name) {
        return filesRepository.findByName(name);
    }
    public File save(File file) {
        return filesRepository.save(file);
    }

    public File delete(File file) {
        return filesRepository.save(file);
    }

}
