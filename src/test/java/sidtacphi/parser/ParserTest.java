package sidtacphi.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import sidtacphi.contact.ContactList;
import sidtacphi.exception.SidException;
import sidtacphi.exception.SidInvalidFormatException;
import sidtacphi.task.TaskList;
import sidtacphi.task.Todo;
import sidtacphi.task.Deadline;
import sidtacphi.task.Event;

public class ParserTest {
    @Test
    public void testInvalidCommand() {
        assertThrows(SidException.class, 
                () -> Parser.parseInput(new TaskList(), new ContactList(), "Wow I love JUnit Tests"));
    }

    @Test
    public void testTodo() throws SidException {
        TaskList taskList1 = new TaskList();
        taskList1.add(new Todo("todo test"));

        TaskList taskList2 = new TaskList();
        Parser.parseInput(taskList2, new ContactList(), "todo todo test");
        assertEquals(taskList1, taskList2);
    }

    @Test
    public void testInvalidTodo() {
        assertThrows(SidInvalidFormatException.class, 
                () -> Parser.parseInput(new TaskList(), new ContactList(), 
                "todo"));
    }

    @Test
    public void testDeadline() throws SidException {
        TaskList taskList1 = new TaskList();
        taskList1.add(new Deadline("test", LocalDate.of(2009, 9, 11)));

        TaskList taskList2 = new TaskList();
        Parser.parseInput(taskList2, new ContactList(), "deadline test /by 2009-09-11");
        assertEquals(taskList1, taskList2);
    }

    @Test
    public void testInvalidDeadline() {
        assertThrows(SidInvalidFormatException.class, 
                () -> Parser.parseInput(new TaskList(), new ContactList(), 
                "deadline hehe I never put date"));
    }

    @Test
    public void testEvent() throws SidException {
        TaskList taskList1 = new TaskList();
        taskList1.add(new Event("test", LocalDate.of(2009, 9, 11), LocalDate.of(2009, 9, 12)));

        TaskList taskList2 = new TaskList();
        Parser.parseInput(taskList2, new ContactList(), "event test /from 2009-09-11 /to 2009-09-12");
        assertEquals(taskList1, taskList2);
    }

    @Test
    public void testInvalidEvent() {
        assertThrows(SidInvalidFormatException.class, 
                () -> Parser.parseInput(new TaskList(), new ContactList(), 
                "event imagine if this event starts after it ends /from 2009-09-12 /to 2009-09-11"));
    }
}
