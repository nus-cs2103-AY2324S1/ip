package chad.util;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import chad.exception.DeleteException;
import chad.exception.MarkException;

public class TaskListTest {
    @Test
    public void removeTest_invalidIndex() {
        TaskList taskList = new TaskList();
        try {
            taskList.remove(1);
            fail();
        } catch (DeleteException e) {
            Assertions.assertEquals(e.getMessage(), new DeleteException().getMessage());
        }
    }

    @Test
    public void markTest_invalidIndex() {
        TaskList taskList = new TaskList();
        try {
            taskList.mark(1);
            fail();
        } catch (MarkException e) {
            Assertions.assertEquals(e.getMessage(), new MarkException().getMessage());
        }
    }
}
