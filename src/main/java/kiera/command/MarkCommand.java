package kiera.command;

import java.util.stream.Collectors;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;
import kiera.exception.KieraException;
import kiera.task.Task;

/**
 * Command to mark a task as done or undone.
 */
public class MarkCommand extends Command {
    private boolean mark;

    /**
     * Constructor for MarkCommand.
     *
     * @param desc Description of task to be marked done or undone.
     * @param mark If true, task will be marked done; else, it will be marked undone.
     */
    public MarkCommand(String desc, boolean mark) {
        setDescription(desc);
        this.mark = mark;
    }

    /**
     * @inheritDoc
     *
     * @throws KieraException If there is an error with the task type or storage operation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KieraException {

        try {
            int index = Integer.parseInt(this.getDescription());
            Task t = tasks.getTaskByIndex(index);
            String notify;
            if (mark) {
                notify = "yay, you're one task down!";
                t.markAsDone();
            } else {
                notify = "ok, this task has been marked undone.";
                t.markAsUndone();
            }

            String result = tasks.getTasks().stream()
                    .map(task -> "     " + tasks.indexOf(task) + ". " + task + "\n")
                    .collect(Collectors.joining());
            System.out.println(notify);
            storage.save(tasks);
            ui.showList(result);
        } catch (IndexOutOfBoundsException e) {
            throw new KieraException("your task number is not in the task list!");
        }

    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
