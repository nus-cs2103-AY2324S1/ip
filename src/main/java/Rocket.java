import java.util.Scanner;

public class Rocket {
    public static void main(String[] args) {
        String LINE = "    ____________________________________________________________";
        // Initialise string variable to store command
        String command;
        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        System.out.println(LINE + "\n    Hello! I'm Rocket\n" +
                "    What can I do for you?\n" + LINE);
        command = scanner.nextLine();
        System.out.println(LINE + "\n    " + command + "\n" + LINE);
        System.out.println(LINE + "\n    Bye. Hope to see you again soon!\n" + LINE);


    }
}
