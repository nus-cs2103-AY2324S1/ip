package parser;

import duke.DukeException.DukeException;
import duke.Task.Deadlines;
import duke.Task.Task;
import duke.TaskList.TaskList;
import duke.Task.ToDos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class parserTest {

    private TaskList tasks;
    @BeforeEach
    public void initialize() {
        this.tasks = new TaskList();
        this.tasks.addTask(new ToDos("IDK"));
    }
    @Test
    public void getNumberOfTask_checkTaskCount() {
        try {
            this.tasks.addTask(new Deadlines("back", "2023-10-10"));
            assertEquals(tasks.getNumberOfTask(), 2);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTask_noDescriptionTodo() {
        assertEquals("[T][ ] IDK" ,this.tasks.getTask(0).toString());
    }
}
