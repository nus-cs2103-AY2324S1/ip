package duke;
import java.util.ArrayList;

//deals with interactions - display welcome/errors/messages/etc.
public class Ui {
    //this is the new name for my chatbot
    private static String name;
    //these are the lines
    private static String lines;
    //this will be the greeting
    private static String greeting;
    //this will be goodbye
    private static String goodbye;

    public Ui() {
        name = "dukey";
        lines = "        ____________________________________________________________";
        greeting = "        Hello! I'm " + name + "\n        What can I do for you?\n";
        goodbye = "        Bye. Hope to see you again soon! :D\n";
    }
    public static void helloMsg() {
        System.out.println(lines + "\n" + greeting + lines);
    }

    public static void goodbyeMsg() {
        System.out.println(lines + "\n" + goodbye + lines);
    }

    public static void listTasks() {
        System.out.println(lines + "\n        Here are the tasks in your list:\n");
        TaskList.listOut();
        System.out.println(lines);
    }

    public static void tasksNumberError() {
        System.out.println(lines
                + "\n        You don't have that many tasks :(\n"
                + lines);
    }

    public static void markMsg(String description) {
        System.out.println(lines
                    + "\n        Nice! I've marked this task as done:\n          "
                    + description + "\n"
                    + lines);
    }

    public static void unmarkMsg(String description) {
        System.out.println(lines
                    + "\n        OK, I've marked this task as not done yet:\n          "
                    + description + "\n"
                    + lines);
    }

    public static void successfulAdd(String type, String description, Integer size) {
        System.out.println(lines + "\n         Got it. I've added this new " + type + ":\n            "
                + description + "\n         Now you have " + size + " tasks in the list.\n" + lines);
    }

    public static void successfulDelete(String description, Integer size) {
        System.out.println(lines + "\n         Noted, I've removed this task:\n              "
                + description + "\n         Now you have " + size + " tasks in the list"
                + "\n" + lines);
    }
}
