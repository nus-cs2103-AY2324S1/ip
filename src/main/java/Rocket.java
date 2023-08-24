import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rocket {
    public static void main(String[] args) {
        String LINE = "    ____________________________________________________________";
        // Initialise string variable to store command
        String command;
        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Create list
        List<String> commandList = new ArrayList<>();
        System.out.println(LINE + "\n    Hello! I'm Rocket\n" +
                "    What can I do for you?\n" + LINE);
        command = scanner.nextLine();
        while (true) {
            if (command.equals("bye")) {
                System.out.println(LINE + "\n    Bye. Hope to see you again soon!\n" + LINE);
                break;
            } else if (command.equals("list")) {
                System.out.println(LINE);
                for (int i = 0; i < commandList.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + commandList.get(i));
                }
                System.out.println(LINE);
                command = scanner.nextLine();
            } else {
                System.out.println(LINE + "\n    added: " + command + "\n" + LINE);
                commandList.add(command);
                command = scanner.nextLine();
            }
        }



    }
}
