import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testTodo() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);

        parser.createTask("todo read book");

        String result = "Got it. I've added this task:\n" +
                "[T][ ] read book";

        assertEquals(result, outputStreamCaptor.toString()
                .trim());

    }

    @Test
    void testDeadlineDash() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);

        parser.createTask("deadline return book /by 2023-12-11");

        String result = "Got it. I've added this task:\n" +
                "[D][ ] return book  (by: Dec 11 2023)";

        assertEquals(result, outputStreamCaptor.toString()
                .trim());

    }

    @Test
    void testDeadlineSlash() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);

        parser.createTask("deadline return book /by 1/12/2022 1800");

        String result = "Got it. I've added this task:\n" +
                "[D][ ] return book  (by: Dec 01 2022)";

        assertEquals(result, outputStreamCaptor.toString()
                .trim());

    }

    @Test
    void testDeadlineOthers() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);

        parser.createTask("deadline return book /by December 2022");

        String result = "Got it. I've added this task:\n" +
                "[D][ ] return book  (by:  December 2022)";

        assertEquals(result, outputStreamCaptor.toString()
                .trim());

    }

    @Test
    void testEventDash() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);

        parser.createTask("event return book /from 2022-12-11 /to 2022-12-12");

        String result = "Got it. I've added this task:\n" +
                "[E][ ] return book  (from: Dec 11 2022 to: Dec 12 2022)";

        assertEquals(result, outputStreamCaptor.toString()
                .trim());

    }

    @Test
    void testEventSlash() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);

        parser.createTask("event return book /from 11/12/2022 1800 /to 12/12/2022 1800");

        String result = "Got it. I've added this task:\n" +
                "[E][ ] return book  (from: Dec 11 2022 to: Dec 12 2022)";

        assertEquals(result, outputStreamCaptor.toString()
                .trim());

    }

    @Test
    void testEventOthers() throws DukeException {
        TaskList taskList = new TaskList();
        Parser parser = new Parser(taskList);

        parser.createTask("event return book /from Tuesday /to Thursday");

        String result = "Got it. I've added this task:\n" +
                "[E][ ] return book  (from: Tuesday to: Thursday)";

        assertEquals(result, outputStreamCaptor.toString()
                .trim());

    }


}
