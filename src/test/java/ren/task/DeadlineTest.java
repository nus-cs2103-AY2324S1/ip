package ren.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    // write unit tests for Deadline's methods here

    @Test
    public void getDeadine() {
        assertEquals("Aug 24 2021", new Deadline("return book", false, LocalDate.parse("2021-08-24"))
                .toString()
                .split("by: ")[1]);
    }

}
