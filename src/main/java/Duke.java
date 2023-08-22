import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String chatbot = "War Room";
    private static String[] userData = new String[100];
    private static int index = 0;

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
            } else if (Objects.equals(user_input, "list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println((i+1) + "." + " " + userData[i]);
                }
            } else {
                userData[index] = user_input;
                index++;
                System.out.println("added: " + user_input);
            }
        }
    }
}
