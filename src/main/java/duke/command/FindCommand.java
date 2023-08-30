package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public class FindCommand extends Command{
    private String keyword;
    private boolean isExit = false;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String res = "Here are the matching tasks in your list: ";
        int count = 1;

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);

            if(task.getDescription().contains(this.keyword)) {
                res += "\n" + count + ". " + task;
                count++;
            }
        }

        ui.printMessage(res);
    }
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
