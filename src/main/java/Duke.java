import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();

            if ("bye".equalsIgnoreCase(userInput)) {
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}

