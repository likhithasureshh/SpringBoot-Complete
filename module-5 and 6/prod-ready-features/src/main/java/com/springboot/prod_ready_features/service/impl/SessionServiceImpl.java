package com.springboot.prod_ready_features.service.impl;

import com.springboot.prod_ready_features.entities.Sessions;
import com.springboot.prod_ready_features.entities.User;
import com.springboot.prod_ready_features.repositories.SessionRepository;
import com.springboot.prod_ready_features.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;
    @Override
    public void generateSessionForUser(String refreshToken, User user)
    {
        List<Sessions> sessions = sessionRepository.findByUser(user);
        if(sessions.size() == SESSION_LIMIT)
        {
            sessions.sort(Comparator.comparing(Sessions::getLeastRecentlyUsed));
            Sessions tobeDeleted = sessions.getFirst();
            sessionRepository.delete(tobeDeleted);
        }
        Sessions sessions1 = Sessions.builder()
                .user(user)
                .refreshToken(refreshToken)
                .leastRecentlyUsed(LocalDateTime.now())
                .build();
        sessionRepository.save(sessions1);
    }

    @Override
    public void validateSession(String refreshToken) {
        Sessions session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new SessionAuthenticationException("Session not found"));
        session.setLeastRecentlyUsed(LocalDateTime.now());
        sessionRepository.save(session);
    }


}
