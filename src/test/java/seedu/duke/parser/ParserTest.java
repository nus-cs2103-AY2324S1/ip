package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Exceptions.LemonException;
import seedu.duke.Tasks.Todo;
import seedu.duke.datafile.Storage;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void initialize() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
        tasks.addTasks(new Todo("Jump 20 times"));
    }
    @Test
    public void listTasks() {
        try {
            Parser.parseTasks("list", tasks, storage, ui);
            assertEquals(tasks.getTasksSize(), 1);
        } catch (LemonException e) {
            fail();
        }
    }

    @Test
    public void markTasks() {
        try {
            tasks.getTask(0).markAsDone();
            assertEquals("[T][X] Jump 20 times",
                    this.tasks.getTask(0).toString());
        } catch (LemonException e) {
            fail();
        }
    }


    @Test
    public void invalidCommand() {
        try {
            Parser.parseTasks("??", tasks, storage, ui);
        } catch (Exception e) {
            assertEquals(":( OPPS!!! I'm sorry, but I don't know what ?? means :-( \n Please add todo/ deadline / event before your task description~ or other commands like mark, unmark & delete", e.getMessage());
        }
    }


}
