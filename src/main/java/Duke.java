import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Jarvis");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String command = userInput.nextLine();

        while (!command.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + command);
            System.out.println("    ____________________________________________________________");
            command = userInput.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
