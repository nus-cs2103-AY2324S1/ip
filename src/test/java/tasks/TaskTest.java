package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {

    @Test
    public void testSetIsDoneMethod() {
        ToDo todo = new ToDo("marry a hot babe");
        todo.setIsDone();
        assertTrue(todo.isDone);
    }

    @Test
    public void testSetIsNotDoneMethod() {
        Event event = new Event("wedding for hot babe",
                true, "1200", "1600");
        event.setIsNotDone();
        assertFalse(event.isDone);
    }

    @Test
    public void testGetDoneStatus() {
        ToDo todo = new ToDo("book venue");
        assertFalse(todo.getDoneStatus());
    }

    @Test
    public void testGetDescriptionMethod() {
        Deadline deadline = new Deadline("send out invitations",
                "2023/12/30 23:59");
        assertEquals("send out invitations", deadline.getDescription());
    }

    @Test
    public void testGetStatusMethod() {
        ToDo todo1 = new ToDo("hire photographers", true);
        ToDo todo2 = new ToDo("hire musicians", false);

        assertEquals("[X]", todo1.getStatus());
        assertEquals("[ ]", todo2.getStatus());
    }

}
