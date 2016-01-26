/**
 * Copyright � 1992-2016 Cisco, Inc.
 */
package org.mule.modules.ciscotropo.automation.runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;
import org.mule.modules.ciscotropo.automation.functional.AddEntityTestCases;
import org.mule.modules.ciscotropo.automation.functional.CreateApplicationTestCases;
import org.mule.modules.ciscotropo.automation.functional.CreateSessionTestCases;
import org.mule.modules.ciscotropo.automation.functional.DeleteApplicationTestCases;
import org.mule.modules.ciscotropo.automation.functional.FetchMetaDataKeysTestCases;
import org.mule.modules.ciscotropo.automation.functional.FetchMetaDataTestCases;
import org.mule.modules.ciscotropo.automation.functional.FunctionalTests;
import org.mule.modules.ciscotropo.automation.functional.GetAddressByApplicationTestCases;
import org.mule.modules.ciscotropo.automation.functional.GetAddressTestCases;
import org.mule.modules.ciscotropo.automation.functional.GetApplicationsTestCases;
import org.mule.modules.ciscotropo.automation.functional.GetAvailablePrefixesTestCases;
import org.mule.modules.ciscotropo.automation.functional.GetExchangesTestCases;
import org.mule.modules.ciscotropo.automation.functional.ProvisioningAddressTestCases;
import org.mule.modules.ciscotropo.automation.functional.ProvisioningApplicationTestCases;
import org.mule.modules.ciscotropo.automation.functional.QueryProcessorTestCases;
import org.mule.modules.ciscotropo.automation.functional.SignalOperationsTestCases;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

@RunWith(Categories.class)
@IncludeCategory(FunctionalTests.class)
@SuiteClasses({
	AddEntityTestCases.class
	,QueryProcessorTestCases.class
	,CreateSessionTestCases.class
	,CreateApplicationTestCases.class
	,ProvisioningApplicationTestCases.class
	,ProvisioningAddressTestCases.class
	,GetAddressTestCases.class
	,GetAddressByApplicationTestCases.class
	,GetAvailablePrefixesTestCases.class
	,DeleteApplicationTestCases.class
	,GetExchangesTestCases.class
	,FetchMetaDataTestCases.class
	,FetchMetaDataKeysTestCases.class
	,GetApplicationsTestCases.class,
	SignalOperationsTestCases.class
	
	
	
})

public class FunctionalTestSuite {
	
	@BeforeClass
	public static void initialiseSuite(){
		
		ConnectorTestContext.initialize(CiscoTropoRestConnector.class);

	}
	
	@AfterClass
    public static void shutdownSuite() {

        ConnectorTestContext.shutDown();

    }
	
}