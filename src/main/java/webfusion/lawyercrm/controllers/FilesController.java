package webfusion.lawyercrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webfusion.lawyercrm.models.File;
import webfusion.lawyercrm.services.FilesService;

import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FilesController {

    @Autowired
    private FilesService filesService;

    @GetMapping("{filename}")
    public Object getFile(@PathVariable String filename) {
        Optional<File> fileOptional = filesService.findByName(filename);
        if (fileOptional.isPresent()) {
            File file = fileOptional.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getMime()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getData());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
