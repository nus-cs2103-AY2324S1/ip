import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String chatbot = "War Room";
    public static void main(String[] args) {
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String user_input = scanner.nextLine();
            if (Objects.equals(user_input, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else {
                System.out.println(user_input);
            }
        }
    }
}
