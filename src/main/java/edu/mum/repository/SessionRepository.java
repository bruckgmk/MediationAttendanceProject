package edu.mum.repository;

import edu.mum.domain.Block;
import edu.mum.domain.Session;
import edu.mum.domain.Block;
import edu.mum.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {

    @Query("select s from  Session s, Block b, Student ss where  s.barcode = :id")
    public List<Session> findSessionByBlockNameAndId( String id);
}
