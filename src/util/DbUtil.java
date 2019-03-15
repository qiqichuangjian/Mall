package util;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Acthor:孙琪; date:2019/3/14;
 */
public class DbUtil {
    private static Properties properties =  new Properties();
    static {
        InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        DruidDataSource dds = new DruidDataSource();
        dds.configFromPropety(properties);
        return dds;
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
