import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputHistory = new ArrayList<>();

        System.out.println("Hello! I'm Gideon");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                for(int i = 0; i < inputHistory.size(); i++) {
                    System.out.println((i + 1) + ". " + inputHistory.get(i));
                }
            } else {
                System.out.println("added: " + userInput);
                inputHistory.add(userInput);
            }
        }
        scanner.close();
    }
}
