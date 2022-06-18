package org.reporting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class ReportClass {
	
	public static void genReport(String jsonFile) {
		
		// 1. to mention the path of the file
		File file = new File("C:\\Users\\Welcome\\eclipse-workspace\\JenkinsTestOne"
				+ "\\src\\test\\resources\\Reports");
		
		// 2. to create the object for configuration class
		Configuration configuration = new Configuration(file, "Fb Login testing");
		
		// 3. to add the classification
		configuration.addClassifications("Project", "Fb Login testing");
		configuration.addClassifications("Browser", "Chrome");
		configuration.addClassifications("Version", "102");
		
		// 4. to add the files into list
		List<String> jsonFiles = new ArrayList<String>();
		jsonFiles.add(jsonFile);
		
		// 5. to create object for ReportBuilder class
		ReportBuilder builder = new ReportBuilder(jsonFiles, configuration);
		
		// 6. to generate report
		builder.generateReports();
		
	}

}
