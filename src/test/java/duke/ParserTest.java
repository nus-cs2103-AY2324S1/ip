package duke;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the methods in Parser class.
 *
 * @author Qin Yan Er
 */
public class ParserTest {

//    @Test
//    public void testParse1() throws DukeException {
//        String input1 = "todo buy book";
//        String input2 = "todo read book";
//        String input3 = "delete 1";
//        String input4 = "mark 1";
//        TaskList list = new TaskList();
//        Storage storage = new Storage("data/list.txt");
//        Ui ui = new Ui();
//
//        Parser.parse(input1, list, storage, ui);
//        Parser.parse(input2, list, storage, ui);
//        Parser.parse(input3, list, storage, ui);
//        Parser.parse(input4, list, storage, ui);
//
//
//        assertEquals(1, list.getSize());
//        assertTrue(list.getTask(0) instanceof Todo);
//        assertEquals("[T][X] read book", list.getTask(0).toString());
//    }
//
//    @Test
//    public void testParse2() {
//        String input1 = "deadline cs2030s lab /by 5/10/2023 2359";
//        String input2 = "event project meeting /from Sat 8pm /to 10pm";
//        String input3 = "mark 10";
//        String input4 = "mark 1";
//        String input5 = "mark 2";
//        String input6 = "unmark 2";
//        TaskList list = new TaskList();
//        Storage storage = new Storage("data/list.txt");
//        Ui ui = new Ui();
//
//        Parser.parse(input1, list, storage, ui);
//        Parser.parse(input2, list, storage, ui);
//        Parser.parse(input3, list, storage, ui);
//        Parser.parse(input4, list, storage, ui);
//        Parser.parse(input5, list, storage, ui);
//        Parser.parse(input6, list, storage, ui);
//
//        assertEquals(2, list.getSize());
//        assertTrue(list.getTask(0) instanceof Deadline);
//        assertTrue(list.getTask(1) instanceof Event);
//        assertEquals(
//                "[D][X] cs2030s lab (by: Oct 5 2023, 11:59PM)",
//                list.getTask(0).toString());
//        assertEquals(
//                "[E][ ] project meeting (from: Sat 8pm to: 10pm)",
//                list.getTask(1).toString());
//    }
//
//    @Test
//    public void testParse3() {
//        String input1 = "hello";
//        String input2 = "deadline ";
//        String input3 = "deadline /by";
//        String input4 = "event /from";
//        String input5 = "event /from Mon 2pm /to";
//        String input6 = "mark 0";
//        TaskList list = new TaskList();
//        Storage storage = new Storage("data/list.txt");
//        Ui ui = new Ui();
//
//        Parser.parse(input1, list, storage, ui);
//        Parser.parse(input2, list, storage, ui);
//        Parser.parse(input3, list, storage, ui);
//        Parser.parse(input4, list, storage, ui);
//        Parser.parse(input5, list, storage, ui);
//        Parser.parse(input6, list, storage, ui);
//
//        assertEquals(0, list.getSize());
//    }
}
