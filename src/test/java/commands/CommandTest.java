package commands;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import functions.TaskList;
import functions.Ui;
import functions.Storage;

import java.io.IOException;

public class CommandTest {

    private Command command;

    @BeforeEach
    public void setUp() {
        // Create a mock Command instance for testing
        command = new Command() {
            @Override
            public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
                // Implement a mock execute method for testing
                return "Mock execute method";
            }
        };
    }

    @Test
    public void testExecute() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data.txt");

        try {
            String result = command.execute(tasks, ui, storage);
            assertEquals("Mock execute method", result);
        } catch (IOException e) {
            fail("IOException should not be thrown during testing.");
        }
    }

    @Test
    public void testIsExit() {
        assertFalse(command.isExit()); // The default implementation should return false
    }
}

