package edu.mum.service.Impl;

import edu.mum.domain.Session;
import edu.mum.repository.SessionRepository;
import edu.mum.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
@Autowired
SessionRepository sessionRepository;
    @Override
    public List<Session> findAll() {
        return (List<Session>)sessionRepository.findAll();
    }

    @Override
    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId).get();
    }

    @Override
    public void deleteById(Long id) {
sessionRepository.deleteById(id);
    }
}
