/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class GetApplicationsTestCases extends CiscoTropoAbtractTestCases {

  public GetApplicationsTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class})
  @Test
	public void testApplications() throws Exception {
		
		String response = getConnector().getApplications();
	    assertNotNull(response);	

	}
}
