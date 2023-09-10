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
    public void printList() {
        System.out.println(Ui.LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + ". " + this.get(i));
        }
        System.out.println(Ui.LINE);
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     * @param storage The Storage object to write to.
     * @param isFromDatabase A boolean to indicate if the task is from the database.
     * @throws DukeException If the index is out of bounds.
     */
    public void markAsDone(Integer index, Storage storage, boolean... isFromDatabase) throws DukeException{   

        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.get(Integer.valueOf(index - 1)).markAsDone();
            return;
        }

        if (index == 0) {
            throw new DukeException("Mark command must be followed by a space and an integer. ERR: NO INTEGER.");
        } 
            
        try {
            this.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at that index.");
        }

        System.out.println(Ui.LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.get(index - 1));
        System.out.println(Ui.LINE);

        try {
            storage.replaceLine(index, this.get(index - 1).toString());
        } catch (DukeException e) {
            System.out.println(Ui.LINE);
            System.out.println(e);
            System.out.println(Ui.LINE);
        }
    }

    /**
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     * @param storage The Storage object to write to.
     * @param isFromDatabase A boolean to indicate if the task is from the database.
     */
    public void markAsUndone(int index, Storage storage, boolean... isFromDatabase) {
        
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.get(index - 1).markAsUndone();
            return;
        }

        System.out.println(Ui.LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        this.get(index - 1).markAsUndone();

        try {
            storage.replaceLine(index, this.get(index - 1).toString());
        } catch (DukeException e) {
            System.out.println(Ui.LINE);
            System.out.println(e);
            System.out.println(Ui.LINE);
        }
        System.out.println(Ui.LINE);
    }

    /**
     * Deletes a task from the TaskList.
     * @param index The index of the task to be deleted.
     * @param storage The Storage object to write to.
     * @param isFromDatabase A boolean to indicate if the task is from the database.
     */
    public void deleteTask(int index, Storage storage, boolean... isFromDatabase) {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.remove(index - 1);
            return;
        }

        System.out.println(Ui.LINE);
        System.out.println("Noted. I've removed this task:");
        Task t = this.remove(index - 1);
        System.out.println(" " + t);
        System.out.println("Now you have " + Integer.toString(this.size()) + " " + (this.size() == 1 ? "task" : "tasks") + " in the list.");

        try {
            storage.deleteLine(index);
        } catch (DukeException e) {
            System.out.println(Ui.LINE);
            System.out.println(e);
            System.out.println(Ui.LINE);
        }
        System.out.println(Ui.LINE);
    }


    public void addTask(Task task, Storage storage, boolean... isFromDatabase) {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.add(task);
            return;
        }

        System.out.println(Ui.LINE);
        System.out.println("Got it. I've added this task:");
        this.add(task);
        System.out.println(" " + task);
        System.out.println("Now you have " + Integer.toString(this.size()) + " " + (this.size() == 1 ? "task" : "tasks") + " in the list.");
        try {
            storage.writeData(task.toString());
        } catch (DukeException e) {
            System.out.println(Ui.LINE);
            System.out.println(e);
            System.out.println(Ui.LINE);
        }
        System.out.println(Ui.LINE);
    }
}
