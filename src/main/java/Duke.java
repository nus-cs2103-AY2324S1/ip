import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static final String DIVIDER = "      ____________________________________________________________";

    public static void main(String[] args) {
        // Opening lines
        System.out.println(DIVIDER);
        System.out.println("        Hello! I'm Valerie!");
        System.out.println("        What can I do for you?");
        System.out.println(DIVIDER);

        // Check user input
        Scanner sc = new Scanner(System.in);
        List<String> userList = new ArrayList<>();

        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                // If user wants to quit
                System.out.println(DIVIDER);
                System.out.println("        Bye ~ Hope to see you again soon ~");
                System.out.println(DIVIDER);
                break;

            } else if (userInput.equals("list")) {
                // If user wants to check list
                System.out.println(DIVIDER);
                System.out.println("        Sure! Here are the tasks in your list:");
                for (int i = 0; i < userList.size(); i++) {
                    String str = String.format("            %d. %s", i + 1, userList.get(i));
                    System.out.println(str);
                }
                System.out.println(DIVIDER);

            } else {
                // Other inputs
                userList.add(userInput);

                System.out.println(DIVIDER);
                System.out.println("        Added: " + userInput);
                System.out.println(DIVIDER);
            }
        }
    }
}