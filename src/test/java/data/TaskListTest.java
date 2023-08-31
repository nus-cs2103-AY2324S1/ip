package data;

import data.exception.DukeException;
import data.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private static class TaskStub extends Task {
        public String desc;
        public boolean done;
        public TaskStub(String description) {
            super(description);
            this.desc = description;
            this.done = false;
        }

        @Override
        public void mark() {
            super.mark();
            done = true;
        }

        @Override
        public void unmark() {
            super.unmark();
            done = false;
        }
    }
    @Test
    public void testDelete() {
        TaskList tasks = new TaskList(null);
        tasks.add(new TaskStub("read a book"));
        try {
            Task deletedTask = tasks.delete(0);
            if (deletedTask instanceof TaskStub) {
                TaskStub stub = (TaskStub) deletedTask;
                assertEquals("read a book", stub.desc);
            } else {
                fail("TaskStub class should be used");
            }
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testDeleteOutOfBounds() {
        TaskList tasks = new TaskList(null);
        tasks.add(new TaskStub("read a book"));
        tasks.add(new TaskStub("read 300 book"));
        assertThrows(
            DukeException.class,
            () -> tasks.delete(2)
        );
        assertThrows(
            DukeException.class,
            () -> tasks.delete(100)
        );
        assertThrows(
            DukeException.class,
            () -> tasks.delete(-1)
        );
        assertEquals(2, tasks.getSize());
        assertFalse(tasks.isEmpty());
    }
    @Test
    public void testMark() {
        TaskList tasks = new TaskList(null);
        tasks.add(new TaskStub("read a book"));
        try {
            tasks.mark(0, true);
            Task markedTask = tasks.delete(0);
            if (markedTask instanceof TaskStub) {
                TaskStub stub = (TaskStub) markedTask;
                assertTrue(stub.done);
                assertEquals("read a book", stub.desc);
            } else {
                fail("TaskStub class should be used");
            }
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testUnmark() {
        TaskList tasks = new TaskList(null);
        tasks.add(new TaskStub("read a book"));
        try {
            tasks.mark(0, true);
            tasks.mark(0, false);
            Task markedTask = tasks.delete(0);
            if (markedTask instanceof TaskStub) {
                TaskStub stub = (TaskStub) markedTask;
                assertEquals(false, stub.done);
                assertEquals("read a book", stub.desc);
            }
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testMarkOutOfBounds() {
        TaskList tasks = new TaskList(null);
        tasks.add(new TaskStub("read a book"));
        tasks.add(new TaskStub("read 300 book"));
        assertThrows(
            DukeException.class,
            () -> tasks.mark(2, true)
        );
        assertThrows(
            DukeException.class,
            () -> tasks.mark(100, false)
        );
        assertThrows(
            DukeException.class,
            () -> tasks.mark(-1, true)
        );
    }
}
