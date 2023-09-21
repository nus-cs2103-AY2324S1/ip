package duke.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.data.exception.DukeException;
import duke.data.exception.InvalidTaskIndexException;

public class TaskListTest {

    @Test
    public void remove_invalidIndex_throwsInvalidTaskIndexException() {
        TaskList taskList = new TaskList(
                new Todo("todo"),
                new Deadline("deadline", LocalDate.now()),
                new Event("event", LocalDate.now(), LocalDate.now())
        );
        assertThrows(InvalidTaskIndexException.class, () -> taskList.remove(-1));
        assertThrows(InvalidTaskIndexException.class, () -> taskList.remove(4));
    }

    @Test
    public void remove_validIndexIndex_taskNotInList() {
        Task toRemove = new Todo("toRemove");
        TaskList taskList = new TaskList(
                new Todo("todo"),
                new Deadline("deadline", LocalDate.now()),
                new Event("event", LocalDate.now(), LocalDate.now()),
                toRemove
        );

        try {
            assertEquals(toRemove, taskList.remove(4));;
        } catch (DukeException e) {
            fail(e.getMessage());
        }

        for (Task task : taskList.getAllTasks()) {
            assertFalse(task.equals(toRemove));
        }
    }

}
