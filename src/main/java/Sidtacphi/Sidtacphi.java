package sidtacphi;

import sidtacphi.contact.ContactList;
import sidtacphi.exception.SidException;
import sidtacphi.parser.Parser;
import sidtacphi.storage.Storage;
import sidtacphi.task.TaskList;

/**
 * Sidtacphi is the main class for the Sidtacphi bot.
 */
public class Sidtacphi {
    private static TaskList taskList = new TaskList();
    private static ContactList contactList = new ContactList();

    /**
     * Constructs the Sidtacphi object.
     */
    public Sidtacphi() {
        taskList = Storage.readTaskListJson("./stored/tasks.json");
        contactList = Storage.readContactListJson("./stored/contacts.json");
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseInput(taskList, contactList, input);
        } catch (SidException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves taskList as json.
     */
    public void saveTaskList() {
        Storage.saveAsJson(taskList, "./stored/tasks.json");
        Storage.saveAsJson(contactList, "./stored/contacts.json");
    }
}
