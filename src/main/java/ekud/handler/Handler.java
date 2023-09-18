package ekud.handler;

import ekud.command.Command;

import java.util.List;

import ekud.command.ByeCommand;
import ekud.command.CleanCommand;
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
        } else if (command instanceof CleanCommand) {
            handleClean(state, command, ui);
        } else if (command instanceof CreateTodoCommand) {
            handleCreateTodo(state, command, ui);
        } else if (command instanceof CreateDeadlineCommand) {
            handleCreateDeadline(state, command, ui);
        } else if (command instanceof CreateEventCommand) {
            handleCreateEvent(state, command, ui);
        } else if (command instanceof MarkCommand) {
            handleMark(state, command, ui);
        } else if (command instanceof UnmarkCommand) {
            handleUnmark(state, command, ui);
        } else if (command instanceof DeleteCommand) {
            handleDelete(state, command, ui);
        } else if (command instanceof FindCommand) {
            handleFind(state, command, ui);
        } else {
            throw new UnsupportedOperationException("Invalid command!");
        }

        return true;
    }

    private void handleClean(State state, Command command, Ui ui) {
        state.getTaskList().deduplicate();
        TaskList taskList = state.getTaskList();
        ui.showTasksCleaned();
        ui.showTaskCount(taskList);
    }

    private void handleCreateTodo(State state, Command command, Ui ui) {
        CreateTodoCommand createTodoCommand = (CreateTodoCommand) command;
        TaskList taskList = state.getTaskList();
        Task task = new TodoTask(createTodoCommand.getTitle(), false);
        taskList.addTask(task);
        ui.showTaskAdded(task);
        ui.showTaskCount(taskList);
    }

    private void handleCreateDeadline(State state, Command command, Ui ui) {
        CreateDeadlineCommand createDeadlineCommand = (CreateDeadlineCommand) command;
        TaskList taskList = state.getTaskList();
        Task task = new DeadlineTask(createDeadlineCommand.getTitle(), false, createDeadlineCommand.getBy());
        taskList.addTask(task);
        ui.showTaskAdded(task);
        ui.showTaskCount(taskList);
    }

    private void handleCreateEvent(State state, Command command, Ui ui) {
        CreateEventCommand createEventCommand = (CreateEventCommand) command;
        TaskList taskList = state.getTaskList();
        Task task = new EventTask(createEventCommand.getTitle(), false, createEventCommand.getFrom(),
                createEventCommand.getTo());
        taskList.addTask(task);
        ui.showTaskAdded(task);
        ui.showTaskCount(taskList);
    }

    private void handleMark(State state, Command command, Ui ui) {
        MarkCommand markCommand = (MarkCommand) command;
        TaskList taskList = state.getTaskList();
        Task task = taskList.getTask(markCommand.getTaskId());
        if (task == null) {
            throw new ArgumentException("Invalid task identifier.");
        }
        task.markAsDone();
        ui.showTaskMarked(task);
    }

    private void handleUnmark(State state, Command command, Ui ui) {
        UnmarkCommand unmarkCommand = (UnmarkCommand) command;
        TaskList taskList = state.getTaskList();
        Task task = taskList.getTask(unmarkCommand.getTaskId());
        if (task == null) {
            throw new ArgumentException("Invalid task identifier.");
        }
        task.markAsNotDone();
        ui.showTaskUnmarked(task);
    }

    private void handleDelete(State state, Command command, Ui ui) {
        DeleteCommand deleteCommand = (DeleteCommand) command;
        TaskList taskList = state.getTaskList();
        Task task = taskList.deleteTask(deleteCommand.getTaskId());
        if (task == null) {
            throw new ArgumentException("Invalid task identifier.");
        }
        ui.showTaskRemoved(task);
        ui.showTaskCount(taskList);
    }

    private void handleFind(State state, Command command, Ui ui) {
        FindCommand findCommand = (FindCommand) command;
        TaskList taskList = state.getTaskList();
        List<Task> foundTasks = taskList.findTasks(findCommand.getQuery().trim());
        ui.showFoundTasks(foundTasks);
    }
}
