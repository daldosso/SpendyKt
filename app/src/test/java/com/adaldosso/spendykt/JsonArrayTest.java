package com.adaldosso.spendykt;

import org.json.JSONArray;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class JsonArrayTest {

    @Test
    public void instantiateJSONArray() throws Exception {
        JSONArray jsonArray = new JSONArray("[" +
                "{'date': '01/01/2017', 'amount': 150.43}" +
                "{'date': '04/01/2017', 'amount': 50.00}" +
                "{'date': '06/01/2017', 'amount': 10.37}" +
                "]");

        assertNotNull(jsonArray);
    }

}
