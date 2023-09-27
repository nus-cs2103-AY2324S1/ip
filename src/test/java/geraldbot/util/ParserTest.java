package geraldbot.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import geraldbot.exception.DukeInvalidCommandException;
import geraldbot.person.Person;
import geraldbot.task.Deadline;
import geraldbot.task.Event;
import geraldbot.task.Task;
import geraldbot.task.Todo;

public class ParserTest {

    @Test
    public void parse_todoCommand_successfullyAdded() {
        TaskStorage taskStorage = new TaskStorage("testData/testStorageData.txt");
        ContactStorage contactStorage = new ContactStorage("testData/testContactStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Person> contactList = new ArrayList<>();
        Parser parser = new Parser(taskStorage, contactStorage, taskList, contactList);

        String input = "todo Buy groceries";
        assertDoesNotThrow(() -> parser.parse(input));

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Todo);
    }

    @Test
    public void parse_deadlineCommand_successfullyAdded() {
        TaskStorage taskStorage = new TaskStorage("testData/testStorageData.txt");
        ContactStorage contactStorage = new ContactStorage("testData/testContactStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Person> contactList = new ArrayList<>();
        Parser parser = new Parser(taskStorage, contactStorage, taskList, contactList);

        String input = "deadline Submit report /by 16/03/2020 1800";
        assertDoesNotThrow(() -> parser.parse(input));

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Deadline);
    }

    @Test
    public void parse_eventCommand_successfullyAdded() {
        TaskStorage taskStorage = new TaskStorage("testData/testStorageData.txt");
        ContactStorage contactStorage = new ContactStorage("testData/testContactStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Person> contactList = new ArrayList<>();
        Parser parser = new Parser(taskStorage, contactStorage, taskList, contactList);

        String input = "event Team meeting /Monday 2pm/ 4pm";
        assertDoesNotThrow(() -> parser.parse(input));

        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0) instanceof Event);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        TaskStorage taskStorage = new TaskStorage("testData/testStorageData.txt");
        ContactStorage contactStorage = new ContactStorage("testData/testContactStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Person> contactList = new ArrayList<>();
        Parser parser = new Parser(taskStorage, contactStorage, taskList, contactList);

        String input = "invalid_command";
        assertThrows(DukeInvalidCommandException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_emptyDescription_exceptionThrown() {
        TaskStorage taskStorage = new TaskStorage("testData/testStorageData.txt");
        ContactStorage contactStorage = new ContactStorage("testData/testContactStorageData.txt");
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<Person> contactList = new ArrayList<>();
        Parser parser = new Parser(taskStorage, contactStorage, taskList, contactList);

        String input = "todo ";
        assertThrows(DukeInvalidCommandException.class, () -> parser.parse(input));
    }
}
