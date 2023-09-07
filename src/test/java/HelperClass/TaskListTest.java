package HelperClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {
    @Test
    public void testDisplayEmptyList() {
        TaskList taskList = new TaskList();
        assertEquals("No items in the list yet", taskList.displayList());
    }

    @Test
    public void testInvalidIndexDetection() {
        TaskList taskList = new TaskList();
        assertEquals("Invalid index.", taskList.markTask(1));
    }
}
