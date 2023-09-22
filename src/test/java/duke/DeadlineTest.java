package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.ronaldo.RonaldoSaysDo;
import duke.ronaldo.TaskType;
import duke.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void deadline_toString_success() {
        Deadline deadline = new Deadline("CS2103T iP", "2022-12-03" , "09:00", TaskType.DEADLINE);
        assertEquals("[D][ ] CS2103T iP(by: Dec 03 2022 09:00)", deadline.toString());
    }
    @Test
    public void deadline_setMarkAgain_exception() {
        try {
            Deadline deadline = new Deadline("CS2103T iP", "2022-12-03" , "09:00", TaskType.DEADLINE);
            deadline.setMarked();
            deadline.setMarked();
        } catch (DukeException e) {
            assertEquals("SUI, This task has already been marked as done!\n", e.getMessage());
        }
    }

    @Test
    public void deadline_setUnMarkAgain_exception() {
        try {
            Deadline deadline = new Deadline("CS2103T iP", "2022-12-03" , "09:00", TaskType.DEADLINE);
            deadline.setUnmarked();
            deadline.setUnmarked();
        } catch (DukeException e) {
            assertEquals("SUI, This task has already been marked as not done!\n", e.getMessage());
        }
    }
    @Test
    public void deadline_missingEndDate_exception() {
        try {
            String input = "deadline cs2103 iP";
            RonaldoSaysDo ronaldoSaysDo = new RonaldoSaysDo();
            ronaldoSaysDo.handleDeadlineTask(input);
        } catch (DukeException e) {
            assertEquals("SUI, Specify by date and time!", e.getMessage());
        }
    }
    @Test
    public void deadline_missingDescription_exception() {
        try {
            String input = "deadline /by 2023-10-09 09:00";
            RonaldoSaysDo ronaldoSaysDo = new RonaldoSaysDo();
            ronaldoSaysDo.handleDeadlineTask(input);
        } catch (DukeException e) {
            assertEquals("SUI, No description specified la dei!! "
                    +
                    "How to do work when no work is said!! Enter again!\n", e.getMessage());
        }
    }
}
