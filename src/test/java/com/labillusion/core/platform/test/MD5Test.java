package com.labillusion.core.platform.test;

import com.labillusion.core.platform.crypto.MD5;
import org.junit.Test;

/**
 * Created by Administrator on 2014/10/20.
 */
public class MD5Test {

    @Test
    public void encrypt() throws Exception {
        System.out.println(MD5.encrypt("6762288"));
    }
}
