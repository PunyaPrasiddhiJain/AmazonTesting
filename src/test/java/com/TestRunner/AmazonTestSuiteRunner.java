package com.TestRunner;

import org.testng.TestNG;
import java.util.Collections;

public class AmazonTestSuiteRunner {
    public static void main(String[] args) {
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList("testng.xml")); // Make sure the path is correct
        testng.run();
    }
}
