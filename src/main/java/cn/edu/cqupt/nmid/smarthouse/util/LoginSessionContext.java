package cn.edu.cqupt.nmid.smarthouse.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author piwenjing
 * @description
 * @date 2020/1/11 11:13 AM
 */
@Component
public class LoginSessionContext {
    private HashMap<String, HttpSession> sessionMap;

    public LoginSessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }
}
