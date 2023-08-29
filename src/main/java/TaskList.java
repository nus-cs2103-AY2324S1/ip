import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the operation on the tasks
 */
public class TaskList {
    /**
     * Stores all the task
     */
    private ArrayList<Task> tasks;


    /**
     * construct a new empty task list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public boolean loadTasks(List<String> storedInput) {


        // parse and store data while looking out for data corruption
        boolean corrupted = false;
        for (String s : storedInput) {
            try {
                Task newTask = Parser.fromStorage(s);
                this.tasks.add(newTask);
            } catch (DukeLoadingException | DateTimeParseException e) {
                corrupted = true;
            }
        }
        return corrupted;
    }

    /**
     * returns the current task list length
     *
     * @return task list length
     */
    public int length() {
        return this.tasks.size();
    }

    /**
     * Adds the given task into memory
     *
     * @param task - the task being added
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * remove the given task from memory
     *
     * @param index - the index of the task being removed
     * @return the task that was removed
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * returns the element at the index
     *
     * @param index - index of the element
     * @return the element at the index
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }


    public Task[] getAllTask() {
        return this.tasks.toArray(new Task[this.length()]);
    }
}
