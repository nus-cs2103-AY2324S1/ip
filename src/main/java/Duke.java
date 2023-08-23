import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "██╗░░░██╗██████╗░██████╗░░█████╗░██╗\n"
                    + "██║░░░██║██╔══██╗██╔══██╗██╔══██╗██║\n"
                    + "██║░░░██║██████╔╝██████╦╝██║░░██║██║\n"
                    + "██║░░░██║██╔══██╗██╔══██╗██║░░██║██║\n"
                    + "╚██████╔╝██║░░██║██████╦╝╚█████╔╝██║\n"
                    + "░╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Create an ArrayList to store user commands
        ArrayList<String> commands = new ArrayList<>(100);

        // Start an infinite loop to continuously read and process commands
        while (true) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("Your commands:");
                for (int i = 0; i < commands.size(); i++) {
                    System.out.println((i + 1) + ". " + commands.get(i));
                }
            } else {
                commands.add("added: " + command);
                System.out.println("added: " + command);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
