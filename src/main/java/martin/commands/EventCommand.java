package martin.commands;

import martin.Ui;
import martin.exceptions.EmptyTaskDescriptionException;
import martin.exceptions.MartinException;
import martin.task.Event;
import martin.task.Task;

import java.util.ArrayList;

public class EventCommand implements Command {

    private Ui ui;
    private String command;
    private ArrayList<Task> tasks;

    public EventCommand(String command, ArrayList<Task> tasks) {
        this.ui = new Ui();
        this.command = command;
        this.tasks = tasks;
    }

    /**
    * Adds a new Event task to the task list.
    */
    @Override
    public void execute() throws MartinException {
        if (command.length() <= 5) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        String[] parts = command.substring(6).split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event or its time cannot be empty.");
        }

        tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
        ui.printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }
}
