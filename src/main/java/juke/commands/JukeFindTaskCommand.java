package juke.commands;

import juke.tasks.JukeTask;
import juke.tasks.TaskList;

import java.util.List;

public class JukeFindTaskCommand extends JukeCommand {
    /** TaskList to manage all tasks. */
    private final TaskList taskList;

    /** Word of interest to search. */
    private final String word;

    /**
     * Constructor for {@code JukeFindTaskCommand}.
     *
     * @param taskList {@code TaskList} instance
     * @param word Word of interest to search
     */
    public JukeFindTaskCommand(TaskList taskList, String word) {
        this.taskList = taskList;
        this.word = word;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    @Override
    public void complete() {
        List<JukeTask> foundTask = this.taskList.findTask(this.word);

        if (foundTask.size() == 0) {
            System.out.print("Sorry! I could not find any task with the word \"" + this.word + "\" "
                                       + "in the task list!");
        } else {
            System.out.print("Found them! Here are some of the tasks in your task list that contain the " +
                                       "word \"" + this.word + "\":\n");
            for (JukeTask t : foundTask) {
                System.out.print(t + "\n");
            }
        }
    }
}
