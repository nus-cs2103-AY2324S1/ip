package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.task.Task;
import oreo.task.TaskList;
import oreo.ui.Ui;
import java.util.Scanner;

public class AddCommand extends Command {
    private String command;

    private Scanner tokeniser;

    public AddCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            Task newTask = Task.generateTask(command, tokeniser);
            tasks.add(newTask);
            return "Gotchu! noted down: \n"
                    + Ui.indentLineBy(newTask.toString(), 2)
                    + "Now you have " + tasks.getNumberOfTask()
                    + " tasks in the list!";
        } catch (IllegalCommandException e) {
            return e.getMessage();
        } catch (IllegalDateTimeException e) {
            return e.getMessage();
        }
    }
}
