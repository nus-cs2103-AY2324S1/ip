package duke.command;

import duke.Ui;
import duke.Storage;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskException;
import duke.task.*;

/**
 * Adds an event task to the taskList
 */
public class EventCommand extends Command {
    protected final static String regex = "^event\\s([\\w\\s]*)\\s\\/from\\s([\\w\\s\\-\\:]*)\\s\\/to\\s([\\w\\s\\-\\:]*)$";

    protected boolean done;
    public EventCommand(String response) {
        super(response, regex);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws InvalidCommandException {
        try {
            if (!matcher.find() || matcher.groupCount() != 3) {
                throw new InvalidTaskException(
                        "Invalid use of event. Usage: event <task description> /from <date & time> /to <date & time>"
                );
            }
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            Task task = new Event(description, from, to);
            taskList.addTask(task);
            return "Got it. I've added this task:\n" +
                    task + "\n" +
                    String.format("Now you have %d tasks in your list", taskList.getSize());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
