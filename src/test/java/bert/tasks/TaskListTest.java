package bert.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void toString_todoDeadlineEvent_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test todo"));
        tasks.add(new Deadline("test deadline", LocalDate.of(2001, 1, 1)));
        tasks.add(new Event("test event",
                LocalDate.of(2001, 1, 1),
                LocalDate.of(2001, 1, 2)
        ));
        assertEquals("1.[T][ ] test todo" + System.lineSeparator()
                + "2.[D][ ] test deadline (by: Jan 01 2001)" + System.lineSeparator()
                + "3.[E][ ] test event (from: Jan 01 2001 to: Jan 02 2001)" + System.lineSeparator(),
                tasks.toString());
    }

    @Test
    public void toString_emptyTaskList_success() {
        TaskList tasks = new TaskList();
        assertEquals("", tasks.toString());
    }

    @Test
    public void mark_undoneTask_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test todo"));
        tasks.add(new Deadline("test deadline", LocalDate.of(2001, 1, 1)));
        tasks.add(new Event("test event",
                LocalDate.of(2001, 1, 1),
                LocalDate.of(2001, 1, 2)
        ));

        Task todo = tasks.mark(0);
        assertEquals("[T][X] test todo", todo.toString());

        Task deadline = tasks.mark(1);
        assertEquals("[D][X] test deadline (by: Jan 01 2001)", deadline.toString());

        Task event = tasks.mark(2);
        assertEquals("[E][X] test event (from: Jan 01 2001 to: Jan 02 2001)", event.toString());
    }

    @Test
    public void mark_isDoneTask_success() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test todo"));
        tasks.mark(0);
        Task t = assertDoesNotThrow(
                () -> {
                    return tasks.mark(0);
                }
        );
        assertEquals("[T][X] test todo", t.toString());
    }

    @Test
    public void mark_outOfBoundsIndex_exceptionThrown() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> {
                    TaskList tasks = new TaskList();
                    tasks.add(new ToDo("test todo"));
                    tasks.mark(1);
                });
    }
}
