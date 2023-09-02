import java.util.Scanner;

public class AddCommand extends Command {
    private String command;

    private Scanner tokeniser;

    public AddCommand(String command, Scanner tokeniser) {
        this.command = command;
        this.tokeniser = tokeniser;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        try {
            Task newTask = Task.generateTask(command, tokeniser);
            tasks.add(newTask);
            ui.say("Gotchu! noted down: \n" +
                    Ui.indentLineBy(newTask.toString(), 2) +
                    "Now you have " +
                    tasks.getNumberOfTask() +
                    " tasks in the list!");
        } catch (IllegalCommandException e) {
            ui.say(e.getMessage());
        } catch (IllegalDateTimeException e) {
            ui.say(e.getMessage());
        }
    }
}
