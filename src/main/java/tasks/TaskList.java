package tasks;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import components.DukeException;
import components.Storage;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    /**
     * Prints the list of tasks in the TaskList.
     */
    public String printList() {
        String result = IntStream.range(0, this.size())
                .mapToObj(i -> (i + 1) + ". " + this.get(i))
                .collect(Collectors.joining("\n"));

        assert this.size() == result.split("\n").length : "Number of tasks should equal number of lines";

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
            assert this.get(index - 1).isDone() : "Task should be marked as done";
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
        assert !this.get(index - 1).isDone() : "Task should be marked as undone";

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
        int initialSize = this.size();
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.remove(index - 1);
            return null;
        }

        try {
            storage.deleteLine(index);
            assert this.size() == initialSize - 1 : "Task list size should decrease by 1 after deletion";
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
        int initialSize = this.size();
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.add(task);
            return null;
        }

        try {
            storage.writeData(task.toString());
            assert this.size() == initialSize + 1 : "Task list size should increase by 1 after adding a task";
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

    public Task findDuplicateTask(String description) {
        for (Task t : this) {
            if (t.getDescription().equals(description)) {
                return t;
            }
        }
        return null;
    }
}
