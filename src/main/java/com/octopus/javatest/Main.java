package com.octopus.javatest;

/**
 * A simple application to check the current java version against
 * a minimum version
 */
public class Main {
    private static final int ERROR_EXIT_CODE = 1;
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int MULTIPLIER = 10;
    private static final int PADDING = 3;

    public static void main(final String[] args) {
        try {
            if (args.length != 1) {
                System.exit(ERROR_EXIT_CODE);
            }

            final int currentVersion = getVersion(System.getProperty("java.version"));
            final int minimumVersion = getVersion(args[0]);

            System.exit(currentVersion >= minimumVersion ? SUCCESS_EXIT_CODE : ERROR_EXIT_CODE);
        } catch (final Exception ex) {
            /*
                On the off chance there is some edge case not accounted
                for in getVersion().
             */
            System.exit(ERROR_EXIT_CODE);
        }
    }

    static int getVersion(final String version) {
        /*
            We expect this to be a string with a version number.
            Null is a problem.
         */
        if (version == null) {
            throw new IllegalArgumentException();
        }

        final int pos = version.indexOf('.');

        /*
            Prior to Java 9, we expect to find versions like "1.8.0".
            With Java 9 we can have values like "9" with JEP 223
         */
        if (pos == -1) {
            return Integer.parseInt(version) * MULTIPLIER * (int) Math.pow(10, PADDING);
        }

        final int nextPos = version.indexOf('.', pos + 1);

        /*
            If nextPos == -1, we assume the input is major.minor i.e. 1.6
         */
        final String versionWithMajorMinor = version.substring(0, nextPos == -1 ? version.length() : nextPos);

        /*
            Split the version into major and minor
         */
        final String[] versionSplit = versionWithMajorMinor.split("\\.");

        /*
            Treat each part of the version as an individual number. We multiply
            each by 10, pad each to 3 places, and recombine them to give a single
            integer. So 1.9 becomes 10090. This allows us to deal with situations
            like testing version 1.1 and 1.10.
         */
        try {
            final String formattedVersion = "" + (Integer.parseInt(versionSplit[0]) * MULTIPLIER) +
                    String.format("%" + PADDING + "s", Integer.parseInt(versionSplit[1]) * MULTIPLIER).replaceAll(" ", "0");

            return Integer.parseInt(formattedVersion);
        } catch (final NumberFormatException ex) {
            /*
                We didn't find the expected version number.
             */
            throw new IllegalArgumentException();
        }
    }
}
