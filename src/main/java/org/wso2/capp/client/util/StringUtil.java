package org.wso2.capp.client.util;

import java.util.Random;

/**
 * Utility class to handle {@link String} related operations.
 *
 * @since 1.0.0
 */
public class StringUtil {

    /**
     * Returns whether the given string is a null or empty.
     *
     * @param string string to check whether null or empty
     * @return returns {@code true} if the string is null or empty, {@code false} otherwise
     */
    public static boolean isStringNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    /**
     * Returns a string concatenating the given strings.
     *
     * @param objects list of objects to concatenate as strings
     * @return concatenated string
     */
    public static String concatStrings(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object object : objects) {
            stringBuilder.append(object); // Null is handled by the append method.
        }
        return stringBuilder.toString();
    }

    /**
     * Generates a random string with the given character count using the below charset as input:
     * <i>ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890</i>
     *
     * @param characterCount number of characters
     * @return a random string
     */
    public static String generateRandomString(int characterCount) {
        final String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < characterCount) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
    }

    /**
     * Get time for summary logging purposes.
     *
     * @param timeDifferenceMilliseconds the time taken
     * @return human readable elapsed time
     */
    public static String getHumanReadableTimeDiff(long timeDifferenceMilliseconds) {
        long diffSeconds = timeDifferenceMilliseconds / 1000;
        long diffMinutes = timeDifferenceMilliseconds / (60 * 1000);
        long diffHours = timeDifferenceMilliseconds / (60 * 60 * 1000);
        long diffDays = timeDifferenceMilliseconds / (60 * 60 * 1000 * 24);

        if (diffSeconds < 1) {
            return "less than a second";
        } else if (diffMinutes < 1) {
            return diffSeconds + " seconds";
        } else if (diffHours < 1) {
            return diffMinutes + " minutes" + " " + (diffSeconds % 60) + " seconds";
        } else if (diffDays < 1) {
            return diffHours + " hours" + " " + (diffMinutes % 60) + " minutes";
        } else {
            return diffDays + " days" + " " + (diffHours % 24) + " hours";
        }
    }
}