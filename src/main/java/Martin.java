import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Martin {
    private static List<String> addList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printMessage("Hello! I'm Martin\n     What can I do for you?");
        
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printTasks();
            } else {
                addList.add(input);
                printMessage("added: " + input);
            }
        }
    }

    private static void printMessage(String message) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + message);
            System.out.println("    ____________________________________________________________");
    }

    private static void printTasks() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < addList.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + addList.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }
}
