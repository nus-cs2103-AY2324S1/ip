package com.nyanbot.dukeexceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeEmptyInputExceptionTest {
    @Test
    public void initTest() {
        DukeEmptyInputException dummy = new DukeEmptyInputException("test");
        String msg = dummy.toString();
        assertEquals(msg, "com.nyanbot.dukeexceptions.DukeEmptyInputException:"
                    + " OOPS!!! The description of a test cannot be empty.");
    }
}
