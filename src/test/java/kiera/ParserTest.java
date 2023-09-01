package kiera;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import kiera.command.AddCommand;
import kiera.command.ExitCommand;
import kiera.command.ListCommand;
import kiera.command.MarkCommand;
import kiera.task.Todo;
import kiera.tasktype.TaskType;

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
        assertEquals(new AddCommand(TaskType.TODO, "test1"), Parser.parse("todo test1"));
    }
    @Test
    public void parse_markDone_success() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test2"));
        assertEquals(new MarkCommand("test2", false), Parser.parse("1"));
    }
    @Test
    public void parse_listInput_success() {
        assertEquals(new ListCommand(), Parser.parse("list"));
    }
}
