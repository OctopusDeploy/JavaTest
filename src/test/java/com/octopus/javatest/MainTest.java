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

    @Test
    public void testVersionJava9() {
        final int version = Main.getVersion("9");
        Assert.assertTrue(version == 90000);
    }

    @Test
    public void testVersionJava9Internal() {
        final int version = Main.getVersion("9-internal");
        Assert.assertTrue(version == 90000);
    }

    @Test
    public void testVersionJava90() {
        final int version = Main.getVersion("9.0");
        Assert.assertTrue(version == 90000);
    }

    @Test
    public void testVersionJava901() {
        final int version = Main.getVersion("9.0.1");
        Assert.assertTrue(version == 90000);
    }

    @Test
    public void testVersionJava91() {
        final int version = Main.getVersion("9.1");
        Assert.assertTrue(version == 90010);
    }

    @Test
    public void testVersionJava10() {
        final int version = Main.getVersion("10");
        Assert.assertTrue(version == 100000);
    }
}
