package corgi.commands;

import java.util.Stack;
import java.util.function.Predicate;

import corgi.State;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to find tasks containing a specific keyword in the task list.
 */
public class FindTasksContainKeywordCommand extends Command {
    /**
     * The predicate used to filter tasks by keyword.
     */
    private Predicate<Task> predicate;

    /**
     * The target keyword for finding tasks.
     */
    private final String target;

    /**
     * Initializes a new FindTasksContainKeywordCommand instance with the target keyword.
     *
     * @param target The target keyword.
     */
    public FindTasksContainKeywordCommand(String target) {
        super(false);
        this.target = target;

        // Define a predicate to filter tasks based on whether they contain the keyword.
        this.predicate = t -> t.contains(target);
    }

    /**
     * Executes the command by filtering the task list based on the given
     * predicate to find tasks containing specific keyword.
     * It then return the filtered tasks to the user or a message indicating
     * that no matching tasks were found.
     *
     * @param currState The current state of the application.
     * @param history The history stack to store the states.
     * @return A pair containing the new state and a string message indicating the result of the command execution.
     */
    @Override
    public Pair<State, String> execute(State currState, Stack<Pair<State, Command>> history) {
        TaskList currList = currState.getTaskList();
        TextRenderer currTextRenderer = currState.getTextRenderer();

        TaskList tasksContainKeyword = currList.filter(predicate);

        String returnMsg = tasksContainKeyword.isEmpty()
                ? currTextRenderer.showKeywordNotFound(this.target)
                : currTextRenderer.showTasksWithKeyword(this.target, tasksContainKeyword.toString());

        return new Pair<>(currState, returnMsg);
    }
}
