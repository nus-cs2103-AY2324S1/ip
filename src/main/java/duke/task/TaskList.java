package duke.task;

import java.util.ArrayList;

import duke.exception.InvalidIndexException;
import duke.message.AddTaskMessage;
import duke.message.DeleteTaskMessage;
import duke.message.FindTaskMessage;
import duke.message.MarkTaskMessage;
import duke.message.TaskListMessage;
import duke.message.UnmarkTaskMessage;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param list ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Checks whether index is valid.
     * @return true if index is valid.
     */
    private boolean isValidIndex(int index) {
        return 0 <= index && index < list.size();
    }

    /**
     * Adds a Task to the TaskList.
     * @param item Task to be added.
     * @return AddTaskMessage.
     */
    public AddTaskMessage add(Task item) {
        this.list.add(item);
        return new AddTaskMessage(item, this.list.size());
    }

    /**
     * Deletes a Task from the TaskList.
     * @param num Index of Task to be deleted.
     * @return DeleteTaskMessage.
     * @throws InvalidIndexException If index is invalid.
     */
    public DeleteTaskMessage delete(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.remove(index);
        return new DeleteTaskMessage(task, this.list.size());
    }

    /**
     * Prints the TaskList.
     * @return TaskListMessage.
     */
    public TaskListMessage printList() {
        return new TaskListMessage(list);
    }

    /**
     * Marks a Task as done.
     * @param num Index of Task to be marked.
     * @return MarkTaskMessage.
     * @throws InvalidIndexException If index is invalid.
     */
    public MarkTaskMessage markTask(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.get(index);
        return new MarkTaskMessage(task);
    }

    /**
     * Unmarks a Task as done.
     * @param num Index of Task to be unmarked.
     * @return UnmarkTaskMessage.
     * @throws InvalidIndexException If index is invalid.
     */
    public UnmarkTaskMessage unmarkTask(int num) throws InvalidIndexException {
        int index = num - 1;
        if (!isValidIndex(index)) {
            throw new InvalidIndexException();
        }
        Task task = this.list.get(index);
        return new UnmarkTaskMessage(task);
    }

    /**
     * Finds a Task with the keyword.
     * @param keyword Keyword to be found.
     * @return FindTaskMessage.
     */
    public FindTaskMessage find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t: this.list) {
            if (t.containsKeyword(keyword)) {
                result.add(t);
            }
        }
        return new FindTaskMessage(result);
    }

    /**
     * Returns the string of task to store.
     * @return String of task to store.
     */
    public String toStringStore() {
        StringBuilder sb = new StringBuilder();
        for (Task t: this.list) {
            sb.append(t.toStringStore());
            sb.append("\n");
        }
        return sb.toString();
    }
}
