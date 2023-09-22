package duke;

import java.util.ArrayList;
import java.util.List;

/** Abstraction of a list that can store Tasks */
public class TaskList {

    /** List to store tasks */
    private List<Task> list;

    /**
     * Creates a new TaskList.
     *
     * @param taskList List containing the task to store.
     */
    public TaskList(List<Task> taskList) {
        this.list = taskList;
    }

    /**
     * Marks the task input by users.
     *
     * @param index Index of task to be marked.
     * */
    public String mark(int index) {
        try {
            assert 0 <= index && index < this.list.size() : "Task must exist in the list";
            return this.list.get(index).markAsDone();
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
        }
    }

    /**
     * Unmarks the task input by user.
     *
     * @param index Index of task to be unmarked.
     */
    public String unmark(int index) {
        try {
            assert 0 <= index && index < this.list.size() : "Task must exist in the list";
            return this.list.get(index).markAsUndone();
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
        }
    }

    /**
     * Stores the task into the task list.
     *
     * @param task Task to be added to list.
     */
    public void store(Task task) {
        this.list.add(task);
    }

    /**
     * Removes the task specified by the index inputed.
     *
     * @param index Position of the task that is to be removed.
     */
    public void delete(int index) {
        assert !this.list.isEmpty() : "Cannot delete from empty list";
        this.list.remove(index);
    }

    /**
     * Gets the task at the specific index of the list.
     *
     * @param index Position of the task in the list.
     * @return The task at the index position.
     */
    public Task retrieve(int index) {
        assert 0 <= index && index < this.list.size() : "Task must exist in the list";
        return this.list.get(index);
    }

    /**
     * Returns length of the list.
     *
     * @return Length of list.
     */
    public int length() {
        return this.list.size();
    }

    /** Prints out the list of tasks stored. */
    public String printList() {
        StringBuilder printedList = new StringBuilder("Here are the tasks in your list:\n");
        for (int index = 0; index < this.list.size(); index++) {
            Task item = this.list.get(index);
            String description = (index + 1) + ". " + item.toString() + "\n";
            printedList.append(description);
        }
        return printedList.toString();
    }

    /**
     * Finds all task with word that similar to user input and prints it out to them.
     *
     * @param filterWord Word to be matched with task description.
     */
    public String find(String filterWord) {
        StringBuilder reply = new StringBuilder("Here are the matching tasks in your list:\n");
        List<Task> filteredList = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            if (task.description.contains(filterWord)) {
                filteredList.add(task);
            }
        }
        for (int i = 0; i < filteredList.size(); i++) {
            String taskDescription = (i + 1) + ". " + filteredList.get(i) + "\n";
            reply.append(taskDescription);
        }
        return reply.toString();
    }
}
