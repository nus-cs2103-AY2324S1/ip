import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> tasklist;

    TaskList() {
        this.tasklist = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    private static boolean isInteger(String taskNumber) {
        for (int i = 0; i < taskNumber.length(); i++) {
            if (!Character.isDigit(taskNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }



    void add(Task task) {
        this.tasklist.add(task);
    }

    int size() {
        return this.tasklist.size();
    }




    // adding task into tasklist and output the relevant information
    void addToList(Task task) {
        this.tasklist.add(task);
        System.out.println("Got it. I've added this task:\n"
                + task.toString() + "\nNow you have "
                + tasklist.size() + " tasks in the list.");

    }
    // getTaskIndex checks if the input is valid and
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
    void mark(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        Task markedTask = this.tasklist.get(taskIndex).done();
        System.out.println("Nice! I've marked this task as done:\n" + markedTask.toString());
        tasklist.set(taskIndex, markedTask);
    }

    void mark(int taskIndex) {
        Task markedTask = this.tasklist.get(taskIndex).done();
        tasklist.set(taskIndex, markedTask);
    }

    void unMark(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        Task unmarkedTask = this.tasklist.get(taskIndex).undone();
        System.out.println("OK, I've marked this task as not done yet:\n" + unmarkedTask.toString());
        tasklist.set(taskIndex, unmarkedTask);
    }

    void delete(String taskNumber) throws DukeException {
        int taskIndex = getTaskIndex(taskNumber);
        Task removedTask = tasklist.remove(taskIndex);
        System.out.println("Noted. I've removed this task:\n" +
                removedTask.toString() +
                "\nNow you have " + tasklist.size() + " tasks in the list.");
    }

    void listContent() {
        if (tasklist.isEmpty()) {
            System.out.println("Oops! It seems you do not have anything in your task list");
        }
        for (int i = 0; i < tasklist.size(); i++) {
            System.out.println((i + 1) + ": " + tasklist.get(i).toString());
        }
    }



}
