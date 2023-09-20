package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;
import taskmanager.Deadline;
import taskmanager.Event;
import taskmanager.ToDo;

/**
 * A command to edit a task.
 */
public class EditCommand extends Command {

    private int index;
    private String newItem;
    private String itemType;


    /**
     * Constructs a `MarkCommand` object with the specified task index.
     *
     * @param index The index of the task to mark as done.
     * @param itemType The item type to edit.
     * @param newItem The new item to update.
     * @throws IllegalArgumentException If the provided index is not positive (greater than 0)
     *                                  and if itemType and newItem is null.
     * */
    public EditCommand(int index, String itemType, String newItem) {
        assert index > 0 : "index cannot be negative or zero";
        assert itemType != null : "Item type for editing cannot be empty";
        assert newItem != null : "New item for editing cannot be empty";
        this.index = index;
        this.itemType = itemType;
        this.newItem = newItem;
    }

    /**
     * Edits the specified task.
     *
     * @param t  The task list containing the tasks.
     * @param ui The user interface to display the result.
     * @param f  The file handler (not used in this command).
     *
     * @return   A string representation of update message.
     */
    @Override
    public String execute(TaskList t, Ui ui, FileHandler f) {
        try {
            if (t.get(index - 1) instanceof ToDo) {
                return editTodo((ToDo) t.get(index - 1), t);
            }
            if (t.get(index - 1) instanceof Event) {
                return editEvent((Event) t.get(index - 1), t);
            }
            if (t.get(index - 1) instanceof Deadline) {
                return editDeadline((Deadline) t.get(index - 1), t);
            }
            return "Invalid task";
        } catch (IndexOutOfBoundsException e) {
            return "Please enter the correct task's index number.";
        }
    }

    /**
     * Edits the ToDo type task.
     *
     * @param todo  The Todo task to update.
     * @param t     The task list to update with the new Todo.
     *
     * @return   A string representation of update message.
     */
    public String editTodo(ToDo todo, TaskList t) {
        if (itemType.equals("taskdesc")) {
            todo.editTaskDesc(newItem);
            FileHandler.writeTasksToFile(t);
            return "The event has been updated!\n"
                    + todo.toString();
        }
        return "Please provide a valid item type for todo (taskdesc)";
    }

    /**
     * Edits the Event type task.
     *
     * @param event  The Event task to update.
     * @param t      The task list to update with the new Event.
     *
     * @return   A string representation of update message.
     */
    public String editEvent(Event event, TaskList t) {
        if (itemType.equals("taskdesc")) {
            event.editTaskDesc(newItem);
            FileHandler.writeTasksToFile(t);
            return "The event has been updated!\n"
                    + event.toString();
        }
        if (itemType.equals("fromdate")) {
            event.editFromDate(newItem);
            FileHandler.writeTasksToFile(t);
            return "The event has been updated!\n"
                    + event.toString();
        }
        if (itemType.equals("todate")) {
            event.editToDate(newItem);
            FileHandler.writeTasksToFile(t);
            return "The event has been updated!\n"
                    + event.toString();
        }
        return "Please provide a valid item type for event! (taskdesc, fromdate and todate)";
    }

    /**
     * Edits the Event type task.
     *
     * @param deadline The Event task to update.
     * @param t        The task list to update with the new deadline.
     *
     * @return   A string representation of update message.
     */
    public String editDeadline(Deadline deadline, TaskList t) {
        if (itemType.equals("taskdesc")) {
            deadline.editTaskDesc(newItem);
            FileHandler.writeTasksToFile(t);
            return "The event has been updated!\n"
                    + deadline.toString();
        }
        if (itemType.equals("duedate")) {
            deadline.editDueDate(newItem);
            FileHandler.writeTasksToFile(t);
            return "The event has been updated!\n"
                    + deadline.toString();
        }
        return "Please provide a valid item type for event! (taskdesc and duedate)";
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

