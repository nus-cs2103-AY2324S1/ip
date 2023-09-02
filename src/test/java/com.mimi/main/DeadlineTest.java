package com.mimi.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.mimi.tasks.Deadline;


public class DeadlineTest {
    @Test
    public void deadlineEventCodeTest() {
        Deadline deadline = new Deadline("test", LocalDateTime.MIN);
        assertEquals("D", deadline.eventCode());
    }


}
