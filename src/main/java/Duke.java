import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
            System.out.println("Taskmaster: " + userInput + "\n");
            if (userInput.equals("bye")) {
                loggedIn = false;
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again!");
        System.out.println("____________________________________________________________");
    }
}
