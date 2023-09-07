package oreo.command;

import oreo.exception.IllegalCommandException;
import oreo.task.TaskList;
import oreo.ui.Ui;

import java.util.Scanner;

public class MarkUnmarkCommand extends Command {
    private String command;
    private Scanner tokeniser;

    public MarkUnmarkCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            return tasks.changeMark(command, tokeniser);
        } catch (IllegalCommandException e) {
            return e.getMessage();
        }
    }
}
