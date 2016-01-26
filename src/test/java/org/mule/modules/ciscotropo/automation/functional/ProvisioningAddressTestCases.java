/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class ProvisioningAddressTestCases extends CiscoTropoAbtractTestCases {
	
  public ProvisioningAddressTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class})
  @Test
	public void testProvisioningAddresses() throws Exception {
		String applicationId = createApplication();
		Map<String, String> map =TestDataBuilder.getProvisioningAddressData();
		
	    String response = getConnector().provisioningAddresses(applicationId, map);
	    assertNotNull(response);		    
				
	}

}
