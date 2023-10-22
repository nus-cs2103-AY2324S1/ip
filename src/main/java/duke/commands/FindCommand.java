package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

/**
 * Represents Find Command to be executed
 */
public class FindCommand extends Command {
    private String input;

    public FindCommand(String input, TaskList taskList) {
        super(taskList);
        this.input = input;
    }

    /**
     * Finds user input in list of tasks
     *
     * @return matching tasks if found, else no tasks found message
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String reply = "";
        String[] splitStr = input.trim().split("\\s+");
        inputChecker(splitStr, "find");
        String toFind = input.substring(5);
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(toFind)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() > 0) {
            reply += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < foundTasks.size(); i++) {
                int index = i + 1;
                reply += "  " + index + "." + foundTasks.get(i).toString() + "\n";
            }
        } else {
            reply += "No tasks called " + toFind + " found\n";
        }
        return reply;
    }
}
