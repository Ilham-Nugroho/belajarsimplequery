package day6.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    private static Connection connection;
    private static Properties properties;
    public static  String getProperty(String key){
        if(properties != null){
            return properties.getProperty(key);
        }else{
            properties = new Properties();
            InputStream is = DbUtil.class.getClassLoader().getResourceAsStream("database.properties");
            try {
                properties.load(is);
                return properties.getProperty(key);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    public static Connection getConnection(){
        if(connection != null){
            return connection;
        } else{
            try {
                connection = DriverManager.getConnection(
                        getProperty("url"),
                        getProperty("username"),
                        getProperty("password")
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
