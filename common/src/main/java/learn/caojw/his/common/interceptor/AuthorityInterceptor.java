package learn.caojw.his.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

public class AuthorityInterceptor implements HandlerInterceptor {
    private static final ThreadLocal<Map<String, String>> currentUser = new ThreadLocal<>();

    public static String getString() {
        return currentUser.get().get("string");
    }

    public static String getUsername() {
        return currentUser.get().getOrDefault("username", "");
    }

    public static String getAuthority() {
        return currentUser.get().getOrDefault("authority", "");
    }

    private Map<String, String> decode(String header) {
        Map<String, String> map = new HashMap<>();
        map.put("string", header);
        if (header != null && !header.isBlank()) {
            for (String string : header.split("&")) {
                String[] split = string.trim().split("=");
                map.put(split[0], split[1]);
            }
        }
        return map;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorization = request.getHeader("Authorization");
        currentUser.set(decode(authorization));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        currentUser.remove();
    }
}
