package com.octopus.javatest;

/**
 * A simple application to return the java version as the
 * application exit code.
 */
public class Main {
    private static final int ERROR_EXIT_CODE = -1;

    public static void main(final String[] args) {
        try {
            System.exit(new Main().getVersion());
        } catch (final Exception ex) {
            /*
                On the off chance there is some edge case not accounted
                for in getVersion().
             */
            System.exit(ERROR_EXIT_CODE);
        }
    }

    int getVersion() {
        final String version = System.getProperty("java.version");

        /*
            We expect this to be a string with a version number.
            Null is a problem.
         */
        if (version == null) {
            return ERROR_EXIT_CODE;
        }

        final int pos = version.indexOf('.');

        /*
            We expect the version number to have decimal places.
            If not, this is a problem.
         */
        if (pos == -1) {
            return ERROR_EXIT_CODE;
        }

        final int nextPos = version.indexOf('.', pos + 1);

        /*
            We expect the version number to have two decimal places.
            If not, this is a problem.
         */
        if (nextPos == -1) {
            return ERROR_EXIT_CODE;
        }

        final String versionWithMajorMinor = version.substring(0, nextPos);

        /*
            We are interested in the major version, and the first value of
            the minor version. This is converted to an int.

            What does this mean for Java 1.10? As an int that is the same as
            Java 1.1. I guess we'll cross that bridge in a couple of years...
         */
        try {
            return (int) (Double.parseDouble(versionWithMajorMinor) * 10);
        } catch (final NumberFormatException ex) {
            /*
                We didn't find the expected version number.
             */
            return ERROR_EXIT_CODE;
        }
    }
}
