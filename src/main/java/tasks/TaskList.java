package tasks;
import java.util.ArrayList;

import components.DukeException;
import components.Storage;
import components.Ui;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    /**
     * Prints the list of tasks in the TaskList.
     */
    public String printList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            result.append((i + 1)).append(". ").append(this.get(i)).append("\n");
        }

        return "Here are the tasks in your list:" +
                "\n" + result;
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     * @param storage The Storage object to write to.
     * @param isFromDatabase A boolean to indicate if the task is from the database.
     * @throws DukeException If the index is out of bounds.
     */
    public String markAsDone(Integer index, Storage storage, boolean... isFromDatabase) throws DukeException {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.get(index - 1).markAsDone();
            return null;
        }

        if (index == 0) {
            throw new DukeException("Mark command must be followed by a space and an integer. ERR: NO INTEGER.");
        } 
            
        try {
            this.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at that index.");
        }

        try {
            storage.replaceLine(index, this.get(index - 1).toString());
        } catch (DukeException e) {
            return e.toString();
        }

        return "Nice! I've marked this task as done:" +
                "\n" +
                " " + this.get(index - 1);
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     * @param storage The Storage object to write to.
     * @param isFromDatabase A boolean to indicate if the task is from the database.
     */
    public String markAsUndone(int index, Storage storage, boolean... isFromDatabase) {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.get(index - 1).markAsUndone();
            return null;
        }

        this.get(index - 1).markAsUndone();

        try {
            storage.replaceLine(index, this.get(index - 1).toString());
        } catch (DukeException e) {
            return e.toString();
        }

        return "OK, I've marked this task as not done yet:" +
                "\n" +
                " " + this.get(index - 1);
    }

    /**
     * Deletes a task from the TaskList.
     * @param index The index of the task to be deleted.
     * @param storage The Storage object to write to.
     * @param isFromDatabase A boolean to indicate if the task is from the database.
     */
    public String deleteTask(int index, Storage storage, boolean... isFromDatabase) {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.remove(index - 1);
            return null;
        }

        try {
            storage.deleteLine(index);
        } catch (DukeException e) {
            return e.toString();
        }

        return "Noted. I've removed this task:" +
                "\n" +
                " " + this.remove(index - 1) +
                "\n" +
                "Now you have " +
                this.size() +
                " " + (this.size() == 1 ? "task" : "tasks") +
                " in the list.";
    }


    public String addTask(Task task, Storage storage, boolean... isFromDatabase) {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.add(task);
            return null;
        }

        try {
            storage.writeData(task.toString());
        } catch (DukeException e) {
            return e.toString();
        }

        this.add(task);

        return "Got it. I've added this task:" +
                "\n" +
                " " + task +
                "\n" +
                "Now you have " +
                this.size() +
                " " + (this.size() == 1 ? "task" : "tasks") +
                " in the list.";
    }
}
