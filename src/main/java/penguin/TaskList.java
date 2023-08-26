package penguin;

import java.util.List;
import java.util.ArrayList;
/**
 * TaskList is a list of Tasks.
 *
 */
public class TaskList {
    protected List<Task> list;

    /**
     * Constructor of List.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }
    /**
     * Returns information about all tasks in the list for output to the user.
     *
     * @return Information about all tasks in the list in user-output format.
     */
    public String printList() {
        String output = "I'm remembering the following tasks...\n";
        for (int i=1; i<=this.list.size(); i++) {
            output += i + ".";
            output += this.list.get(i-1).getDisplay() + "\n";
        }
        return output;
    }


}
