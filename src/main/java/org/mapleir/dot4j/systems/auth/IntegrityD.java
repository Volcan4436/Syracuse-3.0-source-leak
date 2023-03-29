package org.mapleir.dot4j.systems.auth;

import java.net.*;
import java.util.Enumeration;

public class IntegrityD {
    public static boolean checkUnexpectedNetworkActivity() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr.isLinkLocalAddress() || addr.isLoopbackAddress() || addr.isMulticastAddress()) {
                        continue;
                    }
                    if (addr instanceof Inet4Address) {
                        String hostAddress = addr.getHostAddress();
                        if (!hostAddress.startsWith("192.168.") && !hostAddress.startsWith("10.") && !hostAddress.equals("127.0.0.1")) {
                            URL url = new URL("http://" + hostAddress);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setConnectTimeout(3000);
                            connection.connect();

                            String host = url.getHost();
                            if (!host.endsWith("discord.com") && !host.endsWith("syracuse.vip")) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
