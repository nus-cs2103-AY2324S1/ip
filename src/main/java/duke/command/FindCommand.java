package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command {
    String word;
    public FindCommand(String word) {
        this.word = word;
    }
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList newList = new TaskList();
        for (int i = 0; i < taskList.getLength(); i++) {
            Task t = taskList.getTask(i);
            if (t.getDescription().contains(word)) {
                newList.addTask(t);
            }
        }

        System.out.println("Why can't you find them yourself? Here, the matching tasks:\n");
        for (int i = 1; i <= newList.getLength(); i++) {
            Task t = newList.getTask(i-1);
            System.out.format("%d. " + t.toString() + "\n", i);
        }
    }
}
