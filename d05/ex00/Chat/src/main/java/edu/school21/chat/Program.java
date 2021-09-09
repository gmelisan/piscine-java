package edu.school21.chat;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

public class Program {

    private static final String sqlSchema = "/schema.sql";
    private static final String sqlData = "/data.sql";

    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://localhost/chat";
            Properties props = new Properties();
            props.setProperty("user", "gmelisan");
            Connection conn = DriverManager.getConnection(url, props);
            String query = getResourceContent(sqlSchema);
            conn.createStatement().execute(query);
            query = getResourceContent(sqlData);
            Statement statement = conn.createStatement();
            statement.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getResourceContent(String resourceName) {
        StringBuilder str = new StringBuilder();
        try {
            String name = Objects.requireNonNull(Program.class.getResource(resourceName)).getFile();
            InputStream is = new FileInputStream(name);
            int c;
            while ((c = is.read()) != -1) {
                str.append((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
