package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Exception.DukeException;
import duke.task.ToDo;


public class ParserTest {
    private Parser parser = new Parser();

    @Test
    public void testMarkCommand() throws DukeException {
        TaskList tasks = new TaskList();
        ToDo sampleToDo = new ToDo("do econs");
        tasks.addTask(sampleToDo);
        Storage storage = new Storage("data/duke.txt", tasks);
        parser.userCommand("mark 1", storage, tasks);
        assertEquals("[T][X] do econs", tasks.getTask(0).toString());
    }
    @Test
    public void testUnmarkCommand() throws DukeException {
        TaskList tasks = new TaskList();
        ToDo sampleToDo = new ToDo("do econs");
        tasks.addTask(sampleToDo);
        Storage storage = new Storage("data/duke.txt", tasks);
        parser.userCommand("mark 1", storage, tasks);
        parser.userCommand("unmark 1", storage, tasks);
        assertEquals("[T][ ] do econs", tasks.getTask(0).toString());
    }

    @Test
    public void testDeleteCommand() throws DukeException {
        TaskList tasks = new TaskList();
        ToDo sampleToDo = new ToDo("do econs");
        tasks.addTask(sampleToDo);
        Storage storage = new Storage("data/duke.txt", tasks);
        parser.userCommand("delete 1", storage, tasks);
        assertEquals(0, tasks.getSize());
    }
    @Test
    public void testRandomCommand() {
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/duke.txt", tasks);
        try {
            parser.userCommand("add", storage, tasks);
        }
        catch (DukeException e) {
            assertEquals("Hey bud! Sorry I don't quite know what you mean :-(", e.getMessage());
        }
    }

//    @Test
//    public void testAddToList() {
//        TaskList tasks = new TaskList();
//        Storage storage = new Storage("data/duke.txt", tasks);
//        parser.addToList("todo hi", storage, tasks);
//        assertEquals("[T][ ] hi", tasks.getTask(0).toString());
//    }






}
