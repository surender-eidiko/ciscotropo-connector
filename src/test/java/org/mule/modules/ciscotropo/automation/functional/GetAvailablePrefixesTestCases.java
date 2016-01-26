/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class GetAvailablePrefixesTestCases extends
		CiscoTropoAbtractTestCases {
	
  public GetAvailablePrefixesTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class})
  @Test
	public void testAvailablePrefixes() throws Exception {
		
		String response = getConnector().getAvailablePrefixes("true", "1407");
	    assertNotNull(response);
		

	}

}
