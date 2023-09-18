package taskmate.tools.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void nameTest() {
        String name1 = "This is test 1";
        Task t1 = new Todo(name1);
        assertEquals(name1, t1.getTaskName());

        String name2 = "";
        Task t2 = new Todo(name2);
        assertEquals(name2, t2.getTaskName());
    }
}
