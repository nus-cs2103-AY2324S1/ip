import java.lang.reflect.Array;
import java.util.ArrayList;

public class Printing {
    public static void printBlock() {
        System.out.println("===---===---===---===---===---===---===---===");
    }

    public static void greeting() {
        Printing.printBlock();
        System.out.println("Hello I'm Anto\nWhat can I do for you?");
        Printing.printBlock();
    }

    public static void bye() {
        Printing.printBlock();
        System.out.println("Bye. Hope to see you again soon!");
        Printing.printBlock();
    }

    public static void list() {
        ArrayList<Task> storage = Storage.getStorage();
        int length = Storage.getLength();
        Printing.printBlock();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < length; i++) {
            System.out.println(String.format("%d. %s",
                    i + 1, storage.get(i)));
        }
        Printing.printBlock();
    }

    public static void printMarkAsDone(int index) {
        ArrayList<Task> storage = Storage.getStorage();
        Printing.printBlock();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(storage.get(index));
        Printing.printBlock();
    }

    public static void printUnmark(int index) {
        ArrayList<Task> storage = Storage.getStorage();
        Printing.printBlock();
        System.out.println("Okay, I've marked this task as not done yet:");
        System.out.println(storage.get(index));
        Printing.printBlock();
    }

    public static void printAdded(Task task) {
        Printing.printBlock();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", Storage.getLength()));
        Printing.printBlock();
    }

    public static void printError(DukeException e) {
        Printing.printBlock();
        System.out.println(e.getMessage());
        Printing.printBlock();
    }

    public static void printDelete(Task task) {
        Printing.printBlock();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", Storage.getLength()));
        Printing.printBlock();
    }

    public static void printNoTasks() {
        Printing.printBlock();
        System.out.println("Sorry, you currently have no tasks on the list.");
        Printing.printBlock();
    }

    public static void printNoSavedFile() {
        Printing.printBlock();
        System.out.println("No saved file found.");
        Printing.printBlock();
    }

    public static void printSavedFileFound() {
        Printing.printBlock();
        System.out.println("Saved file found.");

        ArrayList<Task> storage = Storage.getStorage();
        int length = Storage.getLength();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < length; i++) {
            System.out.println(String.format("%d. %s",
                    i + 1, storage.get(i)));
        }

        Printing.printBlock();
    }
}