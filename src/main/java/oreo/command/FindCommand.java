package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.TaskList;
import oreo.ui.Ui;

import java.util.Scanner;

public class FindCommand extends Command {
    private Scanner tokeniser;

    public FindCommand(Scanner tokeniser) {
        this.tokeniser = tokeniser;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        try {
            ui.say(tasks.findTasksWith(tokeniser));
        } catch (IllegalCommandException e) {
            ui.say(e.getMessage());
        }
    }
}
