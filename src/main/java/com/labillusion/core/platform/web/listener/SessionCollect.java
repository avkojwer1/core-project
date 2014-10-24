package com.labillusion.core.platform.web.listener;

import com.labillusion.core.platform.exception.ResourceNotFoundException;
import com.labillusion.core.platform.exception.SessionTimeOutException;
import com.labillusion.core.platform.http.HTTPHeaders;
import com.labillusion.core.util.MessageSourceUtils;
import com.labillusion.core.util.StringUtils;
import org.apache.catalina.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by greg.chen on 14-10-20.
 将Session 托管
 */
public class SessionCollect implements HttpSessionListener {
    private static final Logger logger = LoggerFactory.getLogger(SessionCollect.class);

    private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        logger.debug("session created, id=" + session.getId());
        sessions.put(session.getId(), session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        sessions.remove(httpSessionEvent.getSession().getId());
        logger.debug("session destroyed, id=" + httpSessionEvent.getSession().getId());
    }

    public static HttpSession find(String sessionId) {
        return sessions.get(sessionId);
    }

    public static HttpSession find(HttpServletRequest request) {
        String sessionId = request.getHeader(HTTPHeaders.SESSION_ID);
        if(!StringUtils.hasText(sessionId))
            throw new ResourceNotFoundException("Session Id should not be empty");
        HttpSession session = sessions.get(sessionId);
        if(session == null)
            throw new ResourceNotFoundException("Can not found Session by Id");
        return session;
    }
}
