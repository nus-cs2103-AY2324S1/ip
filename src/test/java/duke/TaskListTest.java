package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void nullArrayTest() {
        TaskList t = new TaskList();
         assertEquals(null, t.retrieveArray());
    }
    @Test
    public void arrayCorrectManipulation() {
        TaskList t = new TaskList();
        t.addTodo("read book");
        t.addDeadline("return book", "sunday");
        t.addEvent("play games", "monday - tuesday");
        assertEquals(3, t.retrieveArray().size());
        String dummyInput = "delete 2";
        String[] arr = dummyInput.split(" ");
        try {
            t.delete(arr);
        } catch (WrongInput e) {
            System.out.println(e.getMessage());
        } finally {
            assertEquals(2, t.retrieveArray().size());
        }
    }
}
