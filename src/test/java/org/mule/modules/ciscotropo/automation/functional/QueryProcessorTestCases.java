/**
 * Copyright � 1992-2016 Cisco, Inc.
 */
package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;

public class QueryProcessorTestCases extends CiscoTropoAbtractTestCases {
    
  public QueryProcessorTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class}) 
  @Test
    public void testFlow() throws Exception {
        assertNotNull(getConnector().queryProcessor("SELECT age FROM ENTITY_TYPE_1"));
    }
}
