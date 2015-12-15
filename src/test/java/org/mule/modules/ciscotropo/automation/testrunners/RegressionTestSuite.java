package org.mule.modules.ciscotropo.automation.testrunners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;
import org.mule.modules.ciscotropo.automation.testcases.AddEntityTestCases;
import org.mule.modules.ciscotropo.automation.testcases.QueryProcessorTestCases;
import org.mule.modules.ciscotropo.automation.testcases.TestSparkTestCases;
import org.mule.tools.devkit.ctf.junit.RegressionTests;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

@RunWith(Categories.class)
@IncludeCategory(RegressionTests.class)

@SuiteClasses({
  TestSparkTestCases.class
	,AddEntityTestCases.class
	,QueryProcessorTestCases.class
	
})

public class RegressionTestSuite {
	
	@BeforeClass
	public static void initialiseSuite(){
		
		ConnectorTestContext.initialize(CiscoTropoRestConnector.class);

	}
	
	@AfterClass
    public static void shutdownSuite() {

        ConnectorTestContext.shutDown();

    }
	
}