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

    @Test
    public void postTest() throws IOException {
        RESTServiceClient client = new RESTServiceClient("iPhone","5CA0516726987400677A12F4082D6D7B");
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.setCategoryId(10);
        SearchView searchView = client.post("http://localhost:8080/search", SearchView.class, searchFilter);

        System.out.println(searchView.getList().get(0).getGoods_name());
    }

}
