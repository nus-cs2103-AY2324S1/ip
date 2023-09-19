package kiera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import kiera.command.AddCommand;
import kiera.command.DeleteCommand;
import kiera.command.ExitCommand;
import kiera.command.MarkCommand;
import kiera.task.Todo;

public class ParserTest {
    @Test
    public void parse_wrongInput_exceptionThrown() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("blah"));
            fail();
        } catch (Exception e) {
            assertEquals("invalid input, i don't understand...", e.getMessage());
        }
    }
    @Test
    public void parse_addTodo_success() {
        assertEquals(AddCommand.class, Parser.parse("todo test1").getClass());
    }
    @Test
    public void parse_markDone_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test1"));
        assertEquals(MarkCommand.class, Parser.parse("mark 1").getClass());
    }
    @Test
    public void parse_deleteInput_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test1"));
        assertEquals(DeleteCommand.class, Parser.parse("delete 1").getClass());
    }
}
