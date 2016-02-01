/**
 * Copyright � 1992-2016 Cisco, Inc.
 */
package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class AddEntityTestCases extends CiscoTropoAbtractTestCases {
    

  public AddEntityTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
    @Test
    @Category({FunctionalTests.class})
    public void testFlow() throws Exception {
        assertNotNull(getConnector().addEntity("ENTITY_TYPE_1", new HashMap<String, Object>()));
    }
}
