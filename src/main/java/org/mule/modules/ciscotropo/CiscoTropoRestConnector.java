package org.mule.modules.ciscotropo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.Query;
import org.mule.api.annotations.lifecycle.Start;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.modules.ciscotropo.config.ConnectorConfig;

@Connector(name = "CiscoTropo", friendlyName = "CiscoTropo")
@MetaDataScope(DataSenseResolver.class)
public class CiscoTropoRestConnector {

  @Config
  ConnectorConfig config;

  private TropoClient client;

  @Start
  public void init() {//NOSONAR
    setClient(new TropoClient(this));
  }

  @Processor
  public String createSession(String token, Map<String, String> variables) {
    return getClient().createSession(token, variables);
  }

  @Processor
  public String createApplication(Map<String, String> variables) {
    return getClient().createApplication(variables);
  }

  @Processor
  public String provisioningAddresses(String appId, Map<String, String> variables) {
    return getClient().provisioningAddresses(appId, variables);
  }

  @Processor
  public String provisioningApplication(String appId, Map<String, String> variables) {
    return getClient().provisioningApplication(appId, variables);
  }

  @Processor
  public String deleteApplication(String appId) {
    return getClient().deleteApplication(appId);
  }

  @Processor
  public String deleteAddress(String appId, String number) {
    return getClient().deleteAddress(appId, number);
  }

  @Processor
  public String getApplications() {
    return getClient().getApplications();
  }

  @Processor
  public String getAddressByApplication(String appId) {
    return getClient().getAddressByApplication(appId);
  }

  @Processor
  public String getAddress() {
    return getClient().getAddress();
  }

  @Processor
  public String getExchanges() {
    return getClient().getExchanges();
  }

  @Processor
  public String getAvailablePrefixes(String available, String prefix) {
    return getClient().getAvailablePrefixes(available, prefix);
  }

  @Processor
  public String signalOperations(String sessionId, Map<String, String> variables) {
    return getClient().signalOperations(sessionId, variables);
  }

  @Processor
  public List<Object> queryProcessor(@Query String query) {

    return new ArrayList<Object>();
  }

  /**
   * DataSense processor
   *
   * {@sample.xml ../../../doc/eidiko-tropo-rest-connector.xml.sample eidiko-tropo-rest:add-entity}

   * @param key Key to be used to populate the entity
   * @param entity Map that represents the entity
   * @return Some string
   */
  @Processor
  public Map<String, Object> addEntity(@MetaDataKeyParam String key, @Default("#[payload]") Map<String, Object> entity) {//NOSONAR
    /*
     * USE THE KEY AND THE MAP TO DO SOMETHING
     */
    return entity;
  }

  public ConnectorConfig getConfig() {
    return config;
  }

  public void setConfig(ConnectorConfig config) {
    this.config = config;
  }

  public TropoClient getClient() {
    return client;
  }

  public void setClient(TropoClient client) {
    this.client = client;
  }

}
