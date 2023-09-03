package bob.command;
import bob.Storage;
import bob.TaskList;
import bob.Ui;

public class FindCommand extends Command {

    public FindCommand(String input) {
        super.input = input;
    }
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String keyword = input.split(" ")[1];
            return ui.stringFormat(tasks.findTasks(keyword));
        } catch (Exception e) {
            return ui.stringFormat(new String[] {"Missing keyword!"});
        }

    }
}
