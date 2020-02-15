package cn.edu.cqupt.nmid.smarthouse.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author piwenjing
 * @description 单例
 * @date 2020/1/10 5:47 PM
 */
@Component
public class RegistSessionContext {
    private HashMap<String, HttpSession> sessionMap;

    public RegistSessionContext() {
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

    public synchronized HttpSession getSession(String sessionId) {
        if (sessionId != null) {
            return sessionMap.get(sessionId);
        }
        return null;
    }
}
