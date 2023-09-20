package com.nyanbot.duketasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void initTest() {
        Todo test = new Todo("test");
        String testString = test.toString();
        assertEquals("[T][ ] test", testString);
    }
}
