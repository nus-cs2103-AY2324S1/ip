package anto;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Ui handles all printing to command line.
 */
public class Ui {

    private TaskList taskList;

    /**
     * Creates a Ui class.
     */
    public Ui() {}

    /**
     * Sets Ui's taskList to the given one.
     *
     * @param taskList TaskList to set it to.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints a border.
     */
    public void printBlock() {
        System.out.println("===---===---===---===---===---===---===---===");
    }

    /**
     * Greets the user.
     */
    public String greet() {
        return "Hello I'm Anto\n" +
                "What can I do for you?";
    }

    /**
     * Says bye to the user.
     */
    public String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints current list of tasks.
     */
    public String printList() {
        ArrayList<Task> storage = taskList.getTaskArrayList();
        int length = taskList.getLength();

        ArrayList<String> stringOutputArr = new ArrayList<>();
        stringOutputArr.add("Here are the tasks in your list:");

        for (int i = 0; i < length; i++) {
            stringOutputArr.add(String.format("%d. %s",
                    i + 1, storage.get(i)));
        }

        return String.join("\n", stringOutputArr);
    }

    /**
     * Tells user task is deleted.
     *
     * @param index Index of deleted task.
     */
    public String printMarkAsDone(int index) {
        ArrayList<Task> storage = taskList.getTaskArrayList();

        return String.format("Nice! I've marked this task as done:\n" +
                        "%s",
                storage.get(index));
    }

    /**
     * Tells user task is unmarked.
     *
     * @param index Index of task unmarked.
     */
    public String printUnmark(int index) {
        ArrayList<Task> storage = taskList.getTaskArrayList();

        return String.format("Okay, I've marked this task as not done yet:\n" +
                        "%s",
                storage.get(index));
    }

    /**
     * Tells user task is added.
     *
     * @param task Task that was added.
     */
    public String printAdded(Task task) {
        return String.format("Got it. I've added this task:\n" +
                        "%s\n" +
                        "Now you have %d tasks in the list.",
                task,
                taskList.getLength());
    }

    /**
     * Tells user there is an exception.
     *
     * @param e AntoException that was thrown.
     */
    public String printError(AntoException e) {
        return e.getMessage();
    }

    /**
     * Tells user task is deleted.
     *
     * @param task Task that was deleted.
     */
    public String printDelete(Task task) {
        return String.format("Noted. I've removed this task:\n" +
                        "%s\n" +
                        "Now you have %d tasks in the list.",
                task,
                taskList.getLength());
    }

    /**
     * Tells user there are no current tasks on the list.
     */
    public String printNoTasks() {
        return "Sorry, you currently have no tasks on the list.";
    }

    /**
     * Tells user there is no saved filed.
     */
    public void printNoSavedFile() {
        this.printBlock();
        System.out.println("No saved file found.");
        this.printBlock();
    }

    /**
     * Tells user saved file is found and loaded.
     *
     * @param taskArrayList Current array list of tasks.
     */
    public void printSavedFileFound(ArrayList<Task> taskArrayList) {
        this.printBlock();
        System.out.println("Saved file found.");

        int length = taskArrayList.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < length; i++) {
            System.out.println(String.format("%d. %s",
                    i + 1, taskArrayList.get(i)));
        }

        this.printBlock();
    }

    /**
     * Prints list of tasks found.
     *
     * @param foundTasks List of tasks found.
     */
    public String printFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.size() == 0) {
            return "There are no matching tasks in your list.";
        }

        ArrayList<String> stringOutputArr = new ArrayList<>();
        stringOutputArr.add("Here are the matching tasks in your list:");

        int length = foundTasks.size();

        for (int i = 0; i < length; i++) {
            stringOutputArr.add(String.format("%d. %s",
                    i + 1, foundTasks.get(i)));
        }

        return String.join("\n", stringOutputArr);
    }
}
