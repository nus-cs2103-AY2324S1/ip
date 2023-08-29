package com.mimi.main;

import com.mimi.tasks.Deadline;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineEventCodeTest() {
        Deadline deadline = new Deadline("test", LocalDateTime.MIN);
        assertEquals("D", deadline.eventCode());
    }


}
