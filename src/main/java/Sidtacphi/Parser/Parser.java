package sidtacphi.parser;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

import sidtacphi.contact.ContactList;
import sidtacphi.exception.SidException;
import sidtacphi.task.TaskList;
import sidtacphi.task.TaskType;
import sidtacphi.ui.Ui;

/**
 * Parser is the class that deals with interpreting user commands.
 */
public class Parser {
    private Parser() {
    }

    /**
     *  Reads inputs for the bot.
     */
    public static String parseInput(
            TaskList taskList, ContactList contactList, String input) throws SidException {
        input = input.trim();
        if (Objects.equals(input, "bye")) {
            //Solution below inspired by https://stackoverflow.com/a/21996863
            new Timer().schedule(new TimerTask() {
                    public void run () { 
                        Platform.exit();
                    }
                }, 700); 
            return Ui.getGoodbye();
        } else if (Objects.equals(input, "list")) {
            return taskList.showTaskList();
        } else if (input.startsWith("mark")) {
            return taskList.markTaskAs(true, input);
        } else if (input.startsWith("unmark")) {
            return taskList.markTaskAs(false, input);
        } else if (input.startsWith("todo")) {
            return taskList.addTask(TaskType.TODO, input);
        } else if (input.startsWith("event")) {
            return taskList.addTask(TaskType.EVENT, input);
        } else if (input.startsWith("deadline")) {
            return taskList.addTask(TaskType.DEADLINE, input);
        } else if (input.startsWith("delete")) {
            return taskList.deleteTask(input);
        } else if (input.startsWith("find")) {
            return taskList.findTask(input);
        } else if (input.startsWith("addcontact")) {
            return contactList.addContact(input);
        } else if (input.startsWith("deletecontact")) {
            return contactList.deleteContact(input);
        } else if (Objects.equals(input, "listcontacts")) {
            return contactList.showContactList();
        } else if (Objects.equals(input, "listcontact")) {
            return contactList.showContactList();
        } else {
            throw new SidException("\"" + input + "\" is not a valid command.");
        }
    }
}
