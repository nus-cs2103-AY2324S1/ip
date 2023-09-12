package cringebot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cringebot.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void toStringTest1() {
        Deadline newDeadline = new Deadline("CS2103T ip week 3", "2023-12-03");
        assertEquals("[D][ ] CS2103T ip week 3 (by: Dec 3 2023)", newDeadline.toString());
    }
    @Test
    public void toStringTest2() {
        Deadline newDeadline = new Deadline("CS2103T ip week 3", "12-02-2023");
        assertEquals("[D][ ] CS2103T ip week 3 (by: 12-02-2023)", newDeadline.toString());
    }

    @Test
    public void markDeadline1() {
        Deadline newDeadline = new Deadline("CS2103T ip week 3", "12-02-2023");
        newDeadline.markTask();
        assertEquals("[D][X] CS2103T ip week 3 (by: 12-02-2023)", newDeadline.toString());
    }

    @Test
    public void unmarkDeadline1() {
        Deadline newDeadline = new Deadline("CS2103T ip week 3", "12-02-2023");
        newDeadline.markTask();
        assertEquals("[D][X] CS2103T ip week 3 (by: 12-02-2023)", newDeadline.toString());

        newDeadline.unMarkTask();
        assertEquals("[D][ ] CS2103T ip week 3 (by: 12-02-2023)", newDeadline.toString());
    }
}
