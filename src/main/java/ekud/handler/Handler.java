package ekud.handler;

import ekud.command.Command;

import java.util.List;

import ekud.command.ByeCommand;
import ekud.command.CreateDeadlineCommand;
import ekud.command.CreateEventCommand;
import ekud.command.CreateTodoCommand;
import ekud.command.DeleteCommand;
import ekud.command.FindCommand;
import ekud.command.ListCommand;
import ekud.command.MarkCommand;
import ekud.command.UnmarkCommand;
import ekud.error.ArgumentException;
import ekud.state.DeadlineTask;
import ekud.state.EventTask;
import ekud.state.State;
import ekud.state.Task;
import ekud.state.TaskList;
import ekud.state.TodoTask;
import ekud.ui.Ui;

/**
 * Handles modifying the current state of the program based on the command
 * given, passing side effects the user interface.
 */
public final class Handler {
    /**
     * Handles the command given by getting user input from the user interface,
     * modifying the current state of the program and passing side effects back to
     * the user interface.
     * 
     * @param state   The current state of the program.
     * @param command The command to handle.
     * @param ui      The user interface.
     * @return Whether to continue execution of the program main loop.
     */
    public boolean handle(State state, Command command, Ui ui) {
        if (command instanceof ByeCommand) {
            return false;
        } else if (command instanceof ListCommand) {
            ui.showTaskList(state.getTaskList());
            return true;
        } else if (command instanceof CreateTodoCommand) {
            CreateTodoCommand createTodoCommand = (CreateTodoCommand) command;
            TaskList taskList = state.getTaskList();
            Task task = new TodoTask(createTodoCommand.getTitle(), false);
            taskList.addTask(task);
            ui.showTaskAdded(task);
            ui.showTaskCount(taskList);
            return true;
        } else if (command instanceof CreateDeadlineCommand) {
            CreateDeadlineCommand createDeadlineCommand = (CreateDeadlineCommand) command;
            TaskList taskList = state.getTaskList();
            Task task = new DeadlineTask(createDeadlineCommand.getTitle(), false, createDeadlineCommand.getBy());
            taskList.addTask(task);
            ui.showTaskAdded(task);
            ui.showTaskCount(taskList);
            return true;
        } else if (command instanceof CreateEventCommand) {
            CreateEventCommand createEventCommand = (CreateEventCommand) command;
            TaskList taskList = state.getTaskList();
            Task task = new EventTask(createEventCommand.getTitle(), false, createEventCommand.getFrom(),
                    createEventCommand.getTo());
            taskList.addTask(task);
            ui.showTaskAdded(task);
            ui.showTaskCount(taskList);
            return true;
        } else if (command instanceof MarkCommand) {
            MarkCommand markCommand = (MarkCommand) command;
            TaskList taskList = state.getTaskList();
            Task task = taskList.getTask(markCommand.getTaskId());
            if (task == null) {
                throw new ArgumentException("Invalid task identifier.");
            }
            task.mark();
            ui.showTaskMarked(task);
            return true;
        } else if (command instanceof UnmarkCommand) {
            UnmarkCommand unmarkCommand = (UnmarkCommand) command;
            TaskList taskList = state.getTaskList();
            Task task = taskList.getTask(unmarkCommand.getTaskId());
            if (task == null) {
                throw new ArgumentException("Invalid task identifier.");
            }
            task.unmark();
            ui.showTaskUnmarked(task);
            return true;
        } else if (command instanceof DeleteCommand) {
            DeleteCommand deleteCommand = (DeleteCommand) command;
            TaskList taskList = state.getTaskList();
            Task task = taskList.deleteTask(deleteCommand.getTaskId());
            if (task == null) {
                throw new ArgumentException("Invalid task identifier.");
            }
            ui.showTaskRemoved(task);
            ui.showTaskCount(taskList);
            return true;
        } else if (command instanceof FindCommand) {
            FindCommand findCommand = (FindCommand) command;
            TaskList taskList = state.getTaskList();
            List<Task> foundTasks = taskList.findTasks(findCommand.getQuery().trim());
            ui.showFoundTasks(foundTasks);
            return true;
        } else {
            throw new UnsupportedOperationException("Invalid command!");
        }
    }
}
