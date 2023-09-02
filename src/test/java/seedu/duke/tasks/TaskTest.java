package seedu.duke.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Exceptions.LemonException;
import seedu.duke.Tasks.Deadline;
import seedu.duke.Tasks.Event;
import seedu.duke.Tasks.Todo;
import seedu.duke.datafile.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    private TaskList tasks;

    @BeforeEach
    public void initialize() {
        this.tasks = new TaskList();
        tasks.addTasks(new Todo("Jump 20 times"));
    }

    @Test
    public void TodoTask() {
        try {
            assertEquals("[T][ ] Jump 20 times", this.tasks.getTask(0).toString());
        } catch (LemonException e) {
            fail();
        }
    }

    @Test
    public void DeadlineTask() {
        try {
            tasks.addTasks(new Deadline("Quiz", "2022-10-15"));
            assertEquals("[D][ ] Quiz (by: Oct 15 2022)", this.tasks.getTask(1).toString());
        } catch (LemonException e) {
            fail();
        }
    }
    @Test
    public void EventTask() {
        try {
            tasks.addTasks(new Event("Welcome Tea", "2022-10-15", "2022-10-21"));
            assertEquals("[E][ ] Welcome Tea (from: 2022-10-15 to: 2022-10-21)", this.tasks.getTask(1).toString());
        } catch (LemonException e) {
            fail();
        }
    }


}
