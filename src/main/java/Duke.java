import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private static String chatbot = "chuababyy chatbot";
    private static String line = "------------------------------------";

    public static void main(String[] args) {
        ArrayList fullList = new ArrayList();

        System.out.println(line);
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            }

            if (userInput.equals("list")) {
                for (int i = 1; i <= fullList.size(); i++) {
                    System.out.println(i + ". " + fullList.get(i - 1));
                }
                continue;
            }

            if (userInput.equals("")) {
                System.out.println(line);
                System.out.println("Item to be added cannot be empty");
                System.out.println(line);
                continue;
            }

            char lastChar = userInput.charAt(userInput.length() - 1);
            if (lastChar == ' ') {
                System.out.println(line);
                System.out.println("Last character should not be an empty space");
                System.out.println(line);
                continue;
            }

            fullList.add(userInput);
            System.out.println(line);
            System.out.println("added: " + userInput);
            System.out.println(line);
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);

        scanner.close();

    }
}
