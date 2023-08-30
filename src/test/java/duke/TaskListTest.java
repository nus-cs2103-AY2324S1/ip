package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddAndRetrieveTasks() {
        TaskList taskList = new TaskList();

        // Create tasks
        LocalDate deadline1 = LocalDate.of(2023, 8, 29);
        Task task1 = new Deadline("Task 1", deadline1);

        LocalDate deadline2 = LocalDate.of(2023, 8, 30);
        Task task2 = new Deadline("Task 2", deadline2);

        // Add tasks to the task list
        taskList.add(task1);
        taskList.add(task2);

        // Retrieve tasks from the task list
        Task retrievedTask1 = taskList.get(0);
        Task retrievedTask2 = taskList.get(1);

        // Check if the retrieved tasks match the original tasks
        assertEquals(task1, retrievedTask1);
        assertEquals(task2, retrievedTask2);
    }
}
