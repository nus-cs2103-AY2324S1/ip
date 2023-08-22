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
        //System.out.println("\t" + promptText);
        taskList.add(promptText);
        echo();
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
