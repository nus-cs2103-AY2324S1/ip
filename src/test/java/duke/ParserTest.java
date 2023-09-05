package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

public class ParserTest {
    private Parser parser = new Parser();
    private TaskList taskList = new TaskList();

    @Test
    public void testAddToDo() {
        try {
            parser.addToDo("todo watch videos", taskList);
            assertEquals(1, taskList.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testAddDeadline() {
        try {
            parser.addDeadline("deadline assignment /by 12/03/2040 1345", taskList);
            assertEquals(1, taskList.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testAddEvent() {
        try {
            parser.addEvent("event meeting /from 6th June /to 9th June", taskList);
            assertEquals(1, taskList.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testRemoveTask() {
        try {
            parser.addToDo("todo play!", taskList);

            parser.deleteTask("delete 1", taskList);
            assertEquals(0, taskList.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testFindMatchingTaskList() throws Exception {
        taskList.addTask(new ToDo("return book"));
        taskList.addTask(new Deadline("book review", "07/12/2019 1920"));
        taskList.addTask(new Event("meeting", "Mon 6pm", "10pm"));

        TaskList matchingTaskList = parser.findMatchingTaskList("find book", taskList);

        assertEquals(2, matchingTaskList.getSize());
        assertEquals("[T][ ] return book", matchingTaskList.getTask(0).toString());
        assertEquals("[D][ ] book review (by: 07 Dec 2019 7:20 PM)",
                matchingTaskList.getTask(1).toString());
    }

    @Test
    public void testFindNoMatchingTaskList() throws Exception {
        taskList.addTask(new ToDo("return book"));
        taskList.addTask(new Deadline("book review", "07/12/2019 1920"));
        taskList.addTask(new Event("meeting", "Mon 6pm", "10pm"));

        TaskList matchingTaskList = parser.findMatchingTaskList("find hello", taskList);

        assertEquals(0, matchingTaskList.getSize());
    }

    @Test
    public void testFindMatchingTaskListEmptyKeyword() throws Exception {
        taskList.addTask(new ToDo("return book"));
        taskList.addTask(new Deadline("book review", "07/12/2019 1920"));
        taskList.addTask(new Event("meeting", "Mon 6pm", "10pm"));

        assertThrows(InvalidDescriptionException.class, () -> {
            parser.findMatchingTaskList("find ", taskList);
        });
    }

}
