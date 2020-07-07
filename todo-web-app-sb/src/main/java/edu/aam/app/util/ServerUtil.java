package edu.aam.app.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class ServerUtil {
    public static String getServerUrl(HttpServletRequest request) {
        String appUrl = "http://" + request.getServerName();
        if(request.getServerPort() > 0) {
            appUrl = appUrl +  ":" + request.getServerPort();
        }

        return appUrl;
    }

    public static String getIP() {
        // This try will give the Public IP Address of the Host.
        try {
            URL url = new URL("http://automation.whatismyip.com/n09230945.asp");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String ipAddress = new String();
            ipAddress = (in.readLine()).trim();
            /* IF not connected to internet, then
             * the above code will return one empty
             * String, we can check it's length and
             * if length is not greater than zero,
             * then we can go for LAN IP or Local IP
             * or PRIVATE IP
             */
            if (!(ipAddress.length() > 0))
            {
                try
                {
                    InetAddress ip = InetAddress.getLocalHost();
                    System.out.println((ip.getHostAddress()).trim());
                    return ((ip.getHostAddress()).trim());
                }
                catch(Exception ex) {
                    return "ERROR";
                }
            }
            //System.out.println("IP Address is : " + ipAddress);
            return (ipAddress);
        }
        catch(Exception e) {
            // This try will give the Private IP of the Host.
            try
            {
                InetAddress ip = InetAddress.getLocalHost();
                //System.out.println((ip.getHostAddress()).trim());
                return ((ip.getHostAddress()).trim());
            }
            catch(Exception ex)
            {
                return "ERROR";
            }
        }
    }
}
