package br.com.lucasfood.requests.repository;

import br.com.lucasfood.requests.model.Request;
import br.com.lucasfood.requests.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Request p set p.status = :status where p = :request")
    void updateStatus(Status status, Request request);

    @Query(value = "SELECT p from Request p LEFT JOIN FETCH p.items where p.id = :id")
    Request byIdWithItems(Long id);

}
