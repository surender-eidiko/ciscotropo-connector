/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class TestDataBuilder {

  public static Map<String, String> getApplicationData() {
    final Map<String, String> m = ImmutableMap.<String, String>builder().
      put("name", "eidiko").
      put("voiceUrl", "http://eidiko.com").
      put("messagingUrl", "http://eidiko.com").
      put("platform", "scripting").
      put("partition", "staging").
      build();
    return m;
  }
  
  public static Map<String,String> getSessionData()
  {
	  final Map<String, String> smap = ImmutableMap.<String, String>builder().
	    put("customerName", "Surender").
	    put("numberToDial", "9998884422").
	    put("msg","Hii").
	    build() ;
	    return smap;
  }
  
  public static Map<String,String> getProvisioningApplicationData()
  {

	  final Map<String, String> pAppmap = ImmutableMap.<String, String>builder().
			  put("name", "Eidiko Updated").
			  put("platform", "scripting").
			  put("partition", "staging").
	          build() ;
	    return pAppmap;
  }
  
  public static Map<String,String> getProvisioningAddressData()
  {
	  final Map<String, String> pAddrmap = ImmutableMap.<String, String>builder().
			  put("type", "number").
			  put("prefix", "1407").
	          build() ;
	    return pAddrmap;
  }
 
  public static Map<String,String> getSignalOperations()
  {
	  final Map<String, String> pAddrmap = ImmutableMap.<String, String>builder().
			  put("signal", "exit").
	          build() ;
	    return pAddrmap;
  }
  public static String getToken()
  {
    String token ="11eb459ef811c446a0ea6ccb1dbda737f4069dc76c5b25580c65c7b9dd4b895666577fe2ea597ac1610c58e0";
    return token;
  }
  
  public static String getSessionId()
  {
    String sessionId ="859d69e6c6e32738491288bc114c0a92";
    return sessionId;
  }

  public static String getAddressNumber() {
    return "1407";
  }
  
  
}
