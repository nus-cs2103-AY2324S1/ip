import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ██▄   ████▄    ▄     ▄▀  \n" +
                "█  █  █   █     █  ▄▀    \n" +
                "█   █ █   █ ██   █ █ ▀▄  \n" +
                "█  █  ▀████ █ █  █ █   █ \n" +
                "███▀        █  █ █  ███  \n" +
                "            █   ██       \n" +
                "                         ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if ("bye".equals(input)) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break; // This will exit the loop and end the program
            } else {
                if ("list".equals(input)) {
                    for (int i = 1; i < inputs.size()+1; i++) {
                        System.out.println(i + ". " + inputs.get(i-1));
                    }
                } else {
                    inputs.add(input);
                    System.out.println("added: " + input);
                }
            }
        }

        scanner.close();

    }
}
