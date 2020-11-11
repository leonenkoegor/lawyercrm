package webfusion.lawyercrm.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webfusion.lawyercrm.models.File;

import java.util.Optional;

@Repository
public interface FilesRepository extends CrudRepository<File, Long> {

    Optional<File> findByName(String name);

}
