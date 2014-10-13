package com.labillusion.core.util.test;

import com.labillusion.core.util.StringUtils;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by greg.chen on 14-10-13.
 */
public class StringUtilsTest {

    @Test
    public void hasText(){
        String s = null;
        Assert.assertTrue(!StringUtils.hasText(s));
    }
}
