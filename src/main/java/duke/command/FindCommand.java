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

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList newList = new TaskList();
        for (int i = 0; i < tasks.getLength(); i++) {
            Task t = tasks.getTask(i);
            if (t.getDescription().contains(word)) {
                newList.addTask(t);
            }
        }

        String returnMessage = "Why can't you find them yourself? Here, the matching tasks:\n"
                + newList.toString();
        return returnMessage;
    }
}
