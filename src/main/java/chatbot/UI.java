package chatbot;

import java.util.List;

public class UI {
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_WELCOME = "Hello! I'm Afro\n" + "What can I do for you?\n";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";
    private static final String MESSAGE_MARK = "OK, I've marked this task as done:";
    private static final String MESSAGE_ADD = "Got it. I've added this task:";
    private static final String MESSAGE_DELETE = "Noted. I have removed this task.";
    private static final String MESSAGE_INVALID = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    private Storage storage;

    public UI(Storage storage) {
        this.storage = storage;
    }

    public void startProgram() {
        System.out.println(MESSAGE_WELCOME);
        storage.load();
    }

    public void endProgram() {
        System.out.println(MESSAGE_GOODBYE);
    }

    public void printMarked(Task task) {
        System.out.println(MESSAGE_MARK);
        System.out.println(task);
    }

    public void printUnmarked(Task task) {
        System.out.println(MESSAGE_UNMARK);
        System.out.println(task);
    }

    public void addTask(Task task, int size) {
        System.out.println(MESSAGE_ADD);
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void deleteTask(Task task, int size) {
        System.out.println(MESSAGE_DELETE);
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void printStorageList(List<Task> tasks) {
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ":" + task);
        }
    }

    public void incompleteCommand(String str) {
        System.out.println(str);
    }

    public void invalidInput() {
        System.out.println(MESSAGE_INVALID);
    }
}