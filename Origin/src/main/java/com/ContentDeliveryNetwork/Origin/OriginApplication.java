package com.ContentDeliveryNetwork.Origin;


import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Scanner;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


public class OriginApplication {

    public static void main(String[] args) {
while(true) {
        
        System.out.println("Enter the server port:");
        Scanner scanner = new Scanner(System.in);
        String serverport =  scanner.nextLine();
        
        String urlstring = "https://localhost:" + serverport + "/api/videos/upload";

        File directoryPath = new File("C:\\Users\\Mohammed Sohail\\OneDrive\\Desktop\\OriginServer");
        String contents[] = directoryPath.list();

        
        
        OkHttpClient client = createOkHttpClient();

        for (String content : contents) {
			@SuppressWarnings("deprecation")
			RequestBody body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", content,
                            RequestBody.create(MediaType.parse("application/octet-stream"),
                                    new File("C:\\Users\\Mohammed Sohail\\OneDrive\\Desktop\\OriginServer\\" + content)))
                    .build();

            Request request = new Request.Builder().url(urlstring).post(body).build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    System.out.println("Uploaded " + content);
                } else {
                    System.out.println("Upload failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    System.out.println("Upload complete");}}

    private static OkHttpClient createOkHttpClient() {
        TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // Do nothing, trusting all clients
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // Do Nothing, trusting all servers
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new SecureRandom());

            return new OkHttpClient.Builder()
                    .protocols(java.util.Arrays.asList(Protocol.HTTP_2, Protocol.HTTP_1_1))
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error creating OkHttpClient", e);
        }
    }
    }

