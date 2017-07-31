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
}
