/**
 * Copyright � 1992-2016 Cisco, Inc.
 */

package org.mule.modules.ciscotropo.automation.functional;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.common.Result;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.modules.ciscotropo.CiscoTropoRestConnector;
import org.mule.tools.devkit.ctf.junit.MetaDataTest;

public class FetchMetaDataTestCases extends CiscoTropoAbtractTestCases {

 
  public FetchMetaDataTestCases(){
    super(CiscoTropoRestConnector.class);
  }
  
  @Category({FunctionalTests.class})
  @Test
  @MetaDataTest
  public void testMetaData() throws Exception{


    Result<List<MetaDataKey>> metaDataKeysResult = getDispatcher().fetchMetaDataKeys();
    assertTrue(Result.Status.SUCCESS.equals(metaDataKeysResult.getStatus()));

    List<MetaDataKey> metaDataKeys = metaDataKeysResult.get();

    MetaDataKey user = null;
    MetaDataKey book = null;

    for (MetaDataKey key : metaDataKeys) {
      if (user == null && key.getId().equals("USER"))
        user = key;
      if (book == null && key.getId().equals("BOOK"))
        book = key;
    }


//Asserts on MetaData
      Result<MetaData> recipeKeyResult = getDispatcher().fetchMetaData(user);
                  assertTrue(Result.Status.SUCCESS.equals(recipeKeyResult.getStatus()));

                  Result<MetaData> ingredientKeyResult = getDispatcher().fetchMetaData(book);
                  assertTrue(Result.Status.SUCCESS.equals(ingredientKeyResult.getStatus()));
  }
}
