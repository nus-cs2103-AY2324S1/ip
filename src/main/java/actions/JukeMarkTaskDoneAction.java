package main.java.actions;

import main.java.tasks.JukeTaskManager;
import java.util.Optional;

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
     * @return Optional<? extends JukeAction> object, which contains further action objects,
     * made this way to ensure that actions can call other actions and thus lead to chains
     * of actions for added complexity
     */
    @Override
    public Optional<? extends JukeAction> complete() {
        Optional<? extends JukeAction> optional = this.taskManager.markAsDone(this.index);
        if (optional.isEmpty()) {
            System.out.print("Task Marked as Done!\n" + this.taskManager.taskInformation(this.index));
        }

        return optional;
    }
}
