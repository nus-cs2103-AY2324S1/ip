package geraldbot;

import java.util.ArrayList;

import geraldbot.exception.DukeException;
import geraldbot.person.Person;
import geraldbot.task.Task;
import geraldbot.util.ContactStorage;
import geraldbot.util.Parser;
import geraldbot.util.TaskStorage;

/**
 * The main class that represents the Duke chatbot application.
 * Duke is a task manager that can handle various commands to manage tasks.
 */
public class Duke {
    private final Parser parser;

    /**
     * Constructs a Duke object and initializes the user interface and parser.
     * Reads task data from storage and initializes the task list.
     */
    public Duke() {
        TaskStorage taskStorage = new TaskStorage("./data/data.txt");
        ContactStorage contactStorage = new ContactStorage("./data/contacts.txt");
        ArrayList<Task> taskList = taskStorage.read();
        ArrayList<Person> contactList = contactStorage.read();
        this.parser = new Parser(taskStorage, contactStorage, taskList, contactList);
    }

    /**
     * Returns the response to the user input.
     *
     * @param input The user input.
     * @return The response to the user input.
     */
    public String getResponse(String input) {
        try {
            String response = parser.parse(input);
            return response;
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
