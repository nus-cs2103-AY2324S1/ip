package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTask_validInput_success() {
        TaskList taskList = new TaskList();
        String output = taskList.add(new Task("fakeTask") {
            @Override
            public String getAddMessage() {
                return "";
            }
        });
        assertEquals("\n[ ] fakeTask", output);
    }

    @Test
    public void deleteTask_onlyTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.add(new Task("fakeTask") {
            @Override
            public String getAddMessage() {
                return "";
            }
        });
        assertEquals("As you wish. This task has been removed:\n[ ] fakeTask" +
                "\nYou now have 0 tasks in your list.\nCongratulations!",
                taskList.delete(0));
    }

}
