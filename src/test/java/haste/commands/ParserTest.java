package haste.commands;

import haste.data.Storage;
import haste.data.TaskList;
import haste.exceptions.HasteException;
import haste.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void invalidCommand() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage store = new Storage("./test.txt");
        try {
            Command c = Parser.handleCommand("delete 1", store);
            c.execute(tasks, ui);
        } catch (HasteException e) {
            Assertions.assertEquals("task does not exist! input within the list of numbered tasks.", e.getMessage());
        } finally {
            store.delete();
        }
    }
    @Test
    public void validCommand() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage store = new Storage("./test.txt");
        try {
            Command c = Parser.handleCommand("todo test", store);
            c.execute(tasks, ui);
        } catch (HasteException e) {
            Assertions.fail();
        } finally {
            store.delete();
        }
    }
}
