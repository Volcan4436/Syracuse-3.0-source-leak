package org.mapleir.dot4j.systems.auth;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IntegrityB {

    public static String calculateHash(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            md.update(buffer, 0, bytesRead);
        }
        byte[] hash = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean verifyHashFromURL(String urlStr, String expectedHash) throws IOException, NoSuchAlgorithmException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to download file: HTTP error code " + responseCode);
        }

        File file = File.createTempFile("downloaded", ".jar");
        FileOutputStream fos = new FileOutputStream(file);
        InputStream is = connection.getInputStream();
        byte[] buffer = new byte[8192];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesRead);
        }
        fos.close();
        is.close();

        String actualHash = calculateHash(file);
        boolean isValid = actualHash.equals(expectedHash);

        if (isValid) {
            System.out.println("Integrity check passed! The downloaded JAR file hash is: " + actualHash);
        } else {
            System.out.println("Integrity check failed! The expected hash is: " + expectedHash + ", but the actual hash is: " + actualHash);
            file.delete();
        }

        return isValid;
    }
}
