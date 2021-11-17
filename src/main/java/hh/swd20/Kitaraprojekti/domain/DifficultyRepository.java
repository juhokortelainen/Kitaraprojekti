package hh.swd20.Kitaraprojekti.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface DifficultyRepository extends CrudRepository<Difficulty, Long> {

}
