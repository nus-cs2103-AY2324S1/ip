package cheems;

import cheems.tasks.Deadline;
import cheems.tasks.Event;
import cheems.tasks.Task;
import cheems.tasks.Todo;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import static org.assertj.core.api.Assertions.assertThat;;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class TasklistTest {
    UI uiMock = mock(UI.class);
    Storage storageMock = mock(Storage.class);
    @Test
    void addTodoTaskToDatabaseTest() {
        Tasklist tasklist = new Tasklist(storageMock, uiMock);
        String[] params = {"TODO","Sing a song"};
        tasklist.addTaskToDatabase(params);

        Todo todo = new Todo("Sing a song");
        String resp = "I have added this task for you!\n" + todo.toString();
        resp += String.format("\nNow you have %d task(s) in your list!", tasklist.getTotal());

        assertEquals(1, tasklist.getTotal());
        assertTrue(new ReflectionEquals(todo).matches(tasklist.getTaskAt(0)));
        verify(storageMock).saveNewTask(todo.toStorage());
        verify(uiMock).printWithFormat(resp);
    }

    @Test
    void addDeadlineTaskToDatabaseTest() {
        Tasklist tasklist = new Tasklist(storageMock, uiMock);
        String[] params = {"DEADLINE", "do hw", "2019-12-14"};
        tasklist.addTaskToDatabase(params);

        Deadline ddl = new Deadline("do hw", "2019-12-14");
        String resp = "I have added this task for you!\n" + ddl.toString();
        resp += String.format("\nNow you have %d task(s) in your list!", tasklist.getTotal());

        assertEquals(1, tasklist.getTotal());
        verify(storageMock).saveNewTask(ddl.toStorage());
        verify(uiMock).printWithFormat(resp);
    }

    @Test
    void addEventTaskToDatabaseTest() {
        Tasklist tasklist = new Tasklist(storageMock, uiMock);
        String[] params = {"EVENT", "conference", "2022-12-22", "2022-12-24"};
        tasklist.addTaskToDatabase(params);

        Event event = new Event("conference", "2022-12-22", "2022-12-24");
        String resp = "I have added this task for you!\n" + event.toString();
        resp += String.format("\nNow you have %d task(s) in your list!", tasklist.getTotal());

        assertEquals(1, tasklist.getTotal());
        verify(storageMock).saveNewTask(event.toStorage());
        verify(uiMock).printWithFormat(resp);
    }
}