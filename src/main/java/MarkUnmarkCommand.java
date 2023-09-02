import java.util.Scanner;

public class MarkUnmarkCommand extends Command {
    private String command;
    private Scanner tokeniser;

    public MarkUnmarkCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        try {
            ui.say(tasks.changeMark(command, tokeniser));
        } catch (IllegalCommandException e) {
            ui.say(e.getMessage());
        }
    }
}
