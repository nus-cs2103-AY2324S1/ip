package com.alpha.commands;

import java.time.LocalDateTime;

import com.alpha.exceptions.InvalidTaskException.DuplicateTaskException;
import com.alpha.tasks.Deadline;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;

/**
 * The type Deadline command.
 */
public class DeadlineCommand extends Command {

    private final Task task;

    /**
     * Instantiates a new Deadline command.
     *
     * @param name     The name of the task.
     * @param end      The end datetime as a LocalDateTime object.
     * @param taskList The task list.
     */
    public DeadlineCommand(String name, LocalDateTime end, TaskList taskList) throws DuplicateTaskException {
        super(taskList);
        for (Task currentTasks : taskList.getTasks()) {
            if (currentTasks.getName().equals(name)) {
                throw new DuplicateTaskException();
            }
        }
        task = new Deadline(name, end);
    }

    @Override
    public String execute() {
        getTaskList().addTask(task);
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + super.getTaskListSize() + " tasks in the list.\n";

    }
}
