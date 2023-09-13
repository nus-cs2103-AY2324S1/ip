package prts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import prts.task.Task;

public class TaskListTest {

    @Test
    public void addTask_validInput_success() {
        TaskList taskList = new TaskList();
        String output = taskList.add(new Task("fakeTask") {
            @Override
            public String getAddMessage() {
                return "";
            }

            @Override
            public boolean contains(String searchTerm) {
                return false;
            }
        });
        assertEquals("\n[ ] fakeTask", output);
    }

    @Test
    public void deleteTask_onlyTask_success() throws OutOfRangeException {
        TaskList taskList = new TaskList();
        taskList.add(new Task("fakeTask") {
            @Override
            public String getAddMessage() {
                return "";
            }

            @Override
            public boolean contains(String searchTerm) {
                return false;
            }
        });
        assertEquals("As you wish. This task has been removed:\n[ ] fakeTask"
                        + "\nYou now have 0 tasks in your list.\nCongratulations!",
                taskList.delete(0));
    }

}
