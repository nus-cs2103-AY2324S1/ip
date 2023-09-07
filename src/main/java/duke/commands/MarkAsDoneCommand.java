package duke.commands;

import duke.parsers.ErrorMessages;
import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkAsDoneCommand extends Command {
    private static final String MARKED_AS_DONE_MESSAGE =
    private final int indexToMark;

    public MarkAsDoneCommand(int index) {
        indexToMark = index;
    }

    public boolean isValidIndex(TaskList list) {
        return indexToMark >= 0 && indexToMark < list.getNumberOfTasks();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (tasks.isEmpty()) {

        }

        if (!isValidIndex(tasks)) {

        } else {
            Task currentTask = tasks.getTask(indexToMark);
            currentTask.markAsDone();
            String message = "Nice! I've marked this task as done:\n " + currentTask;

            ui.appendResponse(message);
            storage.save(tasks);
        }
    }
}
