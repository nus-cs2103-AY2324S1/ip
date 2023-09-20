package boti.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boti.storage.Storage;
import boti.task.TaskList;
import boti.ui.UiSample;



/**
 * The test class for Parser
 */
public class ParserTest {
    private UiSample ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Sets things up before each tests
     */
    @BeforeEach
    public void setup() {
        this.ui = new UiSample();
        this.tasks = new TaskList(new ArrayList<>());
        this.storage = new Storage("./data", "test.txt");
    }

    /**
     * Tests the parse method with list message in Parser
     */
    @Test
    public void parse_list_success() {
        Parser.parse("list", ui, tasks, storage);
        assertEquals(ui.getPrintTasksCount(), 1);
    }

    /**
     * Tests the parse method with invalid message in Parser
     */
    @Test
    public void parse_invalid_fail() {
        Parser.parse("invalid", ui, tasks, storage);
        assertEquals(ui.getPrintExceptionCount(), 1);
    }
}
