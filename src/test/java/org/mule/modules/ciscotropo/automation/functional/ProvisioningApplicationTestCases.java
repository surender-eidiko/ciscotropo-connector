/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class ProvisioningApplicationTestCases extends CiscoTropoAbtractTestCases {
	
  public ProvisioningApplicationTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class})
  @Test
	public void testProvisioningApplication() throws Exception {
		String applicationId = createApplication();
		Map<String,String> appMap =TestDataBuilder.getProvisioningApplicationData();
	    String response = getConnector().provisioningApplication(applicationId, appMap);
	    assertNotNull(response);
		
	}

}
