package task;

import java.util.ArrayList;


/**
 * Class encapsulating a list of Tasks, supports easy string representation, refer to Task for more details about
 * what Tasks are.
 */
public class TaskList extends ArrayList<Task> {

    // Parent constructor is (implicitly) used

    /**
     * Returns the user-facing string representation of TaskList,
     * which is a numbered list of the user-facing string representation of each Task,
     * with a newline in between each entry
     * @return the string representation of TaskList
     */
    public String toPrintString() {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < this.size(); i++) {

            Task currTask = this.get(i);

            String newLine = String.format(
                    "%d. %s\n", i+1 , currTask.toPrintString());
            // User probably expects to start counting from 1 instead of 0
            // so need to add 1 here
            out.append(newLine);
        }
        return out.toString();
    }




}
