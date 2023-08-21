import java.util.Scanner;

public class Sara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Sara");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine();

            System.out.println("    ____________________________________________________________");

            if ("bye".equalsIgnoreCase(userInput)) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else {
                System.out.println("     " + userInput);
                System.out.println("    ____________________________________________________________");
            }
        }

        scanner.close();
    }
}