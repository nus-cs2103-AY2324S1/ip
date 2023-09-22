package boti.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boti.command.Command;
import boti.exception.InvalidCommandException;
import boti.exception.InvalidListException;
import boti.exception.InvalidToDoException;
import boti.storage.StorageSample;
import boti.task.TaskList;
import boti.ui.UiSample;

/**
 * The test class for Parser
 */
public class ParserTest {
    private UiSample ui;
    private TaskList tasks;
    private TaskList copyTasks;
    private StorageSample storage;

    /**
     * Sets things up before each tests
     */
    @BeforeEach
    public void setup() {
        this.ui = new UiSample();
        this.tasks = new TaskList(new ArrayList<>());
        this.copyTasks = new TaskList(new ArrayList<>());
        this.storage = new StorageSample("./data", "test.txt");
    }

    /**
     * Tests the parse method with list message in Parser
     */
    @Test
    public void parse_list_success() {
        try {
            String message = "list";

            String result = Parser.parse(message, ui, tasks, storage);
            assertEquals(ui.getPrintTasksCount(), 1);

            String expected = Command.list(message, ui, tasks, storage);
            assertEquals(result, expected);
        } catch (InvalidListException e) {
            fail();
        }
    }

    /**
     * Tests the parse method with todo message in Parser
     */
    @Test
    public void parse_todo_success() {
        try {
            String message = "todo buy a chair";

            String result = Parser.parse("todo buy a chair", ui, tasks, storage);
            assertEquals(ui.getPrintAddTaskCount(), 1);

            String expected = Command.addToDo(message, ui, copyTasks, storage);
            assertEquals(result, expected);
        } catch (InvalidToDoException e) {
            fail();
        }
    }
    /**
     * Tests the parse method with invalid message in Parser
     */
    @Test
    public void parse_invalid_fail() {
        String result = Parser.parse("invalid", ui, tasks, storage);
        assertEquals(ui.getPrintExceptionCount(), 1);
        assertEquals(result, ui.printException(new InvalidCommandException()));
    }
}
