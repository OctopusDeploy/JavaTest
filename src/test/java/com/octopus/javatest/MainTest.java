package com.octopus.javatest;

import org.junit.Assert;
import org.junit.Test;

/**
 * A test of the main class
 */
public class MainTest {
    @Test
    public void testReturnCode() {
        final int version = Main.getVersion(System.getProperty("java.version"));
        Assert.assertTrue(version >= 10050);
    }

    @Test
    public void testReturnCode2() {
        final int version = Main.getVersion("1.6");
        Assert.assertTrue(version == 10060);
    }
}
