package chat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chat.exceptions.ChatException;

public class TaskListTest {

    @Test
    public void test1() {
        TaskList tasklist = new TaskList();
        tasklist.add("hi", true);
        try {
            tasklist.delete(0);
        } catch (ChatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(tasklist.getSize(), new TaskList().getSize());
    }

    @Test
    public void test2() {
        TaskList tasklist = new TaskList();
        tasklist.add("hi", true);
        assertEquals(tasklist.toString(), "1.[T][X] hi\nYou have 1 task(s) in the list.");
    }
}
