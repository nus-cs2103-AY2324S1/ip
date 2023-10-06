package seedu.duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.LemonException;
import seedu.duke.tasklist.TaskList;



public class TaskTest {

    private TaskList tasks;

    @BeforeEach
    public void initialize() {
        this.tasks = new TaskList();
        tasks.addTasks(new Todo("Jump 20 times"));
    }

    @Test
    public void toDoTask() {
        try {
            assertEquals("[T][ ] Jump 20 times", this.tasks.getTask(0).toString());
        } catch (LemonException e) {
            fail();
        }
    }

    @Test
    public void deadlineTask() {
        try {
            tasks.addTasks(new Deadline("Quiz", "2022-10-15"));
            assertEquals("[D][ ] Quiz (by: Oct 15 2022)", this.tasks.getTask(1).toString());
        } catch (LemonException e) {
            fail();
        }
    }
    @Test
    public void eventTask() {
        try {
            tasks.addTasks(new Event("Welcome Tea", "2022-10-15", "2022-10-21"));
            assertEquals("[E][ ] Welcome Tea (from: 2022-10-15 to: 2022-10-21)", this.tasks.getTask(1).toString());
        } catch (LemonException e) {
            fail();
        }
    }


}
