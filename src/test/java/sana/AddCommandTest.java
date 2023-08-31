package sana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class AddCommandTest {

    @Test
    public void execute_emptyTodoDescription_sanaExceptionThrown() {
        Command c = new AddCommand("todo", " ");
        try {
            c.execute(new TaskList("mockList"), new Ui(new Scanner("mockScanner")), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void execute_emptyDeadlineDescription_sanaExceptionThrown() {
        Command c = new AddCommand("deadline", " ");
        try {
            c.execute(new TaskList("mockList"), new Ui(new Scanner("mockScanner")), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!!! Incomplete task description.\nMake sure you follow the format "
                    + "'deadline [name of task] /by [deadline]'", e.getMessage());
        }
    }

    @Test
    public void execute_emptyDeadlineByDate_sanaExceptionThrown() {
        Command c = new AddCommand("deadline", "description /by ");
        try {
            c.execute(new TaskList("mockList"), new Ui(new Scanner("mockScanner")), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!! The deadline cannot be empty.\nMake sure you follow the format "
                    + "'deadline [name of task] /by [deadline]'", e.getMessage());
        }
    }

    @Test
    public void execute_emptyEventDescription_sanaExceptionThrown() {
        Command c = new AddCommand("event", " ");
        try {
            c.execute(new TaskList("mockList"), new Ui(new Scanner("mockScanner")), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!!! Incomplete command. Make sure you follow the format "
                    + "'event [name of task] /from [from] /to [to]'", e.getMessage());
        }
    }

    @Test
    public void execute_emptyEventFromDate_sanaExceptionThrown() {
        Command c = new AddCommand("event", "/from ");
        try {
            c.execute(new TaskList("mockList"), new Ui(new Scanner("mockScanner")), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!! The 'from' field cannot be empty.\nMake sure you follow the format "
                    + "'deadline [name of task] /from [from] /to [to]'", e.getMessage());
        }
    }

    @Test
    public void execute_emptyEventToDate_sanaExceptionThrown() {
        Command c = new AddCommand("event", "description /from 2020-10-20 /to ");
        try {
            c.execute(new TaskList("mockList"), new Ui(new Scanner("mockScanner")), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!! The 'to' field cannot be empty.\nMake sure you follow the format "
                    + "'deadline [name of task] /from [from] /to [to]'", e.getMessage());
        }
    }
}
