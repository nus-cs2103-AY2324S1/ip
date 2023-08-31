package commands;

import duke.DukeException;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class MarkCommand extends Command{
    private Task task;
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, DataFile dF) throws DukeException{
        if (tasks.isTaskListEmpty()) {
            throw new DukeException("List is empty, nothing to mark");
        }
        Task task = tasks.getTask(index);
        task.taskCompleted();
        this.task = task;
        System.out.println(this);
        try {
            dF.editFileAtLineN(index, '1');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Nice! I've marked this task as done:\n" + task;
    }
}
