package pardiyem.command;

import java.io.IOException;

import pardiyem.storage.Storage;
import pardiyem.task.TaskList;
import pardiyem.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String desc) {
        super(desc);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.showOutput(taskList.delete(desc));
        storage.save(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof DeleteCommand) {
            result = this.desc.equals(((DeleteCommand) obj).desc);
        }
        return result;
    }
}
