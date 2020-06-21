import models.xml.Suite;
import models.xml.Test;
import models.xml.TestClass;
import utils.HttpClientUtils;
import utils.JsonUtils;
import utils.PropertyUtils;
import utils.XmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestAnalitics {

    public static void main(String[] args) throws Exception {
        String responseResult = HttpClientUtils.sendRequest();
        Suite suite = XmlUtils.readTestSuite();

        List<Test> failedOrNotExecutedTests = new ArrayList<>();

        // add not passed tests
        List<String> notPassedTests = JsonUtils.getFailedTestNames(responseResult);
        suite.getTests().forEach(test -> {
            if (isContainsFailedTest(test, notPassedTests)) {
                failedOrNotExecutedTests.add(getFailedClasses(test, notPassedTests));
            }
        });

        // add not executed tests
        if (Boolean.parseBoolean(PropertyUtils.getPropertyData(PropertyUtils.TEST_IS_NOT_EXECUTED))) {
            List<String> allTests = JsonUtils.getTestNames(responseResult);

            suite.getTests().forEach(test -> {
                if (!isContainsFailedTest(test, allTests)) {
                    failedOrNotExecutedTests.add(getNotExecutedClasses(test, allTests));
                }
            });
        }

        suite.setTests(failedOrNotExecutedTests);

        XmlUtils.writeTestSuite(suite);
    }

    private static boolean isContainsFailedTest(Test test, List<String> failedTests) {
        return test.getTestClasses().stream().anyMatch(testClass -> failedTests.contains(testClass.getName()));
    }

    private static Test getFailedClasses(Test test, List<String> failedTests) {
        List<TestClass> classes = test.getTestClasses().stream()
                .filter(testClass -> failedTests.contains(testClass.getName())).collect(Collectors.toList());
        test.setTestClasses(classes);
        return test;
    }

    private static Test getNotExecutedClasses(Test test, List<String> executedTests) {
        List<TestClass> classes = test.getTestClasses().stream()
                .filter(testClass -> !executedTests.contains(testClass.getName())).collect(Collectors.toList());
        test.setTestClasses(classes);
        return test;
    }
}
