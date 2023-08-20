package Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task> {

    // Parent constructor is (implicitly) used

    /**
     * Returns the string representation of TaskList
     * @return the string representation of TaskList
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.size(); i++){
            String currTask = this.get(i).toString();
            //this.get(i) is of type Task, so we rely on the string rep of that
            String newLine = String.format(
                    "%d. %s\n", i+1 , currTask);
            // User probably expects to start counting from 1 instead of 0
            // so need to add 1 here
            out.append(newLine);
        }
        return out.toString();
    }

    public boolean addTask(String taskName) {
        return this.add(Task.newTask(taskName));
    }


}
