package alcazar;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    @Test
    void getTasksTest() {
        ArrayList<Task> al = new ArrayList<>();
        al.add(new ToDo("read book"));
        String s = "1. [T][ ] read book\n";
        assertEquals(s, new TaskList(al).getTasks());
    }

    @Test
    void elementAtTest() {
        ArrayList<Task> al = new ArrayList<>();
        al.add(new ToDo("read book"));
        assertEquals("[T][ ] read book", new TaskList(al).elementAt(0).toString());
    }
}
