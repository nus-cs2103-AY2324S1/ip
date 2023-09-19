package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskListTest {
    @Test
    public void addEvent_success() throws DukeException {
        String description = "Test Event /from 2023-10-20 /to 2023-10-21";
        TaskList lst = new TaskList(new ArrayList<Task>());
        lst.addTask("event", description);
        LocalDate testStart = LocalDate.parse("2023-10-20");
        LocalDate testEnd = LocalDate.parse("2023-10-21");
        Event expectedEvent = new Event("Test Event ", testStart, testEnd);
        ArrayList<Task> expectedList = new ArrayList<>();
        expectedList.add(expectedEvent);
        assertEquals(expectedList, lst.getList());
    }

    @Test
    public void addEvent_descriptionEmpty_exceptionThrown() {
        try {
            String description = "";
            TaskList lst = new TaskList(new ArrayList<Task>());
            lst.addTask("event", description);
            fail();
        } catch (DukeException e) {
            assertEquals("The description of an Event cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void addToDo_success() throws DukeException {
        String description = "New TODO";
        TaskList lst = new TaskList(new ArrayList<Task>());
        lst.addTask("todo", description);
        ArrayList<Task> expectedList = new ArrayList<>();
        ToDo testToDo = new ToDo(description);
        expectedList.add(testToDo);
        assertEquals(expectedList, lst.getList());
    }

    @Test
    public void addTodo_descriptionEmpty_exceptionThrown() {
        try {
            String description = "";
            TaskList lst = new TaskList(new ArrayList<Task>());
            lst.addTask("todo", description);
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a todo cannot be empty.",
                    e.getMessage());
        }

    }
}
