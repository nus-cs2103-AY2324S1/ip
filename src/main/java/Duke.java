import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static TaskList taskList = new TaskList();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(taskList.toString());
            } else {
                taskList.add(input);
                System.out.println("added: " + input);
            }

        }
        exit();
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
