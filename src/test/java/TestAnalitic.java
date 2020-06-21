import models.xml.Suite;
import models.xml.TestClass;
import org.testng.annotations.Test;
import utils.HttpClientUtils;
import utils.JsonUtils;
import utils.XmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestAnalitic {

    @Test
    public void getFailedTests() {
        String responseResult = HttpClientUtils.sendRequest();
        List<String> notPassedTests = JsonUtils.getFailedTestNames(responseResult);
        Suite suite = XmlUtils.readTestSuite();

        List<models.xml.Test> failedTests = new ArrayList<>();
        suite.getTests().forEach(test -> {
            if (isContainsFailedTest(test, notPassedTests)) {
                failedTests.add(getFailedClasses(test, notPassedTests));
            }
        });
        suite.setTests(failedTests);

        XmlUtils.writeTestSuite(suite);
    }

    @Test
    public void getNotExecutedTests() {
        String responseResult = HttpClientUtils.sendRequest();
        List<String> allTests = JsonUtils.getTestNames(responseResult);
        Suite suite = XmlUtils.readTestSuite();

        List<models.xml.Test> notExecutedTests = new ArrayList<>();
        suite.getTests().forEach(test -> {
            if (!isContainsFailedTest(test, allTests)) {
                notExecutedTests.add(getNotExecutedClasses(test, allTests));
            }
        });
        suite.setTests(notExecutedTests);

        XmlUtils.writeTestSuite(suite);
    }

    private static boolean isContainsFailedTest(models.xml.Test test, List<String> failedTests) {
        return test.getTestClasses().stream().anyMatch(testClass -> failedTests.contains(testClass.getName()));
    }

    private static models.xml.Test getNotExecutedClasses(models.xml.Test test, List<String> executedTests) {
        List<TestClass> classes = test.getTestClasses().stream()
                .filter(testClass -> !executedTests.contains(testClass.getName())).collect(Collectors.toList());
        test.setTestClasses(classes);
        return test;
    }

    private static models.xml.Test getFailedClasses(models.xml.Test test, List<String> failedTests) {
        List<TestClass> classes = test.getTestClasses().stream()
                .filter(testClass -> failedTests.contains(testClass.getName())).collect(Collectors.toList());
        test.setTestClasses(classes);
        return test;
    }
}
