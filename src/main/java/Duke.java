import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static Scanner prompt = new Scanner(System.in);
    private static List taskList = new List();
    public static void main(String[] args) {
        greet();
    }

    public static void greet() {
        System.out.println("Hello! I'm Oranges.");
        System.out.println("What can I do for you?");
        echo();
    }

    public static void echo() {
        String promptText = prompt.nextLine();
        if (promptText.equals("bye")) {
            exit();
        }
        else if (promptText.equals("list")) {
            list();
        }
        else if (promptText.startsWith("mark") || promptText.startsWith("unmark")) {
            markTask(promptText);
        }
        else {
            Task t = new Task(promptText);
            taskList.add(t);
        }
        echo();
    }

    public static void markTask(String promptText) {
        int i = Integer.parseInt(promptText.substring(promptText.length() - 1));
        Task t = taskList.get(i-1);
        if (promptText.startsWith("unmark")) {
            t.unmark();
        }
        else {
            t.mark();
        }
    }



    public static void list() {
        taskList.list();
        echo();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
