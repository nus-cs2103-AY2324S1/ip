package adam.command;

import adam.Storage;
import adam.TaskList;
import adam.Ui;
import adam.exception.AdamException;
import adam.tasks.Task;
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class AddCommandTest {
    Ui ui = new Ui();
    Storage storage = new Storage();
    TaskList task = new TaskList(new ArrayList<Task>());
    @Test
    public void executeDeadline_noDescription_exceptionThrown() {
        String li = "deadline";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals("OOPS!!! You need to add a description to these tasks", e.getInfo());
        }
    }

    @Test
    public void executeDeadline_noCommand_exceptionThrown() {
        String li = "deadline test";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals( "OOPS!!! You need to add one /by command to indicate by when deadline is", e.getInfo());
        }
    }
    @Test
    public void executeDeadline_noDescriptionWithCommands_exceptionThrown() {
        String li = "deadline /by ";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals( "OOPS!!! You need to add a description to these tasks", e.getInfo());
        }
    }
    @Test
    public void executeDeadline_wrongDates_exceptionThrown() {
        String li = "deadline test /by test";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals( "OOPS!!! you need the date to be in a yyyy-mm-dd format", e.getInfo());
        }
    }

    @Test
    public void executeEvent_wrongDates_exceptionThrown() {
        String li = "event test /from test /to test";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals( "OOPS!!! you need the date to be in a yyyy-mm-dd format", e.getInfo());
        }
    }

    @Test
    public void executeEvent_noDescriptionWithCommands_exceptionThrown() {
        String li = "event /from /to ";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals( "OOPS!!! You need to add a description to these tasks", e.getInfo());
        }
    }

    @Test
    public void executeEvent_noCommand_exceptionThrown() {
        String li = "event test";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals( "OOPS!!! You need to add one /from and one /to command", e.getInfo());
        }
    }

    @Test
    public void executeEvent_noDescription_exceptionThrown() {
        String li = "event";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals("OOPS!!! You need to add a description to these tasks", e.getInfo());
        }
    }

    @Test
    public void executeTodo_noDescription_exceptionThrown() {
        String li = "todo";
        String[] tokens = li.split(" ");
        int length = tokens[0].length();
        String item = li.substring(length, li.length());
        try {
            new AddCommand(tokens,item,tokens[0]).execute(task,storage,ui);
            fail();
        } catch (AdamException e) {
            assertEquals("OOPS!!! You need to add a description to these tasks", e.getInfo());
        }
    }
}
