package corgi.commands;

import java.util.Stack;

import corgi.State;
import corgi.tasks.Deadline;
import corgi.tasks.Event;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.tasks.ToDo;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to add a task to the task list.
 * This command adds a specified task to the task list.
 * The type of task (todo, deadline, or event) is determined by the command type.
 */
public class AddTaskCommand extends Command {
    /**
     * The task to be added.
     */
    private Task target;

    /**
     * The type of task (todo, deadline, or event) being added.
     */
    private String taskType;

    /**
     * Initializes a new AddTaskCommand instance with the specified task and command type.
     *
     * @param target The task to be added.
     * @param type The type of command.
     */
    public AddTaskCommand(Task target) {
        super(false);
        this.target = target;
        if (target instanceof ToDo) {
            this.taskType = "todo";
        } else if (target instanceof Deadline) {
            this.taskType = "deadline";
        } else if (target instanceof Event) {
            this.taskType = "event";
        }
    }

    /**
     * Executes the command by adding the specified task to the task list, saving the updated list to storage,
     * and storing the state to the history stack.
     *
     * @param currState The current state of the application.
     * @param history The history stack to store the states.
     * @return A pair containing the new state and a string message indicating the result of the command execution.
     */
    @Override
    public Pair<State, String> execute(State currState, Stack<Pair<State, Command>> history) {
        history.push(new Pair<>(currState, this));

        State newState = currState.addTask(this.target);

        TextRenderer renderer = newState.getTextRenderer();
        TaskList list = newState.getTaskList();

        String returnMsg = renderer.showTaskAdded(this.taskType, target.toString(), list.size());

        return new Pair<>(newState, returnMsg);
    }

    @Override
    public String toString() {
        return "Add task " + this.target;
    }
}
