package pardiyem.command;

import pardiyem.storage.Storage;
import pardiyem.task.TaskList;
import pardiyem.ui.Ui;

public class ByeCommand extends Command {
    
    public ByeCommand(String desc) {
        super(desc);
        if (!this.desc.isEmpty()) {
            throw new IllegalArgumentException(String
                    .format("You used \"%s\" as an argument. A bye command shouldn't have any arguments", desc));
        }
    }

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showOutput("Ciao! See you again!");
    };
}
