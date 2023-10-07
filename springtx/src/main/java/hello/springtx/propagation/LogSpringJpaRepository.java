package hello.springtx.propagation;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogSpringJpaRepository extends JpaRepository<Log, Long> {

	Optional<Log> findByMessage(String message);
}
