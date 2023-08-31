package aj;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void getTodoTask_correctInput_taskReturned() { // test that the Todo Task created have same behaviour
        String userInput = "todo return book";
        Scanner scanner = new Scanner(userInput);
        String command = scanner.next().toLowerCase();
        String remaining = scanner.nextLine();
        Parser parser = new Parser();
        Task testTask = parser.getTodoTask(remaining, false);
        assertTrue(testTask instanceof Todo); // test object returned is Todo task
        assertEquals(testTask.taskName, "return book"); // test taskName correct
        assertFalse(testTask.isMarked); // test isMarked set to false
    }

    @Test
    public void getDeadlineTask_correctInput_taskReturned() { // test that the deadline Task created have same behaviour
        String userInput = "deadline homework /by 2019-10-15";
        Scanner scanner = new Scanner(userInput);
        String command = scanner.next().toLowerCase();
        String remaining = scanner.nextLine();
        Parser parser = new Parser();
        Task testTask = parser.getDeadlineTask(remaining, false);
        assertTrue(testTask instanceof Deadline); // test object returned is Deadline task
        assertEquals(testTask.taskName, "homework"); // test taskName correct
        assertFalse(testTask.isMarked); // test isMarked set to false
        assertEquals(testTask.toString(), "[D][ ] homework (by: Oct 15 2019)");
    }

    @Test
    public void getEventTask_correctInput_taskReturned() { // test that the event Task created have same behaviour
        String userInput = "event project meeting /from Mon 2pm /to 4pm";
        Scanner scanner = new Scanner(userInput);
        String command = scanner.next().toLowerCase();
        String remaining = scanner.nextLine();
        Parser parser = new Parser();
        Task testTask = parser.getEventTask(remaining, false);
        assertTrue(testTask instanceof Event); // test object returned is Deadline task
        assertEquals(testTask.taskName, "project meeting"); // test taskName correct
        assertFalse(testTask.isMarked); // test isMarked set to false
        assertEquals(testTask.toString(), "[E][ ] project meeting (from: Mon 2pm to: 4pm)");
    }
}
