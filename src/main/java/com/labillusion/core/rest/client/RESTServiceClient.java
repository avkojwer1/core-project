package com.labillusion.core.rest.client;

import com.labillusion.core.platform.json.JSONBinder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

/**
 * Created by Administrator on 2014/10/21.
 */
public class RESTServiceClient {
    private static Logger logger = LoggerFactory.getLogger(RESTServiceClient.class);
    private static final String HEADER_CLIENT_ID = "Client-Id";
    private static final String HEADER_Secret_Key = "Secret-Key";

    private String clientId;
    private String secretKey;

    public RESTServiceClient(String clientId, String secretKey){
        this.clientId = clientId;
        this.secretKey = secretKey;
    }

    public <T> T get(String uri, Class<T> responseClass) throws IOException {
        logger.debug("send request, url={}, method={}", uri, "Get");
        logger.debug("====== http request begin ======");
        HttpRequestBase request = new HttpGet(uri);
        prepareHeaders(request);
        HttpResponse response =createDefaultHttpClient().execute(request);
        logger.debug("====== http request end ======");
        logger.debug("received response, statusCode={}", response.getStatusLine().getStatusCode());

        int responseCode = response.getStatusLine().getStatusCode();
        validateStatusCode(responseCode);
        byte[] content = EntityUtils.toByteArray(response.getEntity());

        return JSONBinder.binder(responseClass).fromJSON(new String(content, "UTF-8"));
    }

    /**
     * 写入请求头
     * @param request
     */
    private void prepareHeaders(HttpRequestBase request) {
        request.setHeader("Client-Id",this.clientId);
        request.setHeader("Secret-Key",this.secretKey);
        request.setHeader("Content-Type", HTTPConstants.CONTENT_TYPE_JSON);
    }

    /**
     * 验证Response code
     * @param code
     */
    private void validateStatusCode(int code) {
        if (code >= HTTPConstants.SC_OK && code <= HTTPConstants.SC_MULTI_STATUS) {
            return;
        }
        throw new HTTPException("invalid response status code, statusCode=" + code);
    }

    /**
     * 获取HttpClient
     * @return
     */
    private HttpClient createDefaultHttpClient() {
        HttpClient client = HttpClientBuilder.create().build();

        return client;
    }
}
