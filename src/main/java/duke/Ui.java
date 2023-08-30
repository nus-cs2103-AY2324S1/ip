package duke;

import duke.task.Task;
import duke.task.Tasklist;

public class Ui {
    private static final String name = "Bot";
    private static final String greeting = "Hello I'm " + name + "\nWhat can I do for you?";
    private static final String end = "Bye bye";
    private static final String mark = "mark";
    private static final String unmark = "unmark";
    private static final String bye = "bye";
    private static final String list = "list";
    private static final String delete = "delete";
    public static void greet() {
        System.out.println(greeting);
    }

    public static void addedTask(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
    }

    public static void printList(Tasklist t) {
        System.out.println("Here are the tasks in your list:");
        t.printlist();
    }

    public static void exit() {
        System.out.println(end);
    }

    public static void add(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    public static void delete(Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void mark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());
    }

    public static void unmark(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
    }

    public static void find(String s) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(s);
    }

}
