import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String line = "____________________________________________________________";

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Fong!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void handleCommand(String command) {
        System.out.println(line);
        System.out.println(command);
        System.out.println(line);

        return;
    }

    public void acceptCommands() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            this.handleCommand(command);

            command = scanner.nextLine();
        }

        scanner.close();

        this.bye();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.greet();
        chatBot.acceptCommands();
    }
}
