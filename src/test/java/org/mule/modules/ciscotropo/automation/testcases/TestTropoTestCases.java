package org.mule.modules.ciscotropo.automation.testcases;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mule.common.Result;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;
import org.mule.tools.devkit.ctf.junit.AbstractTestCase;
import org.mule.tools.devkit.ctf.mockup.ConnectorDispatcher;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

public class TestTropoTestCases extends AbstractTestCase<CiscoTropoRestConnector> {

  
  private CiscoTropoRestConnector connector;
  private ConnectorDispatcher<CiscoTropoRestConnector> dispatcher;

  protected CiscoTropoRestConnector getConnector() {
    return connector;
  }

  protected ConnectorDispatcher<CiscoTropoRestConnector> getDispatcher() {
    return dispatcher;
  }

  @Before
  public void init() throws Exception {

    // Initialization for single-test run
    ConnectorTestContext.initialize(CiscoTropoRestConnector.class, false);

    // Context instance
    ConnectorTestContext<CiscoTropoRestConnector> context = ConnectorTestContext
      .getInstance(CiscoTropoRestConnector.class);

    // Connector dispatcher
    dispatcher = context.getConnectorDispatcher();

    connector = dispatcher.createMockup();

  }
  
  @Test
  //@Category({RegressionTests.class})
  public void testConnector() throws Exception {
    
    String applicationId = createApplication();
    Map<String,String> map = new HashMap<String,String>();
    map.put("type", "number");
    map.put("prefix", "1407");
    
    String response = getConnector().provisioningAddresses(applicationId, map);
    assertNotNull(response);
    
    Map<String,String> appMap = new HashMap<String,String>();
    appMap.put("name", "Eidiko Updated");
    map.put("platform", "scripting");
    map.put("partition", "staging");
    
    response = getConnector().provisioningApplication(applicationId, appMap);
    assertNotNull(response);
    
    response = getConnector().getAddressByApplication(applicationId);
    assertNotNull(response);
    
    response = getConnector().getAvailablePrefixes("true", "1407");
    assertNotNull(response);
    
    response = getConnector().getAddress();
    assertNotNull(response);
    
    response = getConnector().getApplications();
    assertNotNull(response);
    
    response = getConnector().getExchanges();
    assertNotNull(response);
    
    
    response = getConnector().deleteApplication(applicationId);
    assertNotNull(response);
    
    List<Object> list = getConnector().queryProcessor(applicationId);
    assertNotNull(list);
    
    Result<List<MetaDataKey>> metaDataKeysResult = dispatcher.fetchMetaDataKeys();
   // assertTrue(Result.Status.SUCCESS.equals(metaDataKeysResult.getStatus()));
    assertNotNull(metaDataKeysResult.getStatus());
    List<MetaDataKey> keyList = metaDataKeysResult.get();
    assertNotNull(keyList);
    for (MetaDataKey metaDataKey : keyList) {
      Result<MetaData> metaData = dispatcher.fetchMetaData(metaDataKey);
      assertNotNull(metaData);
      break;
    }
    
    }

  private String createApplication() {
    Map<String,String> map = new HashMap<String,String>();
    map.put("name", "eidiko");
    map.put("voiceUrl", "http://eidiko.com");
    map.put("messagingUrl", "http://eidiko.com");
    map.put("platform", "scripting");
    map.put("partition", "staging");
    String application = getConnector().createApplication(map);
    System.out.println(application);
    assertNotNull(application);
    JSONObject json = (JSONObject)new JSONObject(application);
    System.out.println("name=" + json.get("href"));
   String href = (String) json.get("href");
         String applicationId = href.substring(href.lastIndexOf("/")+1);
         System.out.println("application Id = "+ applicationId);
         return applicationId;
    
  }
  
 


}
