package com.ruuner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.report.Reporting;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(snippets=SnippetType.CAMELCASE,strict=true,dryRun=false,monochrome=true,features= "C:\\Users\\valli\\eclipse-workspace\\AdactinAutomate\\src\\test\\resources\\Feature\\Adactin.feature" ,
plugin = {"pretty","json:target\\Cucumber.json"}, glue="com.stepdef")

public class TestRunnerClass {
	@AfterClass 
	public static void afterClass() {
		Reporting.generateJVMReport(System.getProperty("user.dir")+"\\target\\Cucumber.json");

	}


}
