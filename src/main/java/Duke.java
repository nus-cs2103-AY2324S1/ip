import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> tasks = new ArrayList<String>();
    private static void echo(String input) {
        tasks.add(input);
        System.out.println("added: " + input);
    }
    private static void listTask() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm BaekBot. \nWhat can I do for you?");
        while (true) {
            String echoInput = scanner.nextLine();
            if (echoInput.equals("bye")) {
                break;
            } else if (echoInput.equals("list")) {
                listTask();
            } else {
                echo(echoInput);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
