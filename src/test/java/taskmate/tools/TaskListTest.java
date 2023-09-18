package taskmate.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import taskmate.tools.tasks.Deadline;
import taskmate.tools.tasks.Event;
import taskmate.tools.tasks.Task;
import taskmate.tools.tasks.Todo;


public class TaskListTest {
    @Test
    public void addTaskTest() { // todo: Change to stubs
        Task todoTask = new Todo("This is a todo task");
        Task deadlineTask = new Deadline("This is a COMPLETED deadline task", "2023-01-01");
        Task eventTask = new Event("This is an event task", "2024-12-02", "1987-01-02");

        TaskList tasks = new TaskList();
        tasks.addTask(todoTask);
        tasks.addTask(deadlineTask, true);
        tasks.addTask(eventTask, false);

        String tasksFormattedForWritingToDisk = tasks.formatAllTasksForSaving();
        String actualAnswer = "[T][ ] This is a todo task\n"
                + "[D][X] This is a COMPLETED deadline task (by: 2023-01-01)\n"
                + "[E][ ] This is an event task (from: 2024-12-02 to: 1987-01-02)\n";
        assertEquals(tasksFormattedForWritingToDisk, actualAnswer);
    }

}
