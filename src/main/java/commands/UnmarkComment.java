package commands;

import duke.DukeException;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class UnmarkComment extends Command {

    private final int index;
    private Task task;

    public UnmarkComment(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, DataFile dF) throws DukeException {
        if (tasks.isTaskListEmpty()) {
            throw new DukeException("List is empty, nothing to unmark");
        }
        Task selTask = tasks.getTask(index);
        selTask.taskNotCompleted();
        task = selTask;
        System.out.println(this);
        try {
            dF.editFileAtLineN(index, '0');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "OK, I've marked this task as not done yet:\n" + task;
    }
}
