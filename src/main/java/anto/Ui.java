package anto;

import java.util.ArrayList;

public class Ui {

    private TaskList taskList;
    public Ui() {}

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
    public void printBlock() {
        System.out.println("===---===---===---===---===---===---===---===");
    }

    public void greet() {
        this.printBlock();
        System.out.println("Hello I'm Anto\nWhat can I do for you?");
        this.printBlock();
    }

    public void sayBye() {
        this.printBlock();
        System.out.println("Bye. Hope to see you again soon!");
        this.printBlock();
    }

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

    public void printMarkAsDone(int index) {
        ArrayList<Task> storage = taskList.getStorage();
        this.printBlock();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(storage.get(index));
        this.printBlock();
    }

    public void printUnmark(int index) {
        ArrayList<Task> storage = taskList.getStorage();
        this.printBlock();
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(storage.get(index));
        this.printBlock();
    }

    public void printAdded(Task task) {
        this.printBlock();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.getLength()));
        this.printBlock();
    }

    public void printError(DukeException e) {
        this.printBlock();
        System.out.println(e.getMessage());
        this.printBlock();
    }

    public void printDelete(Task task) {
        this.printBlock();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.getLength()));
        this.printBlock();
    }

    public void printNoTasks() {
        this.printBlock();
        System.out.println("Sorry, you currently have no tasks on the list.");
        this.printBlock();
    }

    public void printNoSavedFile() {
        this.printBlock();
        System.out.println("No saved file found.");
        this.printBlock();
    }

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
