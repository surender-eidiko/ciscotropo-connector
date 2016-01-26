/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class SignalOperationsTestCases extends CiscoTropoAbtractTestCases {

  public SignalOperationsTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class})
  @Test
   public void testSignalOperations() throws Exception {

    String sessionId = TestDataBuilder.getSessionId();
    Map<String, String> appMap = TestDataBuilder.getSignalOperations();
    String response = getConnector().signalOperations(sessionId, appMap);
    assertNotNull(response);

  }
}
