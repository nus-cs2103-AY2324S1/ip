package duke.command;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

import duke.tasks.Task;

public class ModifyCommand extends Command{
    private String type;
    private int index;
    private boolean isExit = false;

    public ModifyCommand(String type, int index) {
        this.type = type;
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (type.equals("L")) {
                ui.showList(tasks);
            } else {
                Task task = tasks.getTask(index);
                if (type.equals("M")) {
                    tasks.mark(index);
                    ui.showTaskCompleted(task);
                } else if (type.equals("U")) {
                    tasks.unmark(index);
                    ui.showTaskUnmarked(task);
                } else if (type.equals("D")) {
                    tasks.delete(index);
                    ui.showTaskDeleted(task, tasks.getSize());
                }
                storage.saveTasks(tasks.getTasks());
            }
        } catch (Exception ex) {
            throw new DukeException("I'm afraid such a task do not exist.");
        }
    }

    @Override
    public boolean isExit() {
        return isExit;
    }

    public String getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
