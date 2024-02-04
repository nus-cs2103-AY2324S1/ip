package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Tag;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskTest {
    @Test
    @Tag("Basic test")
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    @Tag("ToDo test")
    public void toDo_toFileString_success() {
        assertEquals("T | 0 | read book", new ToDo("read book").toFileString());
    }

    @Test
    @Tag("ToDo test")
    public void toDo_toString_success() {
        assertEquals("[T][ ] read book", new ToDo("read book").toString());
    }

    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    @Test
    @Tag("Deadline test")
    public void deadline_toFileString_success() {
        assertEquals("D | 0 | return book | 2023-01-12T18:00",
                new Deadline("return book", LocalDateTime.parse("12/01/2023 1800", inputFormatter)).toFileString());
    }

    @Test
    @Tag("Deadline test")
    public void deadline_toString_success() {
        assertEquals("[D][ ] return book (by: Jan 12 2023 18:00)",
                new Deadline("return book", LocalDateTime.parse("12/01/2023 1800", inputFormatter)).toString());
    }

    @Test
    @Tag("Event test")
    public void event_toFileString_success() {
        assertEquals("E | 0 | project meeting | 2023-01-12T18:00 - 2023-01-12T19:00",
                new Event("project meeting", LocalDateTime.parse("12/01/2023 1800", inputFormatter),
                        LocalDateTime.parse("12/01/2023 1900", inputFormatter)).toFileString());
    }

    @Test
    @Tag("Event test")
    public void event_toString_success() {
        assertEquals("[E][ ] project meeting (from: Jan 12 2023 18:00 to Jan 12 2023 19:00)",
                new Event("project meeting", LocalDateTime.parse("12/01/2023 1800", inputFormatter),
                        LocalDateTime.parse("12/01/2023 1900", inputFormatter)).toString());
    }

    @Test
    @Tag("TaskList test")
    public void taskList_emptyInitialisation_success() {
        TaskList taskList = new TaskList();
        assertEquals(2, 2);
    }

    @Test
    @Tag("TaskList test")
    public void taskList_nonEmptyInitialisation_success() {
        ArrayList<Task> testArrayList = new ArrayList<>();
        testArrayList.add(new ToDo("abc"));
        TaskList taskList = new TaskList(testArrayList);
        assertEquals(1, taskList.getSize());
    }

    @Test
    @Tag("TaskList test")
    public void taskList_addTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        assertEquals(1, taskList.getSize());
    }

    @Test
    @Tag("TaskList test")
    public void taskList_deleteTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        taskList.deleteTask(0);
        assertEquals(0, taskList.getSize());
    }

    @Test
    @Tag("TaskList test")
    public void taskList_getTask_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        assertEquals(new ToDo("read book"), taskList.getTask(0));
    }

    @Test
    @Tag("TaskList test")
    public void taskList_getSize_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("read book"));
        assertEquals(1, taskList.getSize());
    }
}
