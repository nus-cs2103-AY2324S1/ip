package commands;

import duke.DukeException;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {

    private final int index;
    private int size = -1;
    private Task task;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, DataFile dF) throws DukeException {
        if (tasks.isTaskListEmpty()) {
            throw new DukeException("List is already empty, nothing to catch");
        }
        Task task = tasks.remTask(index);
        size = tasks.getSize();
        this.task = task;
        System.out.println("Noted. I've removed this task:\n" + task
                + "\nNow you have " + tasks.getSize()  + " tasks in the list.");
        try {
            dF.deleteTaskFromFile(index);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + size  + " tasks in the list.";
    }
}
