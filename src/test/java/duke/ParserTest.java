package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the methods in Parser class.
 *
 * @author Qin Yan Er
 */
public class ParserTest {

    @Test
    public void testParse1() throws DukeException {
        String input1 = "todo buy book";
        String input2 = "todo read book";
        String input3 = "delete 1";
        String input4 = "mark 1";
        TaskList list = new TaskList();
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui();

        String output1 = Parser.parse(input1, list, storage, ui);
        String output2 = Parser.parse(input2, list, storage, ui);
        String output3 = Parser.parse(input3, list, storage, ui);
        String output4 = Parser.parse(input4, list, storage, ui);

        assertEquals("Got it. I've added this task:\n[T][ ] buy book\n" +
                "Now you have 1 tasks in the list.", output1);
        assertEquals("Got it. I've added this task:\n[T][ ] read book\n" +
                "Now you have 2 tasks in the list.", output2);
        assertEquals("Noted. I've removed this task:\n[T][ ] buy book\n" +
                "Now you have 1 tasks in the list.", output3);
        assertEquals("Nice! I've marked this task as done:\n[T][X] read book", output4);

        assertEquals(1, list.getSize());
        assertTrue(list.getTask(0) instanceof Todo);
        assertEquals("[T][X] read book", list.getTask(0).toString());
    }

    @Test
    public void testParse2() throws DukeException {
        String input1 = "deadline cs2030s lab /by 5/10/2023 2359";
        String input2 = "event project meeting /from Sat 8pm /to 10pm";
        TaskList list = new TaskList();
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui();

        String output1 = Parser.parse(input1, list, storage, ui);
        String output2 = Parser.parse(input2, list, storage, ui);


        assertEquals("Got it. I've added this task:\n[D][ ] cs2030s lab " +
                "(by: Oct 5 2023, 11:59PM)\nNow you have 1 tasks in the list.", output1);
        assertEquals("Got it. I've added this task:\n[E][ ] project meeting " +
                "(from: Sat 8pm to: 10pm)\nNow you have 2 tasks in the list.", output2);

        assertEquals(2, list.getSize());
        assertTrue(list.getTask(0) instanceof Deadline);
        assertTrue(list.getTask(1) instanceof Event);
        assertEquals("[D][ ] cs2030s lab (by: Oct 5 2023, 11:59PM)", list.getTask(0).toString());
        assertEquals("[E][ ] project meeting (from: Sat 8pm to: 10pm)", list.getTask(1).toString());
    }

    @Test
    public void testParse3() throws DukeException {
        String input1 = "todo read book";
        String input2 = "mark 10";
        String input3 = "mark 1";
        String input4 = "unmark 3";
        TaskList list = new TaskList();
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui();
        String output1 = Parser.parse(input1, list, storage, ui);
        String output2;
        String output3 = Parser.parse(input3, list, storage, ui);
        String output4;

        try {
            output2 = Parser.parse(input2, list, storage, ui);
        } catch (DukeException e) {
            output2 = e.getMessage(); // Store the exception message
        }
        try {
            output4 = Parser.parse(input4, list, storage, ui);
        } catch (DukeException e) {
            output4 = e.getMessage(); // Store the exception message
        }
        assertEquals("Got it. I've added this task:\n[T][ ] read book\n" +
                "Now you have 1 tasks in the list.", output1);
        assertEquals("OOPS!!! The task you entered is not in the list", output2);
        assertEquals("Nice! I've marked this task as done:\n" +
                "[T][X] read book", output3);
        assertEquals("OOPS!!! The task you entered is not in the list", output4);
        assertEquals(1, list.getSize());
    }

    @Test
    public void testParse4() throws DukeException {
        String input1 = "hello";
        String input2 = "deadline ";
        String input3 = "deadline /by";
        TaskList list = new TaskList();
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui();
        String output1;
        String output2;
        String output3;
        try {
            output1 = Parser.parse(input1, list, storage, ui);
        } catch (DukeException e) {
            output1 = e.getMessage(); // Store the exception message
        }
        try {
            output2 = Parser.parse(input2, list, storage, ui);
        } catch (DukeException e) {
            output2 = e.getMessage(); // Store the exception message
        }
        try {
            output3 = Parser.parse(input3, list, storage, ui);
        } catch (DukeException e) {
            output3 = e.getMessage(); // Store the exception message
        }
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", output1);
        assertEquals("OOPS!!! The description of a deadline cannot be empty.", output2);
        assertEquals("OOPS!!! Please fill in the deadline", output3);
        assertEquals(0, list.getSize());
    }

    @Test
    public void testParse5() {
        String input1 = "event /from";
        String input2 = "event /from Mon 2pm /to";
        String input3 = "mark 0";
        TaskList list = new TaskList();
        Storage storage = new Storage("data/list.txt");
        Ui ui = new Ui();
        String output1;
        String output2;
        String output3;
        try {
            output1 = Parser.parse(input1, list, storage, ui);
        } catch (DukeException e) {
            output1 = e.getMessage(); // Store the exception message
        }
        try {
            output2 = Parser.parse(input2, list, storage, ui);
        } catch (DukeException e) {
            output2 = e.getMessage(); // Store the exception message
        }
        try {
            output3 = Parser.parse(input3, list, storage, ui);
        } catch (DukeException e) {
            output3 = e.getMessage(); // Store the exception message
        }
        assertEquals("OOPS!!! Please fill in the timings", output1);
        assertEquals("OOPS!!! Please fill in the time the event ends", output2);
        assertEquals("OOPS!!! The task you entered is not in the list", output3);
        assertEquals(0, list.getSize());
    }
}
