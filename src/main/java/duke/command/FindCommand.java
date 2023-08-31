package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.messages.ErrorMessages;
import duke.messages.Messages;
import duke.task.Task;
import java.util.ArrayList;

/**
 * A command that lists all task associated with the keyword inputted by the user
 */

public class FindCommand extends Command{
    String keyword ;
    public FindCommand(String input){
        this.keyword = input ;
    }

    /**
     * Finds all tasks related to the keyword provided.
     * @param taskList the existing task list of the user
     * @param ui the ui that handles successful/unsuccessful messages
     * @throws DukeException if no task is found, throw an error
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()){
            if (task.getName().contains(this.keyword)){
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()){
            throw new DukeException(ErrorMessages.KEYWORD_NOT_FOUND.getMessage());
        }
        else {
            System.out.println(Messages.FOUND_TASKS.getMessage());
            for (Task matchedTask:matchingTasks) {
                System.out.println(matchedTask.toString());
            }
        }
    }
}
