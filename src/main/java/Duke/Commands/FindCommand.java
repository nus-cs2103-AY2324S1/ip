package Duke.Commands;

import Duke.Tools.Storage;
import Duke.Tools.TaskList;
import Duke.Tools.Ui;

public class FindCommand extends Command {

    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        String content = fullCommand.replaceAll("^\\s*find\\s*", "");
        ui.printRelatedTasks(tasks, content);
    }
}
