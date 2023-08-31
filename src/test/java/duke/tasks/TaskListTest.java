package duke.tasks;

import duke.exceptions.DukeException;
import duke.utils.Commands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



public class TaskListTest {

    TaskList tasks;

    @BeforeEach
    void setUp() {
        List<String> stringTasks = new ArrayList<>();
        stringTasks.add("/UC todo 1");
        stringTasks.add("/UC deadline 2 /by 2023-12-12");
        stringTasks.add("/UC event 3 /from 2023-12-12 /to 2023-12-13");

        tasks = new TaskList(stringTasks);
    }

    @Test
    public void createTask_success() {
        try {
            assertEquals(new ToDo("4", 0),
                    TaskList.createTask("todo 4", Commands.TODO, 0));
            assertEquals(new Deadline("5", 0, LocalDate.parse("2024-01-01")),
                    TaskList.createTask("deadline 5 /by 2024-01-01", Commands.DEADLINE, 0));
            assertEquals(
                    new Event("6", 0, LocalDate.parse("2024-01-01"),
                            LocalDate.parse("2024-01-02")),
                    TaskList.createTask("event 6 /from 2024-01-01 /to 2024-01-02",
                            Commands.EVENT, 0));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void createTask_invalidDateFormat_exceptionThrown() {
        try {
            assertEquals(
                    new Event("6", 0, LocalDate.parse("2024-01-01"),
                            LocalDate.parse("2024-01-02")),
                    TaskList.createTask("event 6 /from new year /to 2024-01-02",
                            Commands.EVENT, 0));
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text 'new year' could not be parsed at index 0", e.getMessage());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void createTask_missingTitle_exceptionThrown() {
        try {
            assertEquals(
                    new Event("6", 0, LocalDate.parse("2024-01-01"),
                            LocalDate.parse("2024-01-02")),
                    TaskList.createTask("event/from 2024-01-01 /to 2024-01-02",
                            Commands.EVENT, 0));
            fail();
        } catch (DukeException e) {
            assertEquals("Title of the task is missing! Please give your task a name :)", e.getMessage());
        } catch (DateTimeParseException e) {
            fail();
        }
    }
    @Test
    public void deleteTask_success() {
        try {
            assertEquals("Deadline -> [ ] 2 By: Dec 12 2023 has been deleted!",
                    tasks.deleteTask("delete 2"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteTask_exceptionThrown() {
        try {
            assertEquals("fail", tasks.deleteTask("delete 5"));
            fail();
        } catch (DukeException e) {
            assertEquals("Task cannot be found :(", e.getMessage());
        }
    }
}