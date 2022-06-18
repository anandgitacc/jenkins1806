package org.testrunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.reporting.ReportClass;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resources\\Features\\Login.feature",
					glue="org.stepdef",
					monochrome=true,
					plugin= {"pretty","json:src\\test\\resources\\Reports\\output1.json"}
					)
public class RunnerClass {
	
	@AfterClass
	public static void reportGen() {
		
		ReportClass.genReport("C:\\Users\\Welcome\\eclipse-workspace\\JenkinsTestOne"
				+ "\\src\\test\\resources\\Reports\\output1.json");
	}

}
