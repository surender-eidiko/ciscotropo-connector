/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;
import org.mule.tools.devkit.ctf.mockup.ConnectorDispatcher;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

public abstract class CiscoTropoAbtractTestCases extends AbstractTestCase<CiscoTropoRestConnector> {
  
  
  private CiscoTropoRestConnector connector;
  private ConnectorDispatcher<CiscoTropoRestConnector> dispatcher;
  
  
  public CiscoTropoAbtractTestCases(Class<CiscoTropoRestConnector> connector){
    super(connector);
  }
  
  protected CiscoTropoRestConnector getConnector() {
    return connector;
  }


  protected ConnectorDispatcher<CiscoTropoRestConnector> getDispatcher() {
    return dispatcher;
  }

  
  @Before
  public void init() throws Exception {
    
    //Initialization for single-test run
        ConnectorTestContext.initialize(CiscoTropoRestConnector.class, false);
    
    //Context instance
    ConnectorTestContext<CiscoTropoRestConnector> context = ConnectorTestContext.getInstance(CiscoTropoRestConnector.class);
    
    //Connector dispatcher
    dispatcher = context.getConnectorDispatcher();
    
    connector = dispatcher.createMockup();
    
  }

  public String createApplication() {
    Map<String, String> map = TestDataBuilder.getApplicationData();

    String application = getConnector().createApplication(map);

    assertNotNull(application);
    JSONObject json = (JSONObject) new JSONObject(application);
    String href = (String) json.get("href");
    String applicationId = href.substring(href.lastIndexOf("/") + 1);
    return applicationId;

  }
 

}
