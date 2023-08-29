package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    private String messageCard(String message) {
        String horizontalLine = "\t____________________________________________________________\n";
        return horizontalLine + "\t " + message + "\n" + horizontalLine;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(messageCard("Bye. Hope to see you again soon!"));
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
