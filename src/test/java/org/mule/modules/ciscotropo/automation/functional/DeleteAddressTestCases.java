/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class DeleteAddressTestCases extends CiscoTropoAbtractTestCases{
  
  public DeleteAddressTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class})
  @Test
  public void deleteAddress() throws Exception {
    String applicationId = createApplication();
   String number =TestDataBuilder.getAddressNumber();
    
      String response = getConnector().deleteAddress(applicationId, number);
      assertNotNull(response);        
    
    assertNotNull(applicationId);
  }


}
