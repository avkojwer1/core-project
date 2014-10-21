package com.labillusion.core.rest.client;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2014/10/21.
 */
public class RESTServiceClientTest {

    @Test
    public void getTest() throws IOException {
        RESTServiceClient client = new RESTServiceClient("iPhone","5CA0516726987400677A12F4082D6D7B");

        GoodsView v = client.get("http://localhost:8080/goods/10", GoodsView.class);

        System.out.println(v.getGoods_name());

    }

}
