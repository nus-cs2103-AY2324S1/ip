package cloud.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * The chatbot's main class.
 */
public class Cloud {
    private static Scanner SCANNER = new Scanner(System.in);
    private static List<Todo> TODOS = new ArrayList<>();

    private static void handle(String input) {
        switch (input) {
        case "list":
            // Lists TODOs
            Cloud.say("Your TODO list:");
            for (int i = 0; i < Cloud.TODOS.size(); i++) {
                Cloud.say(
                    String.format(
                        "%d) %s",
                        i + 1,
                        Cloud.TODOS.get(i).getDescription()
                    )
                );
            }
            break;
        case "bye":
            // Ends chat session
            Cloud.say("\\o");
            System.exit(0);
            break;
        default:
            // Stores new TODO
            Cloud.TODOS.add(
                new Todo(input)
            );
            Cloud.say(
                String.format(
                    "Added TODO #%d: \"%s\"",
                    Cloud.TODOS.size(),
                    input
                )
            );
            break;
        }
    }

    private static void say(String text) {
        System.out.println(text);
    }

    /**
     * The chatbot's main method.
     *
     * @param args Java arguments.
     */
    public static void main(String[] args) {
        Cloud.say("Cloud online.");

        while (true) {
            System.out.println("");
            System.out.print(">>> ");

            String input = Cloud.SCANNER.nextLine();
            Cloud.handle(input);
        }
    }
}
