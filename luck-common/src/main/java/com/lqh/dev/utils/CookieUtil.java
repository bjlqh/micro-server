package com.lqh.dev.utils;

import com.lqh.dev.model.JdCookie;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CookieUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CookieUtil.class);
    private Map<String, JdCookie> cookieMap;

    public CookieUtil() {
    }

    public String getCookieValue(HttpServletRequest servletRequest, String name) {
        Cookie[] cookies = servletRequest.getCookies();
        if (cookies != null && cookies.length > 0) {
            Cookie[] var4 = cookies;
            int var5 = cookies.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Cookie cookie = var4[var6];
                String cookieName = cookie.getName();
                if (cookieName.equals(name)) {
                    if (this.cookieMap != null && this.cookieMap.containsKey(name)) {
                        JdCookie jdCookie = (JdCookie) this.cookieMap.get(name);
                        return jdCookie.getValue(cookie.getValue());
                    }

                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    public void deleteCookie(HttpServletResponse servletResponse, String name) {
        Cookie cookie;
        if (this.cookieMap != null && this.cookieMap.containsKey(name)) {
            JdCookie jdCookie = (JdCookie) this.cookieMap.get(name);
            cookie = jdCookie.newCookie((String) null);
        } else {
            cookie = new Cookie(name, (String) null);
        }

        cookie.setMaxAge(0);
        servletResponse.addCookie(cookie);
    }

    public void setCookie(HttpServletResponse servletResponse, String name, String value) {
        if (this.cookieMap != null && this.cookieMap.containsKey(name)) {
            JdCookie jdCookie = (JdCookie) this.cookieMap.get(name);
            Cookie cookie = jdCookie.newCookie(value);
            servletResponse.addCookie(cookie);
        } else {
            throw new RuntimeException("Cookie " + name + " is undefined!");
        }
    }

    public void setJdCookie(List<JdCookie> jdCookieList) {
        if (jdCookieList != null) {
            HashMap<String, JdCookie> jdCookieHashMap = new HashMap(jdCookieList.size());
            Iterator var3 = jdCookieList.iterator();

            while (var3.hasNext()) {
                JdCookie jdCookie = (JdCookie) var3.next();
                jdCookieHashMap.put(jdCookie.getName(), jdCookie);
            }

            this.cookieMap = jdCookieHashMap;
        }

    }

    public void invalidate(HttpServletRequest request, HttpServletResponse response) {
        if (this.cookieMap != null && this.cookieMap.size() > 0) {
            Iterator var3 = this.cookieMap.entrySet().iterator();

            while (var3.hasNext()) {
                Map.Entry<String, JdCookie> entry = (Map.Entry) var3.next();
                String key = (String) entry.getKey();
                JdCookie jdCookie = (JdCookie) entry.getValue();
                if (jdCookie.getExpiry() < 1 && StringUtils.isNotEmpty(this.getCookieValue(request, key))) {
                    this.deleteCookie(response, key);
                }
            }
        }

    }

    public void setGlobalCookie(HttpServletResponse response, String name, String value, String domain) {
        if (name != null) {
            Cookie cookie = new Cookie(name, value);
            if (domain != null && !"".equals(domain.trim())) {
                cookie.setDomain(domain);
            }

            cookie.setPath("/");
            cookie.setMaxAge(-1);
            response.addCookie(cookie);
        }
    }
}

