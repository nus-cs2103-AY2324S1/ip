package zean.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import zean.exception.ZeanException;

public class EventTest {
    @Test
    public void invalidFromDateTest1() {
        Exception exception = assertThrows(ZeanException.class, () ->
                new Event("0", "invalid from date", "2023-02-29", "2023-03-01"));
        assertEquals("The date is invalid!", exception.getMessage());
    }

    @Test
    public void invalidFromDateTest2() {
        Exception exception = assertThrows(ZeanException.class, () ->
                new Event("0", "invalid from date", "2023-13-13", "2024-03-01"));
        assertEquals("The date is invalid!", exception.getMessage());
    }

    @Test
    public void invalidToDateTest1() {
        Exception exception = assertThrows(ZeanException.class, () ->
                new Event("0", "invalid to date", "2023-02-28", "2023-02-29"));
        assertEquals("The date is invalid!", exception.getMessage());
    }

    @Test
    public void invalidToDateTest2() {
        Exception exception = assertThrows(ZeanException.class, () ->
                new Event("0", "invalid to date", "2023-02-28", "2023-32-29"));
        assertEquals("The date is invalid!", exception.getMessage());
    }

    @Test
    public void endBeforeStartDateTest1() {
        Exception exception = assertThrows(ZeanException.class, () ->
                new Event("0", "end before start date", "2023-02-28", "2023-01-29"));
        assertEquals("The end date is before the start date!", exception.getMessage());
    }

    @Test
    public void endBeforeStartDateTest2() {
        Exception exception = assertThrows(ZeanException.class, () ->
                new Event("0", "end before start date", "2024-01-28", "2023-01-29"));
        assertEquals("The end date is before the start date!", exception.getMessage());
    }

    @Test
    public void endBeforeStartDateTest3() {
        Exception exception = assertThrows(ZeanException.class, () ->
                new Event("0", "end before start date", "2023-08-29", "2023-08-20"));
        assertEquals("The end date is before the start date!", exception.getMessage());
    }

    @Test
    public void validFromFormatTest1() {
        Event event = new Event("0", "test", "2023-09-29", "2023-09-30");
        assertEquals("Sep 29 2023", event.getFrom());
    }

    @Test
    public void validFromFormatTest2() {
        Event event = new Event("0", "test", "2024-02-29", "2024-09-30");
        assertEquals("Feb 29 2024", event.getFrom());
    }

    @Test
    public void validToFormatTest1() {
        Event event = new Event("0", "test", "2023-09-29", "2023-09-30");
        assertEquals("Sep 30 2023", event.getTo());
    }

    @Test
    public void validToFormatTest2() {
        Event event = new Event("0", "test", "2023-09-29", "2024-02-29");
        assertEquals("Feb 29 2024", event.getTo());
    }
}
