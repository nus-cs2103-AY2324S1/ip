package echobot.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import echobot.storage.Storage;
import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.Task;
import echobot.task.Todo;
import echobot.ui.Ui;

public class MarkCommandTest {
    @Test
    public void testMarkCommand() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new Todo("Buy groceries");
        LocalDate by = LocalDate.now();
        Task task2 = new Deadline("Finish homework", by);
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(10);
        Task task3 = new Event("Meeting", from, to);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);


        MarkCommand markCommand = new MarkCommand(2);
        String response = markCommand.doCommand(tasks, new Ui(), new Storage("./data/dummy.txt"), null, null);

        assertTrue(task2.isTaskDone());

        // Verify the response message
        String expectedResponse = "Nice! I've marked this task as done:\n";
        expectedResponse += "[X] Finish homework (by: " + by.toString() + ")";
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void anotherTestMarkCommand() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task1 = new Todo("Buy groceries");
        LocalDate by = LocalDate.now();
        Task task2 = new Deadline("Finish homework", by);
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.now().plusHours(10);
        Task task3 = new Event("Meeting", from, to);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        MarkCommand markCommand = new MarkCommand(1);
        String response = markCommand.doCommand(tasks, new Ui(), new Storage("./data/dummy.txt"), null, null);

        assertFalse(task3.isTaskDone());

        // Verify the response message
        String expectedResponse = "Nice! I've marked this task as done:\n";
        expectedResponse += "[X] Buy groceries";
        Assertions.assertEquals(expectedResponse, response);
    }
}
