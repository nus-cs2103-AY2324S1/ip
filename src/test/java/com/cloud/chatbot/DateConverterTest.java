package com.cloud.chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;



// Assuming the "user" is on a GMT+8 system
public class DateConverterTest {
    @Test
    public void timestampToInstant() {
        Instant instantExpected = Instant.parse("2023-01-31T20:56:00Z");
        Instant instantActual = DateConverter.timestampToInstant("1 2 23 456");
        assertEquals(instantExpected, instantActual);
    }

    @Test
    public void instantToPrettyTimestamp_nonZeroMinutes() {
        Instant instant = Instant.parse("2023-01-31T20:56:00Z");
        String timestamp = DateConverter.instantToPrettyTimestamp(instant);
        assertEquals("1 Feb '23, 4:56am", timestamp);
    }

    @Test
    public void instantToPrettyTimestamp_zeroMinutes() {
        Instant instant = Instant.parse("2023-09-14T13:00:00Z");
        String timestamp = DateConverter.instantToPrettyTimestamp(instant);
        assertEquals("14 Sep '23, 9pm", timestamp);
    }
}
