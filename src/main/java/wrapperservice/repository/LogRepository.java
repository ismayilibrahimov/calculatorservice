package wrapperservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wrapperservice.model.Log;

public interface LogRepository extends JpaRepository<Log, Integer> {
    Log findTopByOrderByIdDesc();
}
