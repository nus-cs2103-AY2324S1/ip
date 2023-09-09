package com.ducky.command;

import com.ducky.logic.Storage;
import com.ducky.logic.TaskList;
import com.ducky.logic.DuckyException;
import com.ducky.task.Task;

/**
 * Represents a command that changes completion status of a task in Ducky's task list.
 */
public class UpdateTaskCompletionCommand extends Command {

    private final int inputIndex;
    private final boolean isCompleted;

    /**
     * Constructs a command that changes completion status of the task with specified index.
     * @param inputIndex Index of task in task list to be changed.
     * @param isCompleted Whether the specified task is completed.
     */
    public UpdateTaskCompletionCommand(int inputIndex, boolean isCompleted) {
        this.inputIndex = inputIndex;
        this.isCompleted = isCompleted;
    }

    /**
     * Updates the completion status of the task, saves the state to file system,
     * then reflects changes to user interface.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return
     * @throws DuckyException If exceptions specific to Ducky are raised.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DuckyException {
        int updateIndex = this.inputIndex - 1;
        if (this.isCompleted) {
            Task changedTask = taskList.markTaskAsComplete(updateIndex);
            storage.save(taskList);
            return String.format("%s\n%s\n",
                    "Okay! I've marked this task as complete:",
                    changedTask
            );
        } else {
            Task changedTask = taskList.markTaskAsIncomplete(updateIndex);
            storage.save(taskList);
            return String.format("%s\n%s\n",
                    "Okay! I've marked this task as incomplete:",
                    changedTask
            );
        }
    }
}
