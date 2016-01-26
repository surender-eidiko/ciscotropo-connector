/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class DeleteApplicationTestCases extends
		CiscoTropoAbtractTestCases {
  
  
  public DeleteApplicationTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class})
  @Test
	public void testDeleteApplication() throws Exception {
		 String applicationId = createApplication();
		 String response = getConnector().deleteApplication(applicationId);
	     assertNotNull(response);	

	}

}
