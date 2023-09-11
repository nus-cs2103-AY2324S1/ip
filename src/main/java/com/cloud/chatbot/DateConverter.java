package com.cloud.chatbot;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides utilities for working with timestamps.
 *
 * A timestamp is a string of a format that is easy to type.
 *
 * A pretty timestamp is a more complex string used for display purposes.
 */
public final class DateConverter {
    public static Instant timestampToInstant(String timestamp) {
        // Eg 1 2 23 456
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d M uu Hmm");

        LocalDateTime local = LocalDateTime.parse(timestamp, formatter);
        ZonedDateTime zoned = local.atZone(ZoneId.systemDefault());
        return zoned.toInstant();
    }

    public static String instantToPrettyTimestamp(Instant instant) {
        ZonedDateTime zoned = instant.atZone(ZoneId.systemDefault());

        // Eg 1 Feb '23, 4
        String timestamp = DateTimeFormatter
                .ofPattern("d MMM ''uu, h")
                .format(zoned);

        // Only add minutes that are not 00
        if (zoned.getMinute() != 0) {
            timestamp += DateTimeFormatter
                    .ofPattern(":mm")
                    .format(zoned);
        }

        // Lowercase the AM/PM
        timestamp += DateTimeFormatter
                .ofPattern("a")
                .format(zoned)
                .toLowerCase();

        // Eg 1 Feb '23, 4:56am
        return timestamp;
    }
}
