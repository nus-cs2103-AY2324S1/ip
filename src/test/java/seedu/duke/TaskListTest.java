package seedu.duke;
import seedu.duke.Task;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {

    @Test
    public void test_loading() throws Exception {
        // normal division results in an integer answer 2
        TaskList tasks = new TaskList("");
        tasks.add(new Deadline("fish","2000-12-20"));
        assertEquals("[D][ ] fish (by: 2000-12-20)", tasks.get(0).toString());
    }

}