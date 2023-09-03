package cheems;

import cheems.functionalities.Storage;
import cheems.functionalities.Tasklist;
import cheems.functionalities.textUi;
import cheems.tasks.Deadline;
import cheems.tasks.Event;
import cheems.tasks.Todo;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import static org.assertj.core.api.Assertions.assertThat;;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class TasklistTest {
    Storage storageMock = mock(Storage.class);
    @Test
    void addTaskToDatabaseTest_todo_success() {
        Tasklist tasklist = new Tasklist(storageMock);
        String[] params = {"TODO","Sing a song"};
        String actualOutput = tasklist.addTaskToDatabase(params);

        Todo todo = new Todo("Sing a song");
        String expectedOutput = "I have added this task for you!\n" + todo.toString();
        expectedOutput += String.format("\nNow you have %d task(s) in your list!", tasklist.getTotal());

        assertTrue(new ReflectionEquals(todo).matches(tasklist.getTaskAt(0)));
        assertEquals(1, tasklist.getTotal());
        assertEquals(expectedOutput, actualOutput);
        verify(storageMock).saveNewTask(todo.toStorage());
    }

    @Test
    void addTaskToDatabaseTest_deadline_success() {
        Tasklist tasklist = new Tasklist(storageMock);
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
        Tasklist tasklist = new Tasklist(storageMock);
        String[] params = {"EVENT", "conference", "2022-12-22", "2022-12-24"};
        String expectedOutput = tasklist.addTaskToDatabase(params);

        Event event = new Event("conference", "2022-12-22", "2022-12-24");
        String actualOutput = "I have added this task for you!\n" + event.toString();
        actualOutput += String.format("\nNow you have %d task(s) in your list!", tasklist.getTotal());

        assertEquals(1, tasklist.getTotal());
        assertEquals(expectedOutput, actualOutput);
        verify(storageMock).saveNewTask(event.toStorage());
    }
}