package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TaskListTest {

    @Test
    public void markTask_withNegativeIndex() {
        try {
            TaskList taskList = new TaskList();
            taskList.handleTodoTask("todo borrow", "user");
        } catch (DukeException | IOException e) {
            assertEquals("Enter mark command with positive index lesser than 2", e.getMessage());
        }
    }

    @Test
    public void deadlineTask_noBy_exception() {
        try {
            TaskList taskList = new TaskList();
            taskList.handleDeadlineTask("deadline borrow", "user");
        } catch (DukeException | IOException e) {
            assertEquals("Specify by date and time!", e.getMessage());
        }
    }

    @Test
    public void eventTask_noDescription_exception() {
        try {
            TaskList taskList = new TaskList();
            taskList.handleEventTask("event /from 2022-10-12 20:00 /to 2022-11-10 00:00", "user");
        } catch (DukeException | IOException e) {
            assertEquals("No description specified la dei!! How to do work when no work is said!! "
                    + "Enter again!\n", e.getMessage());
        }
    }

}
