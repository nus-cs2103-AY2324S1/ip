package cheems;

import cheems.functionalities.Storage;
import cheems.functionalities.TaskList;
import cheems.tasks.Deadline;
import cheems.tasks.Event;
import cheems.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class TaskListTest {
    Storage storageMock = mock(Storage.class);
    @Test
    void addTaskToDatabaseTest_todo_success() {
        TaskList tasklist = new TaskList(storageMock);
        String[] params = {"TODO", "Sing a song"};
        String actualOutput = tasklist.addTaskToDatabase(params);

        Todo todo = new Todo("Sing a song");
        String expectedOutput = "I have added this task for you!\n" + todo.toString();
        expectedOutput += String.format("\nNow you have %d task(s) in your list!", tasklist.getTotal());

        assertEquals(1, tasklist.getTotal());
        assertEquals(expectedOutput, actualOutput);
        verify(storageMock).saveNewTask(todo.toStorage());
    }

    @Test
    void addTaskToDatabaseTest_deadline_success() {
        TaskList tasklist = new TaskList(storageMock);
        String[] params = {"DEADLINE", "do hw", "2019-12-14"};
        String actualOutput = tasklist.addTaskToDatabase(params);

        Deadline ddl = new Deadline("do hw", "2019-12-14");
        String expectedOutput = "I have added this task for you!\n" + ddl.toString();
        expectedOutput += String.format("\nNow you have %d task(s) in your list!", tasklist.getTotal());

        assertEquals(1, tasklist.getTotal());
        assertEquals(expectedOutput, actualOutput);
        verify(storageMock).saveNewTask(ddl.toStorage());
    }

    @Test
    void addTaskToDatabaseTest_event_success() {
        TaskList tasklist = new TaskList(storageMock);
        String[] params = {"EVENT", "conference", "2022-12-22", "2022-12-24"};
        String expectedOutput = tasklist.addTaskToDatabase(params);

        Event event = new Event("conference", "2022-12-22", "2022-12-24");
        String actualOutput = "I have added this task for you!\n" + event.toString();
        actualOutput += String.format("\nNow you have %d task(s) in your list!", tasklist.getTotal());

        assertEquals(1, tasklist.getTotal());
        assertEquals(expectedOutput, actualOutput);
        verify(storageMock).saveNewTask(event.toStorage());
    }

    @Test
    void addTaskToDatabaseTest_taskWrongFormat_exceptionThrown() {
        TaskList tasklist = new TaskList(storageMock);
        String[] params1 = {"EVENT", "conference", "2022-12", "2022-12-24"};
        String[] params2 = {"DEADLINE", "quizzes", "abc"};

        assertThrows(DateTimeParseException.class, () -> {
            tasklist.addTaskToDatabase(params1);
        });
        assertThrows(DateTimeParseException.class, () -> {
            tasklist.addTaskToDatabase(params2);
        });
        assertEquals(0, tasklist.getTotal());
    }
}