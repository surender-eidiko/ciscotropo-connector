/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation;

import org.junit.Before;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;
import org.mule.tools.devkit.ctf.mockup.ConnectorDispatcher;
import org.mule.tools.devkit.ctf.mockup.ConnectorTestContext;

public abstract class AbstractTestCase {
	
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
		
		//Initialization for single-test run
        ConnectorTestContext.initialize(CiscoTropoRestConnector.class, false);
		
		//Context instance
		ConnectorTestContext<CiscoTropoRestConnector> context = ConnectorTestContext.getInstance(CiscoTropoRestConnector.class);
		
		//Connector dispatcher
		dispatcher = context.getConnectorDispatcher();
		
		connector = dispatcher.createMockup();
		
	}


}
