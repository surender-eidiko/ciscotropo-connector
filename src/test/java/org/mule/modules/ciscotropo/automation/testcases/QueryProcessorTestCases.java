package org.mule.modules.ciscotropo.automation.testcases;

import static org.junit.Assert.assertNotNull;

import org.mule.modules.ciscotropo.automation.AbstractTestCase;
import org.mule.tools.devkit.ctf.junit.RegressionTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QueryProcessorTestCases extends AbstractTestCase {
    
    @Test
    @Category({RegressionTests.class})
    public void testFlow() throws Exception {
        assertNotNull(getConnector().queryProcessor("SELECT age FROM ENTITY_TYPE_1"));
    }
}
