package jeoe.Commands;

import jeoe.Others.StorageManager;
import jeoe.Others.Ui;
import jeoe.Tasks.Event;
import jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the class EventCommand.
 * It is meant to execute the creation of an Event object.
 *
 * @author Joe Chua
 * @version Week-6
 */
public class EventCommand extends Command {

    /** Contains the components of the event command */
    private String[] eventArr;

    /**
     * Constructor for an EventCommand object.
     * @param input The string input by the user to parse into a command.
     */
    EventCommand(String input) {
        super(false);
        this.eventArr = input.replaceFirst("event ", "").split(" /from "); // eventArr have description
    }

    /**
     * Executes the event command.
     * Creates an Event object, adds it to the task list, saves it in local storage then displays it.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        try {
            String eventDescription = eventArr[0];
            String[] eventArr2 = eventArr[1].split(" /to "); // eventArr2 have the from & to
            String from = eventArr2[0];
            String to = eventArr2[1];
            Event event = new Event(eventDescription, from, to);

            // add to storage
            taskManager.addTask(event);
            storageManager.save(taskManager.getTasks());

            // add to the reply
            ui.displayReply(event.replyString(taskManager.getTasksSize()));
        } catch (Exception e) {
            ui.displayReply("event command is invalid, please try again");
        }
    }

    /**
     * Executes the event command.
     * Creates an Event object, adds it to the task list, saves it in local storage then returns string.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    public String executeAndReply(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        try {
            String eventDescription = eventArr[0];
            String[] eventArr2 = eventArr[1].split(" /to "); // eventArr2 have the from & to
            String from = eventArr2[0];
            String to = eventArr2[1];
            Event event = new Event(eventDescription, from, to);

            // add to storage
            taskManager.addTask(event);
            storageManager.save(taskManager.getTasks());

            // add to the reply
            return ui.getReply(event.replyString(taskManager.getTasksSize()));
        } catch (Exception e) {
            return ui.getReply("event command is invalid, please try again");
        }
    }
}
