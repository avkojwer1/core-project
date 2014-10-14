package com.labillusion.core.platform.test;

import com.labillusion.core.base.BaseTest;
import com.labillusion.core.platform.web.rest.exception.ErrorResponseBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by greg.chen on 14-10-14.
 */
public class ErrorResponseBuilderTest extends BaseTest{

    @Autowired
    protected ErrorResponseBuilder errorResponseBuilder;

    @Test
    public void CreateErrorResponse(){
       errorResponseBuilder.createErrorResponse(null);
    }

}
