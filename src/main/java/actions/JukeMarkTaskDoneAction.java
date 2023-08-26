package main.java.actions;

import main.java.exceptions.storage.JukeStorageException;
import main.java.tasks.JukeTaskManager;

/**
 * Action that marks a JukeTask as done.
 */
public class JukeMarkTaskDoneAction extends JukeAction {
    /** JukeTaskManager that manages the tasks. */
    private final JukeTaskManager taskManager;

    /** The index of the task of interest. */
    private final int index;

    /**
     * Constructor for JukeMarkTaskDoneAction.
     * @param taskManager JukeTaskManager that manages the tasks
     * @param index Index of the Task to act on
     */
    public JukeMarkTaskDoneAction(JukeTaskManager taskManager, int index) {
        this.taskManager = taskManager;
        this.index = index;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    @Override
    public void complete() throws JukeStorageException {
        this.taskManager.markAsDone(this.index);
        System.out.print("Task Marked as Done!\n" + this.taskManager.taskInformation(this.index));
    }
}
