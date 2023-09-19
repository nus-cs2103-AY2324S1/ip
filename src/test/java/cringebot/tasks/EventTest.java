package cringebot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void toStringTest1() {
        Event newEvent = new Event("birthday (from: friday to: monday)");
        assertEquals("[E][ ] birthday (from: friday to: monday)", newEvent.toString());
    }

    @Test
    public void markEvent1() {
        Event newEvent = new Event("birthday (from: friday to: monday)");
        newEvent.markTask();
        assertEquals("[E][X] birthday (from: friday to: monday)", newEvent.toString());
    }

    @Test
    public void unmarkEvent1() {
        Event newEvent = new Event("birthday (from: friday to: monday)");
        newEvent.markTask();
        assertEquals("[E][X] birthday (from: friday to: monday)", newEvent.toString());
        newEvent.unMarkTask();
        assertEquals("[E][ ] birthday (from: friday to: monday)", newEvent.toString());
    }
}
