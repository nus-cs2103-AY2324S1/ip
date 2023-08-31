package anto;

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
    public void greet() {
        this.printBlock();
        System.out.println("Hello I'm Anto\nWhat can I do for you?");
        this.printBlock();
    }

    /**
     * Says bye to the user.
     */
    public void sayBye() {
        this.printBlock();
        System.out.println("Bye. Hope to see you again soon!");
        this.printBlock();
    }

    /**
     * Prints current list of tasks.
     */
    public void printList() {
        ArrayList<Task> storage = taskList.getStorage();
        int length = taskList.getLength();
        this.printBlock();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < length; i++) {
            System.out.println(String.format("%d. %s",
                    i + 1, storage.get(i)));
        }
        this.printBlock();
    }

    /**
     * Tells user task is deleted.
     *
     * @param index Index of deleted task.
     */
    public void printMarkAsDone(int index) {
        ArrayList<Task> storage = taskList.getStorage();
        this.printBlock();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(storage.get(index));
        this.printBlock();
    }

    /**
     * Tells user task is unmarked.
     *
     * @param index Index of task unmarked.
     */
    public void printUnmark(int index) {
        ArrayList<Task> storage = taskList.getStorage();
        this.printBlock();
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(storage.get(index));
        this.printBlock();
    }

    /**
     * Tells user task is added.
     *
     * @param task Task that was added.
     */
    public void printAdded(Task task) {
        this.printBlock();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.getLength()));
        this.printBlock();
    }

    /**
     * Tells user there is an exception.
     *
     * @param e AntoException that was thrown.
     */
    public void printError(AntoException e) {
        this.printBlock();
        System.out.println(e.getMessage());
        this.printBlock();
    }

    /**
     * Tells user task is deleted.
     *
     * @param task Task that was deleted.
     */
    public void printDelete(Task task) {
        this.printBlock();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.getLength()));
        this.printBlock();
    }

    /**
     * Tells user there are no current tasks on the list.
     */
    public void printNoTasks() {
        this.printBlock();
        System.out.println("Sorry, you currently have no tasks on the list.");
        this.printBlock();
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
}
