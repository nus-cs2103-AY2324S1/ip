package com.nyanbot.dukeexceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeEmptyTaskListExceptionTest {
    @Test
    public void initTest() {
        DukeEmptyTaskListException dummy = new DukeEmptyTaskListException();
        String msg = dummy.toString();
        System.out.println(msg);
        assertEquals(msg, "com.nyanbot.dukeexceptions.DukeEmptyTaskListException: OOPS!!! The tasks list is empty!");
    }
}
