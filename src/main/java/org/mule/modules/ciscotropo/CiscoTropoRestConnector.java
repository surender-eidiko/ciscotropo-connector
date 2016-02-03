/**
 * Copyright � 1992-2016 Cisco, Inc.
 */
package org.mule.modules.ciscotropo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.Query;
import org.mule.api.annotations.licensing.RequiresEnterpriseLicense;
import org.mule.api.annotations.lifecycle.Start;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.modules.ciscotropo.config.ConnectorConfig;

/**
 * This is Cisco Tropo Connector Class
 * @author Surender
 *
 * 
 */
@Connector(name = "CiscoTropo", friendlyName = "CiscoTropo", minMuleVersion = "3.7.2")
@RequiresEnterpriseLicense
@MetaDataScope(DataSenseResolver.class)
public class CiscoTropoRestConnector {

  @Config
  ConnectorConfig config;

  private TropoClient client;

  /**
   * 
   */

  @Start
  public void init() {// NOSONAR
    setClient(new TropoClient(this));
  }

  /**
  * This method creates Session Object
  * @param token
  - token : This is the token associated with your application. Tropo uses the token to identify your app when it launches the session. This field is required.<BR>
  * @param variables , This identifies variables you want to pass; the only required variable is , though you can use as many additional variables as you'd like. This field is optional.<BR>
  * @return String ,This confirms whether the token launch was successful - possible values are true and false. This field is read-only.
  */

  @Processor
  public String createSession(String token, @Default("#[payload]") Map<String, String> variables) {
    return getClient().createSession(token, variables);
  }
  /**
  * This method create Application Object
  * @param variables
  * List of variables: <BR>
  - name :   The name of the application. This field is required.<BR>
  - voiceUrl :  The URL that powers voice calls for your application. This field is optional, but must be set if messagingUrl is left empty.<BR>
  - messagingUrl :  The URL that powers SMS/messaging calls for your application. This field is optional, but must be set if voiceUrl is left empty.<BR>
  - platform :  This defines whether the application will use the Scripting API ("scripting") or the Web API ("webapi"). This field is required.<BR>
  - partition :   This defines whether the application is in "staging" (our free platform for development) or "production" (our paid platform for live applications). This field is optional and defaults to staging.<BR>
  * @return String , The REST address for the application. This field is read-only.
  */

  @Processor
  public String createApplication(@Default("#[payload]") Map<String, String> variables) {
    return getClient().createApplication(variables);
  }

  
  
  /**
   * This method create Address Object using appId
  
   * 
   * @param appId
   * List of variables: <BR>
   - type : This defines the type of address. The possible types are number (phone numbers and iNum), token, and sip.<BR>
   - prefix : This defines the country code and area code for a phone number. This field will not display if you use a GET on a particular address, it's only relevant when requesting a auto-provisioned phone number. If the type parameter is set to 'number', you need to include either a value for this parameter or for number.<BR>
   * @param variables
   * List of variables: <BR>
   - number : This is used when you want to assign a specific phone number to an application rather than request one based on prefix. See the Adding a Specific Number / Moving a Number example for more details. If the type parameter is set to 'number', you need to include either a value for this parameter or for prefix.<BR>
   - city : If you run a GET on the addresses associated with your application, this will display the city associated with an already assigned phone number. This field is read-only.<BR>
   - state : If you run a GET on the addresses associated with your application, this will display the state associated with an already assigned phone number. This field is read-only.<BR>
   - channel : This applies only to tokens and identifies the type of channel used by the token. It can be either "voice" or "messaging". This field is only required to be set if you add a token manually (this is rare), otherwise it will just display when you use a GET on an account.<BR>
   - token : This is a long, alphanumeric string that identifies your Tropo app. It's used when you need the application to launch an outbound call based on an external event, like when a user clicks on a link on a web site. You can manually add a token, but it's rare.<BR>
   * @return String
   */
@Processor
  public String provisioningAddresses(String appId, @Default("#[payload]") Map<String, String> variables) {
    return getClient().provisioningAddresses(appId, variables);
  }

  /**
   * This Object is create Application Object using appId
   * @param appId
   *  Sometimes you need to access a list of the applications associated with your account; maybe you need to find a particular application's ID number or need to verify whether it's a Scripting or WebAPI application.<BR>
   * @param variables 
   * Using a GET on the applications folder will provide you with the information you need.<BR>
   * @return String
   */
  @Processor
  public String provisioningApplication(String appId, @Default("#[payload]") Map<String, String> variables) {
    return getClient().provisioningApplication(appId, variables);
  }

  /**
   * This method remove application using appId
   * @param appId
   * Use this method to remove an application. This cannot be undone; once an application has been deleted, it cannot be restored without recreating it from scratch.<BR>
   * @return String
   */
@Processor
  public String deleteApplication(String appId) {
    return getClient().deleteApplication(appId);
  }

  /**
   * This method delete address using appId and number
   * @param appId
   *  Sometimes you need to access a list of the applications associated with your account; maybe you need to find a particular application's ID number or need to verify whether it's a Scripting or WebAPI application.<BR>
   * @param number
   * If you remove a phone number, it will become available for use by someone else, so it's not recommended to delete a phone number unless you're absolutely sure you have no further need for it. If you want to move a phone number between applications, there's an easier way.<BR>
   * @return String
   */
@Processor
  public String deleteAddress(String appId, @Default("#[payload]") String number) {
    return getClient().deleteAddress(appId, number);
  }

  /**
   * This method get the application object
   * @return String
   */
@Processor
  public String getApplications() {
    return getClient().getApplications();
  }

  /**
   * This method get the address by using appId
   * @param appId
   * Get the address using application id.<BR>
   * @return String
   */
@Processor
  public String getAddressByApplication(String appId) {
    return getClient().getAddressByApplication(appId);
  }

  /**
   * This method get the address object
   * @return String
   */
@Processor
  public String getAddress() {
    return getClient().getAddress();
  }

  /**
   * This method create Exchange object
   * List of variables: <BR>
   - prefix : This displays the country code and area codes available for use when you request a phone number from the number pool. This field is read-only.<BR>
   - city : This identifies the city associated with a prefix, e.g. Orlando. This field is read-only.<BR>
   - state : This identifies the state associated with a prefix, e.g. FL. This field is read-only.<BR>
   - country : This identifies the country associated with a prefix, e.g. United States. This field is read-only.<BR>
   - description : This identifies the type of phone number; possible values are "Phone Number w/SMS", "Toll Free Phone Number" and "International Phone Number". This field is read-only.<BR>
   * @return String
   */
@Processor
  public String getExchanges() {
    return getClient().getExchanges();
  }

  /**
   * This method get the availableprefix object
   * @param available
   * When viewing available exchanges, you only get a list of country codes for anywhere except the U.S.<BR>
   * @param prefix
   * If you want to view deeper, such as checking to see if Tropo has Tel Aviv numbers available in Israel.<BR>
   * @return String
   */
@Processor
  public String getAvailablePrefixes(String available, @Default("#[payload]") String prefix) {
    return getClient().getAvailablePrefixes(available, prefix);
  }

  /**
   * This method is create SignalOperation Object
   * @param sessionId
   * signal 	This is the signal used to interrupt the function. This field is required.<BR>
   * @param variables
   * List of variables: <BR>
   - status : This identifies whether the interrupt was successful or not. Possible values are:<BR>
			  QUEUED - the event delivered successfully to the event queue of the target.<BR>
			  NOTFOUND - the target session could not be found.<BR>
			  FAILED - some other failure occurred.This field is read-only.<BR>
 * @return String
 */
@Processor
  public String signalOperations(String sessionId, @Default("#[payload]") Map<String, String> variables) {
    return getClient().signalOperations(sessionId, variables);
  }

  /**
   * This method process the query
   * @param query
   * Process the Query .<BR>
   * @return List Object
   */
@Processor
  public List<Object> queryProcessor(@Query String query) {

    return new ArrayList<Object>();
  }

  /**
   * DataSense processor
   *
   * {@sample.xml ../../../doc/eidiko-tropo-rest-connector.xml.sample eidiko-tropo-rest:add-entity}

   * @param key Key to be used to populate the entity.<BR>
   * @param entity Map that represents the entity.<BR>
   * @return Some string
   */
  /**
   * This method add the Entity using key
   * @param key
   * Add the Entity Object using key.<BR>
   * @param entity
   * By using the key return the entity object.<BR>
   * @return Map Object
   */
@Processor
  public Map<String, Object> addEntity(@MetaDataKeyParam String key, @Default("#[payload]") Map<String, Object> entity) {// NOSONAR
    /*
     * USE THE KEY AND THE MAP TO DO SOMETHING
     */
    return entity;
  }

  /**
 * @return
 */
public ConnectorConfig getConfig() {
    return config;
  }

  /**
 * @param config
 */
public void setConfig(ConnectorConfig config) {
    this.config = config;
  }

  /**
 * @return
 */
public TropoClient getClient() {
    return client;
  }

  /**
 * @param client
 */
public void setClient(TropoClient client) {
    this.client = client;
  }

}
