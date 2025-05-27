package com.TestRunner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "/Users/punya.jain/Documents/Java Project/TestingAmazon/Feature File/Amazon.feature",
    glue = {"com.TestAmazonCucumber"},
    plugin = {"pretty", "html:target/cucumber-report.html"},
    monochrome = true
)

public class TestRunnerCucumber {

}
