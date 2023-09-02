package bob.command;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super.input = input;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String keyword = input.split(" ")[1];
            ui.stringFormat(tasks.findTasks(keyword));
        } catch (Exception e) {
            ui.stringFormat(new String[] {"Missing keyword!"});
        }

    }
}
