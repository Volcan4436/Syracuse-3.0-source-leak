package org.mapleir.dot4j.systems.auth;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IntegrityF {
    public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(1000);
        } catch (UnknownHostException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
