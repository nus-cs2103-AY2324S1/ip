package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.TaskList;
import oreo.ui.Ui;
import java.util.Scanner;

public class DeleteCommand extends Command {
    private Scanner tokeniser;

    public DeleteCommand(Scanner tokeniser) {
        this.tokeniser = tokeniser;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        try {
            ui.say(tasks.deleteTask(tokeniser));
        } catch (IllegalCommandException e) {
            ui.say(e.getMessage());
        }
    }
}
