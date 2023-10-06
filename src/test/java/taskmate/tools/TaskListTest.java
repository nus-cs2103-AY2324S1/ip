package taskmate.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import taskmate.tools.tasks.Deadline;
import taskmate.tools.tasks.Event;
import taskmate.tools.tasks.Task;
import taskmate.tools.tasks.Todo;


public class TaskListTest {
    @Test
    public void addTask_onlyTodoTasks_success() {
        Task todoTask1 = new Todo("This is a todo task");
        Task todoTask2 = new Todo("This is a COMPLETED todo task");
        Task todoTask3 = new Todo("This is a todo task"); // replicate of todoTask1

        TaskList tasks = new TaskList();
        tasks.addTask(todoTask1);
        tasks.addTask(todoTask2, true);
        tasks.addTask(todoTask3, false);

        String tasksFormattedForWritingToDisk = tasks.formatAllTasksForSaving();
        String actualAnswer = "[T][ ] This is a todo task\n";
        actualAnswer += "[T][X] This is a COMPLETED todo task\n";
        actualAnswer += "[T][ ] This is a todo task\n";
        assertEquals(tasksFormattedForWritingToDisk, actualAnswer);
    }

    @Test
    public void addTask_onlyDeadlineTasks_success() {
        Task deadlineTask1 = new Deadline("This is a deadline task", "2024-02-29"); // Leap year
        Task deadlineTask2 = new Deadline("by", "2023-01-01");
        Task deadlineTask3 = new Deadline(".", "2100-01-02"); // many years ahead
        Task deadlineTask4 = new Deadline("This is a deadline task", "2024-02-29"); // copy of deadlineTask1

        TaskList tasks = new TaskList();
        tasks.addTask(deadlineTask1);
        tasks.addTask(deadlineTask2, true);
        tasks.addTask(deadlineTask3, false);
        tasks.addTask(deadlineTask4);

        String tasksFormattedForWritingToDisk = tasks.formatAllTasksForSaving();
        String actualAnswer = "[D][ ] This is a deadline task (by: 2024-02-29)\n";
        actualAnswer += "[D][X] by (by: 2023-01-01)\n";
        actualAnswer += "[D][ ] . (by: 2100-01-02)\n";
        actualAnswer += "[D][ ] This is a deadline task (by: 2024-02-29)\n";
        assertEquals(tasksFormattedForWritingToDisk, actualAnswer);
    }

    @Test
    public void addTask_onlyEventTasks_success() {
        Task eventTask1 = new Event("This is an event task", "2000-07-29", "2036-02-29"); // Leap year
        Task eventTask2 = new Event("This is a COMPLETED event task", "2023-01-01",
                "3023-12-31");
        Task eventTask3 = new Event("from", "1100-01-02", "2100-01-02");
        Task eventTask4 = new Event("to", "1100-01-02", "2100-01-02");

        TaskList tasks = new TaskList();
        tasks.addTask(eventTask1);
        tasks.addTask(eventTask2, true);
        tasks.addTask(eventTask3, false);
        tasks.addTask(eventTask4);

        String tasksFormattedForWritingToDisk = tasks.formatAllTasksForSaving();
        String actualAnswer = "[E][ ] This is an event task (from: 2000-07-29 to: 2036-02-29)\n";
        actualAnswer += "[E][X] This is a COMPLETED event task (from: 2023-01-01 to: 3023-12-31)\n";
        actualAnswer += "[E][ ] from (from: 1100-01-02 to: 2100-01-02)\n";
        actualAnswer += "[E][ ] to (from: 1100-01-02 to: 2100-01-02)\n";
        assertEquals(tasksFormattedForWritingToDisk, actualAnswer);
    }

    @Test
    public void addTask_addAllTaskTypes_success() {
        Task todoTask = new Todo("This is a todo task");
        Task deadlineTask = new Deadline("This is a COMPLETED deadline task", "2023-01-01");
        Task eventTask = new Event("This is an event task", "2024-12-02", "1987-01-02");

        TaskList tasks = new TaskList();
        tasks.addTask(todoTask);
        tasks.addTask(deadlineTask, true);
        tasks.addTask(eventTask, false);

        String tasksFormattedForWritingToDisk = tasks.formatAllTasksForSaving();
        String actualAnswer = "[T][ ] This is a todo task\n";
        actualAnswer += "[D][X] This is a COMPLETED deadline task (by: 2023-01-01)\n";
        actualAnswer += "[E][ ] This is an event task (from: 2024-12-02 to: 1987-01-02)\n";
        assertEquals(tasksFormattedForWritingToDisk, actualAnswer);
    }

    @Test
    public void addTask_noTasksAdded_success() {
        TaskList tasks = new TaskList();

        String tasksFormattedForWritingToDisk = tasks.formatAllTasksForSaving();
        String actualAnswer = "";
        assertEquals(tasksFormattedForWritingToDisk, actualAnswer);
    }
}
