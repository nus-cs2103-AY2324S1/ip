import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String welcomeMessage = "──────────────────────────────────── \n" +
                                "Hello >u<! I'm OwO_bot \n" +
                                "How can I help ♥w♥ ? \n" +
                                "────────────────────────────────────";

        String exitMessage = "──────────────────────────────────── \n" +
                             "Bye! Hope to see you again soon! \n" +
                             "────────────────────────────────────";

        System.out.println(welcomeMessage);

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            String echo = "──────────────────────────────────── \n" +
                          "" + userInput + "\n" +
                          "────────────────────────────────────";
            System.out.println(echo);

            userInput = scanner.nextLine();
        }

        System.out.println(exitMessage);
    }
}
