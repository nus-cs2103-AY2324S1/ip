package storage;

import java.io.Serializable;
import java.util.ArrayList;

import task.Task;




/**
 * Class encapsulating a list of Tasks, supports easy string representation, refer to Task for more details about
 * what Tasks are.
 */
public class TaskList extends ArrayList<Task> implements Serializable {

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
                    "%d. %s\n", i + 1 , currTask.toPrintString());
            // User probably expects to start counting from 1 instead of 0
            // so need to add 1 here
            out.append(newLine);
        }
        return out.toString();
    }

    /**
     * Marks a given task specified by index as done
     * @param index the index of the task to mark as done
     * @return the task that was unmarked
     */
    public Task markAsUndone(int index) {

        this.get(index).markAsUndone();
        return this.get(index);
    }

    /**
     * Marks the given task specified by index as not done
     * @param index the index of the task marked as not done
     * @return the task that was marked
     */
    public Task markAsDone(int index) {

        this.get(index).markAsDone();
        return this.get(index);
    }


    /**
     * Adds a tag to the task at the provided index
     * @param index the index of the task
     * @param tag the tag to add
     * @return the task that was tagged
     */
    public Task addTag(int index, String tag) {
        this.get(index).addTag(tag);
        return this.get(index);
    }




}
