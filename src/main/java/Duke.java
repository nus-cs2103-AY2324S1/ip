import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "────────────────────────────────────────────────────────────────────\n";
        String logo = " ___  _ _                  ___  _          _    ___        _    ___ \n"
                + "|  _|| | | ___  _ _  _ _  |  _>| |_  ___ _| |_ | . > ___ _| |_ |_  |\n"
                + "| |  \\   // . \\| | || '_> | <__| . |<_> | | |  | . \\/ . \\ | |    | |\n"
                + "| |_  |_| \\___/`___||_|   `___/|_|_|<___| |_|  |___/\\___/ |_|   _| |\n"
                + "|___|                                                          |___|\n";

        String greet = line +
                "Hello! I'm\n"
                + logo
                + "What can I do for you?\n"
                + line;
        System.out.println(greet);
        Scanner input = new Scanner(System.in);
        while (true) {
            String userInput = input.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else {
                System.out.println(line + userInput + "\n"+ line);
            }
        }

        String sendOff = line
                + "Bye. Hope to see you again soon!\n"
                + line;
        System.out.println(sendOff);
    }
}