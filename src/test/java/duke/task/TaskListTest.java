package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskListTest {
    @Test
    public void markDoneTest() {
        TaskList list = new TaskList();
        try {
            list.addTodoToList("test");
            list.markDone("1");
        } catch (DukeException e) {
            // do nothing
        }
        assertEquals(list.toString(), "1. [T][X] test\n", "'markDone()' should mark task as done");
    }

    @Test
    public void getDateTasksTest() {
        TaskList list = new TaskList();
        try {
            list.addDeadlineToList("test /by 2020-10-10");
            list.addDeadlineToList("test /by 2020-10-10");
            assertEquals(list.getTasksOnDate("2020-10-10"),
                    "1. [D][ ] test (by: Oct 10 2020)\n2. [D][ ] test (by: Oct 10 2020)\n",
                    "'getTasksOnDate()' should return tasks on specified date");
        } catch (DukeException e) {
            // do nothing
        }
    }
}
