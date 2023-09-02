package juke.commands;

import java.util.List;

import juke.Juke;
import juke.responses.Response;
import juke.tasks.JukeTask;
import juke.tasks.TaskList;
import juke.utils.StringUtils;

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
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object that contains response from Juke and the user
     */
    @Override
    public Response execute(Response response) {
        List<JukeTask> foundTask = this.taskList.findTask(this.word);
        StringBuilder stringBuilder = new StringBuilder();

        if (foundTask.size() == 0) {
            stringBuilder.append("Sorry! I could not find any task with the word \"")
                    .append(this.word)
                    .append("\" in the task list!");
        } else {
            stringBuilder.append("Found them! Here are some of the tasks in your task list that contain the "
                    + "word \"")
                    .append(this.word)
                    .append("\":\n");

            for (JukeTask t : foundTask) {
                stringBuilder.append(t)
                        .append("\n");
            }
        }

        return response.withJuke(
                StringUtils.wrap(stringBuilder.toString(), Juke.MAX_STRING_LENGTH));
    }
}
