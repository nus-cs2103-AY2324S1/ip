import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final String botName = "cc";

    private static final ArrayList<String> memory = new ArrayList<>();

    private static void greet() {
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String input) {
        System.out.println(input);
    }
    public static void main(String[] args) {
        Duke.greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (input != null) {
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < memory.size(); i++) {
                    int order = i + 1;
                    System.out.println(order + ". " + memory.get(i));
                }
                input = scanner.nextLine();
            } else {
                System.out.print("added: ");
                echo(input);
                memory.add(input);
                input = scanner.nextLine();
            }
        }
        Duke.bye();
    }
}
