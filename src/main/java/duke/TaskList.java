package duke;

import duke.DukeException;

import java.util.ArrayList;



public class TaskList {
    private ArrayList<Task> tasklist;
    private static final Storage STORAGE = new Storage();
    // create a list of tasks
    public TaskList() {
        this.tasklist = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasklist = tasks;
    }

    private static boolean isInteger(String taskNumber) {
        for (int i = 0; i < taskNumber.length(); i++) {
            if (!Character.isDigit(taskNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // adding task into tasklist and output the relevant information
    public String addToList(Task task) {
        this.tasklist.add(task);
        return ("Got it. I've added this task:\n"
                + task.toString() + "\nNow you have "
                + tasklist.size() + " tasks in the list.\n");

    }

    // check if the input is valid and
    // returns the index of the task to be marked or unmarked
    private int getTaskIndex(String taskNumber) throws DukeException {
        if (!isInteger(taskNumber)) {
            throw new DukeException("Number not found: Please enter a valid task number");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;

        if (taskIndex >= tasklist.size()) {
            throw new DukeException("Task number out of range: please enter a valid task number");
        }
        return taskIndex;
    }

    // mark a task given the task number and store it
    public String mark(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        Task markedTask = this.tasklist.get(taskIndex).done();
        tasklist.set(taskIndex, markedTask);
        STORAGE.writeAllToFile(tasklist);
        return ("Nice! I've marked this task as done:\n" + markedTask.toString() + "\n");
    }


    public String unMark(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        Task unmarkedTask = this.tasklist.get(taskIndex).undone();
        tasklist.set(taskIndex, unmarkedTask);
        STORAGE.writeAllToFile(tasklist);
        return ("OK, I've marked this task as not done yet:\n" + unmarkedTask.toString() + "\n");
    }

    public String delete(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        Task removedTask = tasklist.remove(taskIndex);
        STORAGE.writeAllToFile(tasklist);
        return ("Noted. I've removed this task:\n"
                + removedTask.toString()
                + "\nNow you have " + tasklist.size()
                + " tasks in the list.\n");


    }

    public String listContent() {
        String listOfContents = "";
        if (tasklist.isEmpty()) {
            return ("Oops! It seems you do not have anything in your task list");
        }
        for (int i = 0; i < tasklist.size(); i++) {
            listOfContents = listOfContents + ((i + 1) + ": " + tasklist.get(i).toString() + "\n");
        }
        return listOfContents;
    }

}
