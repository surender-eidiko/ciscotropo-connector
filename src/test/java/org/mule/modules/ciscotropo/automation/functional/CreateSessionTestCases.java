/**
 * Copyright � 1992-2016 Cisco, Inc.
 */
package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class CreateSessionTestCases extends CiscoTropoAbtractTestCases {

  public CreateSessionTestCases(){
    super(CiscoTropoRestConnector.class);
  }

  @Category({FunctionalTests.class})
  @Test
  public void testCreateSession() throws Exception {

    String token = TestDataBuilder.getToken();
    Map<String, String> sesMap = TestDataBuilder.getSessionData();

    String sesResponse = getConnector().createSession(token, sesMap);

    assertNotNull(sesResponse);
  }

}
