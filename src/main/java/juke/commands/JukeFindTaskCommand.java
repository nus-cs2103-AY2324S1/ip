package juke.commands;

import java.util.List;

import juke.tasks.JukeTask;
import juke.tasks.TaskList;

/**
 * Action that finds a Task in the {@code TaskList} according to the
 * task description.
 */
public class JukeFindTaskCommand extends JukeCommand {
    /** {@code TaskList} to manage all tasks. */
    private final TaskList taskList;

    /** Word of interest to search. */
    private final String word;

    /**
     * Creates an instance of {@code JukeFindTaskCommand}.
     *
     * @param taskList {@code TaskList} instance
     * @param word Word of interest to search
     */
    public JukeFindTaskCommand(TaskList taskList, String word) {
        this.taskList = taskList;
        this.word = word;
    }

    /**
     * Carries out an action when the command is executed.
     */
    @Override
    public void execute() {
        List<JukeTask> foundTask = this.taskList.findTask(this.word);

        if (foundTask.size() == 0) {
            System.out.print("Sorry! I could not find any task with the word \"" + this.word + "\" "
                                       + "in the task list!");
        } else {
            System.out.print("Found them! Here are some of the tasks in your task list that contain the "
                                     + "word \"" + this.word + "\":\n");
            for (JukeTask t : foundTask) {
                System.out.print(t + "\n");
            }
        }
    }
}
