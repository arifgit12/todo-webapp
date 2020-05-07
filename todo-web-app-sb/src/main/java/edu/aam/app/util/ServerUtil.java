package edu.aam.app.util;

import javax.servlet.http.HttpServletRequest;

public class ServerUtil {
    public static String getServerUrl(HttpServletRequest request) {
        String appUrl = "http://" + request.getServerName();
        if(request.getServerPort() > 0) {
            appUrl = appUrl +  ":" + request.getServerPort();
        }

        return appUrl;
    }
}
