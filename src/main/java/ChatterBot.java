import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args) {

        String logo = "ChatterBot";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();

            if (userMessage.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(userMessage);
            }
        }
    }
}
