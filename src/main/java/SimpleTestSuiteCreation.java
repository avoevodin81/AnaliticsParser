/*
 * Copyright © 2020 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 * CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.
 */

import models.xml.Suite;
import models.xml.Test;
import models.xml.TestClass;
import utils.HttpClientUtils;
import utils.JsonUtils;
import utils.PropertyUtils;
import utils.XmlUtils;

import java.util.ArrayList;
import java.util.List;

public class SimpleTestSuiteCreation {

    public static void main(String[] args) {
        String responseResult = HttpClientUtils.sendRequest();
        Suite suite = XmlUtils.readTestSuite(PropertyUtils.getPropertyData(PropertyUtils.FILE_TEMPLATE));

        // add not passed tests
        List<String> notPassedTests = JsonUtils.getFailedTestNames(responseResult);
        List<Test> tests = new ArrayList<>();
        notPassedTests.forEach(testName -> {
            Test test = new Test();
            test.setName(testName);
            TestClass testClass = new TestClass();
            testClass.setName(testName);
            test.setTestClass(testClass);
            tests.add(test);
        });

        suite.setTests(tests);
        XmlUtils.writeTestSuite(suite);
    }
}
