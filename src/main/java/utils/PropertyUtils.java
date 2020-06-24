package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    public static final String URL_SCHEME = "url.scheme";
    public static final String URL_HOST = "url.host";
    public static final String URL_PORT = "url.port";
    public static final String URL_PATH = "url.path";
    public static final String URL_RUN_ID = "url.run.id";

    public static final String USER_LOGIN = "user.login";
    public static final String USER_PASSWORD = "user.password";

    public static final String TEST_IS_NOT_EXECUTED = "test.isNotExecuted";

    public static final String FILE_INPUT = "file.input";
    public static final String FILE_OUTPUT = "file.output";
    public static final String FILE_TEMPLATE = "file.template";

    private static Properties prop = initPropertyData("src/main/resources/config.properties");

    public static String getPropertyData(String key) {
        return prop.getProperty(key);
    }

    private static Properties initPropertyData(String fileName) {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(IOException fnfe) {
            fnfe.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}
