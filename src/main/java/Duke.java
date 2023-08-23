import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
        int number = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I am TaskMaster!");
        System.out.println("What can I do for you today?");
        System.out.println("____________________________________________________________");

        boolean loggedIn = true;

        while (loggedIn) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                loggedIn = false;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("List:");
                for (int i = 0; i < number; i ++ ) {
                    System.out.println("Task " + i + ": " + taskList.get(i) + "\n");
                }
            } else {
                taskList.add(userInput);
                number++;
                System.out.println("Item added: " + userInput + "\n");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}
