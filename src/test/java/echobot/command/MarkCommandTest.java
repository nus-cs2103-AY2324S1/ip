package echobot.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import echobot.storage.Storage;
import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.Task;
import echobot.task.Todo;

public class MarkCommandTest {
    @Test
    public void testMarkCommand() {
        ArrayList<Task> tasks = new ArrayList<>();

        Task task1 = new Todo("Buy groceries");
        LocalDate dueDate = LocalDate.now();
        Task task2 = new Deadline("Finish homework", dueDate);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusHours(10);
        Task task3 = new Event("Meeting", start, end);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        MarkCommand markCommand = new MarkCommand(2);
        String response = markCommand.doCommand(tasks, new Storage("./data/dummy.txt", null), null);

        assertTrue(task2.isTaskDone());

        // Verify the response message
        String expectedResponse = "Nice! I've marked this task as done:\n";
        expectedResponse += "[X] Finish homework ";
        expectedResponse += "(by: "
                + dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void anotherTestMarkCommand() {
        ArrayList<Task> tasks = new ArrayList<>();

        Task task1 = new Todo("Buy groceries");
        LocalDate dueDate = LocalDate.now();
        Task task2 = new Deadline("Finish homework", dueDate);
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusHours(10);
        Task task3 = new Event("Meeting", start, end);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        MarkCommand markCommand = new MarkCommand(1);
        String response = markCommand.doCommand(tasks,
                new Storage("./data/dummy.txt", null), null);

        assertFalse(task3.isTaskDone());

        // Verify the response message
        String expectedResponse = "Nice! I've marked this task as done:\n";
        expectedResponse += "[X] Buy groceries";

        Assertions.assertEquals(expectedResponse, response);
    }
}
