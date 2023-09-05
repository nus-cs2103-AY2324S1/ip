package Duke.Tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Duke.*;
import Duke.Exceptions.*;

public class TaskList {

    private ArrayList<Task> storagePile;

    /**
     * Constructs a TaskList and loads tasks from storage.
     */
    public TaskList() {
        storagePile = Storage.loadTasks();
    }

    /**
     * Constructs an empty TaskList.
     *
     * @param test just to set it off.
     */
    public TaskList(String test) {
        storagePile = new ArrayList<>();
    }

    public String toString() {
        int leng = storagePile.size();
        String listed = "";
        for (int i = 1; i <= leng; i++) {
            listed += String.format("%s - %s",
                    i, storagePile.get(i-1)) +" \n" ;
        }
        return listed;
    }

    public void checkItem(int x) {
        storagePile.get(x-1).markDone();
    }

    public void notDoneItem(int x) {
        storagePile.get(x-1).markUndone();
    }

    public void deleteIndex(int x) {
        storagePile.remove(x-1);
    }

    public Task getTask(int x) { return storagePile.get(x-1); }

    public int numOfItems() {
        return storagePile.size();
    }

    /**
     * Adds a task to the TaskList based on user input.
     *
     * @param item The user input representing a task.
     * @throws InvalidInput    If the input is invalid.
     * @throws IncompleteInput If the input is incomplete.
     */
    public void input(String item) throws InvalidInput, IncompleteInput  {
        String firstWord = item.split(" ")[0];

        if (item.split(" ").length == 1) {
            if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                throw new IncompleteInput("Not finished");
            } else {
                throw new InvalidInput("Invalid");
            }
        } else if (firstWord.equals("todo")) {
            String task = item.split(" ", 2)[1];
            storagePile.add(new ToDoTask(task));
        } else if (firstWord.equals("deadline")) {
            String task = item.split(" ", 2)[1];
            storagePile.add(new DeadlineTask(task));
        } else {
            String task = item.split(" ", 2)[1];
            storagePile.add(new EventTask(task));
        }
    }

    /**
     * Filters the TaskList and returns a new TaskList containing tasks with descriptions that match the provided description.
     *
     * @param desc The description to filter tasks by.
     * @return A filtered TaskList containing matching tasks.
     */
    public TaskList filterTaskList(String desc) {
        TaskList filtered = new TaskList("empty");
        for (Task task : this.storagePile) {
            if (task.filterMatchDesc(desc.trim())) {
                filtered.storagePile.add(task);
            }
        }
        return filtered;
    }


    /**
     * Saves the tasks in the TaskList to a PrintWriter.
     *
     * @param pw The PrintWriter to write tasks to.
     */
    public void saveToFile(PrintWriter pw) {
        for (Task item : storagePile) {
            String str = item.toString();
            pw.println(str);
        }
    }

}