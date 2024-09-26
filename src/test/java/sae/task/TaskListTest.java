package sae.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    private TaskList taskList;

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void checkList() {
        taskList = new TaskList();
        taskList.addToDoTask("sleep");
        LocalDateTime dateTime = LocalDateTime.of(2003, 01, 07, 15, 0);
        taskList.addDeadlineTask("sleep", dateTime);
        taskList.addEventTask("sleep", "6am", "12pm");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void listTasksTest() {
        String expected =  "1.[T][ ] sleep\n"
                + "2.[D][ ] sleep (by: 7 January 2003 3pm)\n"
                + "3.[E][ ] sleep (from: 6am to: 12pm)\n";

        taskList.listTasks();
        assertEquals(expected.trim(), outContent.toString().trim());

    }

    @Test
    public void deleteTaskTest() {
        String expected =  "Noted. I've removed this task:\n" + "  [T][ ] sleep\n"
                + "Now you have 2 tasks in the list.";
        taskList.deleteTask(0);
        assertEquals(expected.trim(), outContent.toString().trim());

    }

}
