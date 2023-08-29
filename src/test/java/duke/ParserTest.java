package duke;

import duke.command.Command;
import duke.exception.DukeException;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void testList() {
        try{
            Command c = Parser.parse("list");
            assertEquals(false, c.isExit());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testExit() {
        try{
            Command c = Parser.parse("bye");
            assertEquals(true, c.isExit());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testAdd() {
        TaskList tasks = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage("storage.txt");
        try {
            Command c = Parser.parse("todo read book");
            c.execute(tasks, ui, storage);
            assertEquals(1, tasks.getSize());
        } catch (DukeException e){
            fail();
        } catch (IOException e) {
            fail();
        }
    }
}
