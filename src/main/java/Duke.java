import java.util.Scanner;

public class Duke {

    private static Scanner prompt = new Scanner(System.in);
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
        System.out.println("\t" + promptText);
        echo();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
