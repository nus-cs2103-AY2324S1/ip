package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Formatter;

import java.util.Scanner;

public class DeleteCommand extends Command {
    private String command;
    private Scanner tokeniser;

    public DeleteCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    public String actOn(TaskList tasks) throws IllegalCommandException {
        if (command.equals("clear")) {
            tasks.clearAll();
            return "Cleared all your task for you! Play time?";
        }
        // nothing specified after command
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        // specified content is not an integer
        String content = tokeniser.next();
        if (!isInteger(content)) {
            if (content.equals("all")) {
                tasks.clearAll();
                return "Cleared all your task for you! Play time?";
            }
            throw new IllegalCommandException("do that... try a number instead");
        }
        // if number of task does not exist
        int id = Integer.parseInt(content);
        if (id > tasks.getNumberOfTask() || id <= 0) {
            throw new IllegalCommandException("do that... this task does not exist :(");
        }
        Task removedTask = tasks.remove(id - 1);
        assert removedTask != null: "removed task must exist";
        String message = "Happily scratched this off your list:\n"
                + Formatter.indentLineBy(removedTask.toString(), 2) + "Now you have "
                + tasks.getNumberOfTask() + " tasks in the list!";
        if (tasks.isAllComplete()) {
            message += tasks.list();
        }
        return message;
    }

    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) {
        return execute(tasks);
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            return actOn(tasks);
        } catch (IllegalCommandException e) {
            return e.getMessage();
        }
    }
}
