package com.octopus.javatest;

import org.junit.Assert;
import org.junit.Test;

/**
 * A test of the main class
 */
public class MainTest {
    @Test
    public void testReturnCode() {
        final Main main = new Main();
        final int version = main.getVersion();
        Assert.assertTrue(version >= 15);
    }
}
