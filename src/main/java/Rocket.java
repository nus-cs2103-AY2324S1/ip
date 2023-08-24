import java.util.Scanner;

public class Rocket {
    public static void main(String[] args) {
        String LINE = "    ____________________________________________________________";
        // Initialise string variable to store command
        String command;
        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Create list
        String[] commandList = new String[100];
        System.out.println(LINE + "\n    Hello! I'm Rocket\n" +
                "    What can I do for you?\n" + LINE);
        command = scanner.nextLine();
        while (true) {
            if (command.equals("bye")) {
                System.out.println(LINE + "\n    Bye. Hope to see you again soon!\n" + LINE);
                break;
            } else if (command.equals("list")){
                System.out.println(LINE + "\n    list\n" + LINE);
                command = scanner.nextLine();
            } else {
                System.out.println(LINE + "\n    added: " + command + "\n" + LINE);
                command = scanner.nextLine();
            }
        }



    }
}
