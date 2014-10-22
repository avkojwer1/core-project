package com.labillusion.core.rest.client;

import com.labillusion.core.platform.json.JSONBinder;
import com.labillusion.core.util.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

/**
 * Created by greg.chen on 2014/10/21.
 */
public class RESTServiceClient {
    private static Logger logger = LoggerFactory.getLogger(RESTServiceClient.class);
    private static final String HEADER_CLIENT_ID = "Client-Id";
    private static final String HEADER_SECRET_KEY = "Secret-Key";
    private static final String CHARSET_UTF_8 = "UTF-8";

    private String clientId;
    private String secretKey;

    public RESTServiceClient(String clientId, String secretKey){
        this.clientId = clientId;
        this.secretKey = secretKey;
    }

    public <T> T get(String uri, Class<T> responseClass) throws IOException {
        StopWatch stopWatch = new StopWatch();
        logger.debug("send request, url={}, method={}", uri, "Get");
        logger.debug("====== http request begin ======");
        HttpRequestBase request = new HttpGet(uri);
        prepareHeaders(request);
        HttpResponse response =createDefaultHttpClient().execute(request);

        int responseCode = response.getStatusLine().getStatusCode();
        validateStatusCode(responseCode);
        byte[] content = EntityUtils.toByteArray(response.getEntity());

        logger.debug("====== http request end ======");
        logger.debug("received response, statusCode={}, elapsed={}", response.getStatusLine().getStatusCode(), stopWatch.elapsedTime());

        request.releaseConnection();
        return JSONBinder.binder(responseClass).fromJSON(new String(content, "UTF-8"));
    }

    public <T, U> T post(String uri, Class<T> responseClass, U requestObj) throws IOException {
        StopWatch stopWatch = new StopWatch();
        logger.debug("send request, url={}, method={}", uri, "Post");
        logger.debug("====== http request begin ======");

        String body = JSONBinder.binder((Class<U>) requestObj.getClass()).toJSON(requestObj);
        AbstractHttpEntity entity = new StringEntity(body, CHARSET_UTF_8);
        HttpPost post = new HttpPost(uri);
        post.setEntity(entity);
        prepareHeaders(post);

        HttpResponse response =createDefaultHttpClient().execute(post);
        int responseCode = response.getStatusLine().getStatusCode();
        validateStatusCode(responseCode);
        byte[] content = EntityUtils.toByteArray(response.getEntity());

        logger.debug("====== http request end ======");
        logger.debug("received response, statusCode={}, elapsed={}", response.getStatusLine().getStatusCode(), stopWatch.elapsedTime());

        post.releaseConnection();

        return JSONBinder.binder(responseClass).fromJSON(new String(content, "UTF-8"));
    }

    @SuppressWarnings("unchecked")
    public <T, U> T put(String uri, Class<T> responseClass, U requestObj) throws IOException {
        StopWatch stopWatch = new StopWatch();
        logger.debug("send request, url={}, method={}", uri, "Put");
        logger.debug("====== http request begin ======");

        String body = JSONBinder.binder((Class<U>) requestObj.getClass()).toJSON(requestObj);
        AbstractHttpEntity entity = new StringEntity(body, CHARSET_UTF_8);
        HttpPut put = new HttpPut(uri);
        put.setEntity(entity);

        HttpResponse response =createDefaultHttpClient().execute(put);
        int responseCode = response.getStatusLine().getStatusCode();
        validateStatusCode(responseCode);
        byte[] content = EntityUtils.toByteArray(response.getEntity());

        logger.debug("====== http request end ======");
        logger.debug("received response, statusCode={}, elapsed={}", response.getStatusLine().getStatusCode(), stopWatch.elapsedTime());

        put.releaseConnection();

        return JSONBinder.binder(responseClass).fromJSON(new String(content, "UTF-8"));
    }

    @SuppressWarnings("unchecked")
    public <T, U> T delete(String uri, Class<T> responseClass) throws IOException {
        StopWatch stopWatch = new StopWatch();
        logger.debug("send request, url={}, method={}", uri, "Delete");
        logger.debug("====== http request begin ======");

        HttpRequestBase request = new HttpDelete(uri);
        prepareHeaders(request);
        HttpResponse response =createDefaultHttpClient().execute(request);

        int responseCode = response.getStatusLine().getStatusCode();
        validateStatusCode(responseCode);
        byte[] content = EntityUtils.toByteArray(response.getEntity());

        logger.debug("====== http request end ======");
        logger.debug("received response, statusCode={}, elapsed={}", response.getStatusLine().getStatusCode(), stopWatch.elapsedTime());

        request.releaseConnection();
        return null;
    }
    /**
     * 写入请求头
     * @param request
     */
    private void prepareHeaders(HttpRequestBase request) {
        request.setHeader(HEADER_CLIENT_ID,this.clientId);
        request.setHeader(HEADER_SECRET_KEY,this.secretKey);
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
