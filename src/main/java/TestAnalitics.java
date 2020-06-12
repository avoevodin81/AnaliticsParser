import models.xml.Suite;
import models.xml.Test;
import models.xml.TestClass;
import utils.HttpClientUtils;
import utils.JsonUtils;
import utils.XmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestAnalitics {

    public static void main(String[] args) throws Exception {
        String responseResult = HttpClientUtils.sendRequest();
        List<String> notPassedTests = JsonUtils.getTestNames(responseResult);
        Suite suite = XmlUtils.readTestSuite();

        List<Test> failedTests = new ArrayList<>();
        suite.getTests().forEach(test -> {
            if (isContainsFailedTest(test, notPassedTests)) {
                failedTests.add(getFailedClasses(test, notPassedTests));
            }
        });
        suite.setTests(failedTests);

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
}
