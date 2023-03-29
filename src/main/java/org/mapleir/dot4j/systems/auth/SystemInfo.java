package org.mapleir.dot4j.systems.auth;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.security.MessageDigest;
import java.util.Base64;

public class SystemInfo {

    public static String getESystemIds() throws Exception {
        String[] systemIds = getSystemIds();
        String plaintext = String.join(",", systemIds);

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(plaintext.getBytes());

        return Base64.getEncoder().encodeToString(hash);
    }

    private static String[] getSystemIds() {
        String[] systemIds = new String[2];
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeBean.getName();
        systemIds[0] = name.split("@")[0];

        try {
            OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
            systemIds[1] = osBean.getArch() + "_" + osBean.getName() + "_" + osBean.getVersion();
        } catch (Exception e) {
            systemIds[1] = "unknown";
        }

        return systemIds;
    }
}