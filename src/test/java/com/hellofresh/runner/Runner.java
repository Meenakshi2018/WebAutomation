package com.hellofresh.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/*
 * This is a runner file for Cucumber - will create Cucumber report in HTML and JSON format
 * JSON report will be consumed by Maven Cucumber Reporting plugin for beautiful reports
 */
public class Runner {
	@CucumberOptions(
			features = {"src/test/java/com/hellofresh/features"},
			glue = "com.hellofresh.steps",
			plugin = {"pretty", "html:target/simplereport", "json:target/cucumber.json"},
			monochrome = true
			)
		
		//TestNG - Cucumber
		public class runner extends AbstractTestNGCucumberTests {
		
	}
}