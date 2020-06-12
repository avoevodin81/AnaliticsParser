package utils;

import models.xml.Suite;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public class XmlUtils {

    private static Serializer serializer = new Persister();

    public static Suite readTestSuite() {
        //read source xml
        File source = new File(PropertyUtils.getPropertyData(PropertyUtils.FILE_INPUT));
        Suite suite = new Suite();
        try {
            suite = serializer.read(Suite.class, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suite;
    }

    public static void writeTestSuite(Suite suite) {
        File result = new File(PropertyUtils.getPropertyData(PropertyUtils.FILE_OUTPUT));
        try {
            serializer.write(suite, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
