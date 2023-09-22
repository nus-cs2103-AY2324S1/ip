package com.cloud.chatbot;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Arrays;

import org.junit.jupiter.api.Test;



// Assuming the "user" is on a GMT+8 or GMT/UTC system
public class DateConverterTest {
    @Test
    public void timestampToInstant() {
        Instant instantExpected = Instant.parse("2023-01-31T20:56:00Z");
        Instant instantActual = DateConverter.timestampToInstant("1 2 23 456");
        Instant instantActual2 = DateConverter.timestampToInstant("31 1 23 2056");
        assertTrue(
            Arrays.asList(instantActual, instantActual2)
                    .contains(instantExpected)
        );
    }

    @Test
    public void instantToPrettyTimestamp_nonZeroMinutes() {
        Instant instant = Instant.parse("2023-01-31T20:56:00Z");
        String timestamp = DateConverter.instantToPrettyTimestamp(instant);
        assertTrue(
            Arrays.asList("1 Feb '23, 4:56am", "31 Jan '23, 8:56pm")
                    .contains(timestamp)
        );
    }

    @Test
    public void instantToPrettyTimestamp_zeroMinutes() {
        Instant instant = Instant.parse("2023-09-14T13:00:00Z");
        String timestamp = DateConverter.instantToPrettyTimestamp(instant);
        assertTrue(
            Arrays.asList("14 Sep '23, 9pm", "14 Sep '23, 1pm")
                    .contains(timestamp)
        );
    }
}
