package edu.aam.app.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class ServerUtils {

    public static String getServerUrl(HttpServletRequest request) {
        String appUrl = "http://" + request.getServerName();
        if(request.getServerPort() > 0) {
            appUrl = appUrl +  ":" + request.getServerPort();
        }

        return appUrl;
    }

}
