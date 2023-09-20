package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void addTodoTest() {
        Duke duke = new Duke();
        duke.reset();
        String[] result = duke.getResponse("todo abc").split("\n");
        assertEquals(result[0], "Message received! Task added to the list:");
        assertEquals(result[1], "[T][ ] abc");
    }

    @Test
    public void addDeadlineTest() {
        Duke duke = new Duke();
        duke.reset();
        String[] result = duke.getResponse("deadline abc /by 2023-12-12").split("\n");
        assertEquals(result[0], "Message received! Task added to the list:");
        assertEquals(result[1], "[D][ ] abc (by: Dec 12 2023)");
    }

    @Test
    public void addEventTest() {
        Duke duke = new Duke();
        duke.reset();
        String[] result = duke.getResponse("event abc /from 1999-11-11 /to 1999-12-12").split("\n");
        assertEquals(result[0], "Message received! Task added to the list:");
        assertEquals(result[1], "[E][ ] abc (from: Nov 11 1999 to: Dec 12 1999)");
    }

    @Test
    public void deleteTaskTest() {
        Duke duke = new Duke();
        duke.reset();
        duke.getResponse("todo abc");
        String[] result = duke.getResponse("delete 1").split("\n");
        assertEquals(result[0], "Copy that! Task successfully erased:");
        assertEquals(result[1], "[T][ ] abc");
    }

    @Test
    public void markTaskTest() {
        Duke duke = new Duke();
        duke.reset();
        duke.getResponse("todo abc");
        String[] result = duke.getResponse("mark 1").split("\n");
        assertEquals(result[0], "Fantastic! Task conquered, marked as done:");
        assertEquals(result[1], "[T][X] abc");
    }

    @Test
    public void unmarkTaskTest() {
        Duke duke = new Duke();
        duke.reset();
        duke.getResponse("todo abc");
        duke.getResponse("mark 1");
        String[] result = duke.getResponse("unmark 1").split("\n");
        assertEquals(result[0], "Alrighty then! Task status reverted to 'Not done' for now:");
        assertEquals(result[1], "[T][ ] abc");
    }

    @Test
    public void byeTest() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("bye"), "Adieu! Anticipating your triumphant return!");
    }

    @Test
    public void findTaskTest() {
        Duke duke = new Duke();
        duke.reset();
        duke.getResponse("todo abc");
        assertEquals(duke.getResponse("find abc"), "1.[T][ ] abc\n");
    }
}
