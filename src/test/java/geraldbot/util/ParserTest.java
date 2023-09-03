package geraldbot.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import geraldbot.exception.DukeInvalidCommandException;
import geraldbot.task.Deadline;
import geraldbot.task.Event;
import geraldbot.task.Task;
import geraldbot.task.Todo;

public class ParserTest {

    @Test
    public void parse_todoCommand_successfullyAdded() {
        Storage storage = new Storage("testData/testStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        Parser parser = new Parser(storage, taskList);

        String input = "todo Buy groceries";
        assertDoesNotThrow(() -> parser.parse(input));

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Todo);
    }

    @Test
    public void parse_deadlineCommand_successfullyAdded() {
        Storage storage = new Storage("testData/testStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        Parser parser = new Parser(storage, taskList);

        String input = "deadline Submit report /by 16/03/2020 1800";
        assertDoesNotThrow(() -> parser.parse(input));

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Deadline);
    }

    @Test
    public void parse_eventCommand_successfullyAdded() {
        Storage storage = new Storage("testData/testStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        Parser parser = new Parser(storage, taskList);

        String input = "event Team meeting /Monday 2pm/ 4pm";
        assertDoesNotThrow(() -> parser.parse(input));

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Event);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        Storage storage = new Storage("testData/testStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        Parser parser = new Parser(storage, taskList);

        String input = "invalid_command";
        assertThrows(DukeInvalidCommandException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_emptyDescription_exceptionThrown() {
        Storage storage = new Storage("testData/testStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        Parser parser = new Parser(storage, taskList);

        String input = "todo ";
        assertThrows(DukeInvalidCommandException.class, () -> parser.parse(input));
    }
}
