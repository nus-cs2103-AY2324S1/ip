package penguin;

import java.util.List;
import java.util.ArrayList;

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

    public String printList() {
        String output = "I'm remembering the following tasks...\n";
        for (int i=1; i<=this.list.size(); i++) {
            output += i + ".";
            if (this.list.get(i-1).done) {
                output += "[X]";
            } else {
                output += "[ ]";
            }
            output += this.list.get(i-1).getName() + "\n";
        }
        return output;
    }


}
