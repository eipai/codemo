package com.github.eipai.codemo.tools;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Rmi {
    private static int TIMEOUT = 3000;

    public static Object invoke(String url, Object param) {
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setDefaultUseCaches(false);
            connection.setRequestProperty("Content-type", "application/x-java-serialized-object");
            ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
            output.writeObject(param);
            output.flush();
            output.close();
            connection.setReadTimeout(TIMEOUT);
            ObjectInputStream inputFromServlet = new ObjectInputStream(connection.getInputStream());
            Object obj = (Object) inputFromServlet.readObject();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            //log.error(new StringBuffer("\n").append(e.getClass()).append(" : ").append(e.getMessage()).append("\n").append(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n")));
        }
        return null;
    }
}
