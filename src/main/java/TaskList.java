import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaskList {

    private List<Task> list;

    public TaskList (List<Task> taskList) {
        this.list = taskList;
    }

    /** Marks the task input by users.
     *
     * @param index Index of task to be marked.
     * */
    public void mark(int index) {
        try {
            this.list.get(index).markAsDone();
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
        }
    }

    /**
     * Unmarks the task input by user.
     *
     * @param index Index of task to be unmarked.
     */
    public void unmark(int index) {
        try {
            this.list.get(index).markAsUndone();
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalArgumentException("OOPS!!! I could not find any task in that position.");
        }
    }

    /** Stores the task into the task list.
     *
     * @param task Task to be added to list.
     */
    public void store(Task task) {
        this.list.add(task);
    }

    /** Removes the task specified by the index inputed.
     *
     * @param index Position of the task that is to be removed.
     */
    public void delete(int index) {
        this.list.remove(index);
    }

    /** Get the task at the specific index of the list.
     *
     * @param index Position of the task in the list.
     * @return The task at the index position.
     */
    public Task retrieve(int index) {
        return this.list.get(index);
    }

    /** Returns length of the list.
     *
     * @return Length of list.
     */
    public int length() {
        return this.list.size();
    }

    /** Prints out the list of tasks stored. */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int index = 0; index < this.list.size(); index++) {
            Task item = this.list.get(index);
            System.out.println((index + 1) + ". " + item.toString());
        }
    }

    /** Goes through all task stored in list and updates the hard drive. */
    public void updateStorage(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task: this.list) {
                String description = task.getStorageDescription();
                writer.write(description + "\n");
            }
            writer.close();
        } catch (IOException error) {
            throw new IllegalArgumentException("Oops! Something went wrong!");
        }
    }
}
