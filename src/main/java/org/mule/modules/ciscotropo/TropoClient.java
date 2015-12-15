package org.mule.modules.ciscotropo;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jackson.map.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class TropoClient {

  private static String TOKEN = "token";
  private Client client;
  private WebResource apiResource;
  private CiscoTropoRestConnector connector;
  private static final Logger log = Logger.getLogger(TropoClient.class
    .getName());

  public TropoClient(CiscoTropoRestConnector connector) {
    setConnector(connector);

    ClientConfig clientConfig = new DefaultClientConfig();
    clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
      Boolean.TRUE);
    this.client = Client.create(clientConfig);
    this.apiResource = this.client.resource(getConnector().getConfig()
      .getUrl());
  }

  public String createSession(String token, Map<String, String> variables) {
    WebResource webResource = getApiResource().path("sessions");
    variables.put(TOKEN, token);
    return (String) postData(variables, webResource, String.class);
  }

  public String createApplication(Map<String, String> variables) {
    WebResource webResource = getApiResource().path("applications");
    return (String) postData(variables, webResource, String.class);
  }

  public String provisioningAddresses(String appId,
    Map<String, String> variables) {
    WebResource webResource = getApiResource().path("applications")
      .path(appId).path("addresses");
    return (String) postData(variables, webResource, String.class);
  }

  public String provisioningApplication(String appId,
    Map<String, String> variables) {
    WebResource webResource = getApiResource().path("applications").path(
      appId);
    return (String) putData(variables, webResource, String.class);
  }

  public String deleteApplication(String appId) {
    WebResource webResource = getApiResource().path("applications").path(
      appId);
    return (String) deleteData(webResource, String.class);
  }

  public String deleteAddress(String appId, String number) {
    WebResource webResource = getApiResource().path("applications")
      .path(appId).path("addresses").path("number").path(number);
    return (String) deleteData(webResource, String.class);
  }

  public String getApplications() {
    WebResource webResource = getApiResource().path("applications");
    return (String) getData(webResource, String.class);
  }

  public String getAddressByApplication(String appId) {
    WebResource webResource = getApiResource().path("applications")
      .path(appId).path("addresses");
    return (String) getData(webResource, String.class);
  }

  public String getAddress() {
    WebResource webResource = getApiResource().path("addresses");
    return (String) getData(webResource, String.class);
  }

  public String getExchanges() {
    WebResource webResource = getApiResource().path("exchanges");
    return (String) getData(webResource, String.class);
  }

  public String getAvailablePrefixes(String available, String prefix) {
    WebResource webResource = getApiResource().path("addresses");
    MultivaluedMap queryParams = new MultivaluedMapImpl();
    queryParams.add("available", available);
    queryParams.add("prefix", prefix);

    webResource = webResource.queryParams(queryParams);
    return (String) getData(webResource, String.class);
  }

  public String signalOperations(String sessionId,
    Map<String, String> variables) {
    WebResource webResource = getApiResource().path("sessions")
      .path(sessionId).path("signals");
    return (String) postData(variables, webResource, String.class);
  }

  private Object getData(WebResource webResource, Class returnClass) {

    WebResource.Builder builder = addHeader(webResource);

    ClientResponse response = builder.get(ClientResponse.class);
    System.out.println(response.toString());
    return response.getEntity(returnClass);
  }

  private Object postData(Object request, WebResource webResource,
    Class returnClass) {
    WebResource.Builder builder = addHeader(webResource);
    builder.type(MediaType.APPLICATION_JSON);
    ObjectMapper mapper = new ObjectMapper();
    String input = convertObjectToString(request, mapper);
    ClientResponse clientResponse = builder.post(ClientResponse.class,
      input);

    logResponseCode(clientResponse);
    return clientResponse.getEntity(returnClass);
  }

  private Object putData(Object request, WebResource webResource,
    Class returnClass) {
    WebResource.Builder builder = addHeader(webResource);
    builder.type(MediaType.APPLICATION_JSON);
    ObjectMapper mapper = new ObjectMapper();
    String input = convertObjectToString(request, mapper);
    ClientResponse clientResponse = builder
      .put(ClientResponse.class, input);

    logResponseCode(clientResponse);
    return clientResponse.getEntity(returnClass);
  }

  private Object deleteData(WebResource webResource, Class returnClass) {
    WebResource.Builder builder = addHeader(webResource);
    ClientResponse clientResponse = builder.delete(ClientResponse.class);
    return clientResponse.getEntity(returnClass);

  }

  private WebResource.Builder addHeader(WebResource webResource) {
    WebResource.Builder builder = webResource
      .accept(MediaType.APPLICATION_JSON);

    builder.header("Authorization", connector.getConfig()
      .getAuthorization());
    return builder;
  }

  private void logResponseCode(ClientResponse clientResponse) {
    if (clientResponse.getStatus() == 200) {
      System.out.println("200 success");
    } else if (clientResponse.getStatus() == 401) {
      System.out.println("False 400");
    } else {
      System.out.println(clientResponse.getStatus());
      System.out.println("Error");
    }
  }

  private String convertObjectToString(Object request, ObjectMapper mapper) {
    String input = null;
    try {
      input = mapper.writeValueAsString(request);
    } catch (Exception ex) {
      log.log(Level.SEVERE, "Error", ex);
    }
    return input;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public WebResource getApiResource() {
    return apiResource;
  }

  public void setApiResource(WebResource apiResource) {
    this.apiResource = apiResource;
  }

  public CiscoTropoRestConnector getConnector() {
    return connector;
  }

  public void setConnector(CiscoTropoRestConnector connector) {
    this.connector = connector;
  }

}
