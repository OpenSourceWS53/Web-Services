package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
