package main.java.actions;

import main.java.tasks.JukeTask;
import main.java.tasks.JukeTaskManager;

import java.util.Optional;

/**
 * Action that adds a Task to the Task Manager.
 */
public class JukeAddTaskAction extends JukeAction {
    /** JukeTaskManager to manage all tasks. */
    private final JukeTaskManager taskManager;

    /** JukeTask to add. */
    private final JukeTask task;

    /**
     * Constructor for JukeAddTaskAction
     * @param taskManager JukeTaskManager
     * @param task JukeTask to add
     */
    public JukeAddTaskAction(JukeTaskManager taskManager, JukeTask task) {
        this.taskManager = taskManager;
        this.task = task;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     * @return Optional<? extends JukeAction> object, which contains further action objects,
     * made this way to ensure that actions can call other actions and thus lead to chains
     * of actions for added complexity
     */
    @Override
    public Optional<? extends JukeAction> complete() {
        if (this.taskManager.addTask(this.task)) {
            System.out.print("Task added: " + this.task);
            return Optional.empty();
        } else {
            return Optional.of(new JukeErrorAction("Oops! We cannot add the task of interest to your task list!"));
        }
    }
}
