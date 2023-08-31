package cloud.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cloud.main.todo.Todo;
import cloud.main.token.Token;
import cloud.main.token.TokenManager;



/**
 * The chatbot's main class.
 */
public class Cloud {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Todo> TODOS = new ArrayList<>();

    private static void handle(String input) {
        TokenManager manager = new TokenManager(input);
        switch (manager.getCommand()) {
        case "":
            Cloud.say("Please enter a valid command.");
            break;
        case "list":
            if (Cloud.TODOS.size() <= 0) {
                Cloud.say("Your TODO list is empty.");
                return;
            }

            for (int i = 0; i < Cloud.TODOS.size(); i++) {
                Todo todo = Cloud.TODOS.get(i);
                Cloud.say(todo.toString(i + 1));
            }
            break;
        case "mark": {
            Integer number = Cloud.verifyNumber(manager);
            if (number == null) {
                return;
            }

            Todo todo = Cloud.TODOS.get(number - 1);
            todo.setComplete(true);
            Cloud.say(todo.toString(number));
            break;
        }
        case "unmark": {
            Integer number = Cloud.verifyNumber(manager);
            if (number == null) {
                return;
            }

            Todo todo = Cloud.TODOS.get(number - 1);
            todo.setComplete(false);
            Cloud.say(todo.toString(number));
            break;
        }
        case "bye":
            Cloud.say("\\o");
            System.exit(0);
            break;
        default:
            // Stores new TODO
            Todo todo = Cloud.createTodo(manager);
            Cloud.TODOS.add(todo);
            Cloud.say(todo.toString(Cloud.TODOS.size()));
            break;
        }
    }

    private static Todo createTodo(TokenManager manager) {
        //TODO base on flags
        return new Todo(manager.toString());
    }

    private static Integer verifyNumber(TokenManager manager) {
        List<Token> tokens = manager.getTokens();
        if (tokens.size() <= 1) {
            return null;
        }

        Token numberToken = tokens.get(1);
        if (!numberToken.isInt()) {
            Cloud.say(
                String.format(
                    "\"%s\" is not a valid TODO number.",
                    numberToken.get()
                )
            );
            return null;
        }

        if (!numberToken.isValidNumber(Cloud.TODOS)) {
            Cloud.say(
                String.format(
                    "TODO #%d does not exist.",
                    numberToken.asInt()
                )
            );
            return null;
        }

        return numberToken.asInt();
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
