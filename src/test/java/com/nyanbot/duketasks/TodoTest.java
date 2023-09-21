package com.nyanbot.duketasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void initTest() {
        Todo test = new Todo("test");
        String testString = test.toString();
        assertEquals("[T][ ] test", testString);
    }
}
