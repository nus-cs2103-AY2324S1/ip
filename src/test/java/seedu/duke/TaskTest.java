package seedu.duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskTest {

    @Test
    public void todo_mark_test() throws Exception {
        // normal division results in an integer answer 2
        Task todo = new Todo("homework");
        todo.markAsDone();
        assertEquals("[T][X] homework", todo.toString());
    }

}