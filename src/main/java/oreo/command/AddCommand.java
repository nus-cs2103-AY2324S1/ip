package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Formatter;
import java.util.Scanner;

public class AddCommand extends Command {
    private String command;

    private Scanner tokeniser;

    /**
     * Constructor for AddCommand.
     *
     * @param command todo, deadline or event.
     * @param tokeniser rest of user input.
     */
    public AddCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String executeEditMode(TaskList tasks, int index, Task oldTask) throws IllegalCommandException,
            IllegalDateTimeException {
        Task newTask = Task.generateTask(command, tokeniser);
        tasks.modifyTask(index, newTask);
        return "Modified this: \n"
                + Formatter.indentLineBy(oldTask.toString(), 2)
                + "to this: \n"
                + Formatter.indentLineBy(newTask.toString(), 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks) {
        try {
            Task newTask = Task.generateTask(command, tokeniser);
            tasks.add(newTask);
            return "Gotchu! noted down: \n"
                    + Formatter.indentLineBy(newTask.toString(), 2)
                    + "Now you have " + tasks.getNumberOfTask()
                    + " tasks in the list!";
        } catch (IllegalCommandException e) {
            return e.getMessage();
        } catch (IllegalDateTimeException e) {
            return e.getMessage();
        }
    }
}
