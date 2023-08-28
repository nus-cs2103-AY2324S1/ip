import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Represents a list of tasks in the Duke application.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Mark a specific task in the list as done.
     * @param taskNumber The index of the task to be marked as done.
     */
    public void mark(int taskNumber) {
        Task task = taskList.get(taskNumber);
        task.mark();
        System.out.println(task.toString());
    }

    /**
     * Mark a specific task in the list as not done.
     * @param taskNumber The index of the task to be marked as not done.
     */
    public void unmark(int taskNumber) {
        Task task = taskList.get(taskNumber);
        task.unmark();
        System.out.println(task.toString());
    }

    /**
     * Delete a specific task from the list.
     * @param taskNumber The index of the task to be deleted.
     */
    public void delete(int taskNumber) {
        Task task = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        System.out.println("Noted. I've removed this task:\n" + task);
        message();
    }

    public void saveFile() throws IOException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileWriter file = new FileWriter("data/tasks.txt");
        file.write(toString());
        file.close();
    }

    /**
     * Returns a string representation of the task list.
     * @return A string representing all tasks in the list.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return result;
    }
    public void message() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}