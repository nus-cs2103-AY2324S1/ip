package cloud.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cloud.main.todo.Todo;



/**
 * The chatbot's main class.
 */
public class Cloud {
    private static Scanner SCANNER = new Scanner(System.in);
    private static List<Todo> TODOS = new ArrayList<>();

    private static List<Token> toTokens(String input) {
        String[] words = input.split(" ");
        List<Token> tokens = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Token token = new Token(word);
            tokens.add(token);
        }
        return tokens;
    }

    private static void handle(String input) {
        List<Token> tokens = Cloud.toTokens(input);

        String command = tokens.size() >= 1 ? tokens.get(0).get() : "";
        switch (command) {
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
                Cloud.sayTodo(todo, i + 1);
            }
            break;
        case "mark": {
            Integer number = Cloud.verifyNumber(tokens);
            if (number == null) {
                return;
            }

            Todo todo = Cloud.TODOS.get(number - 1);
            todo.setComplete(true);
            Cloud.sayTodo(todo, number);
            break;
        }
        case "unmark": {
            Integer number = Cloud.verifyNumber(tokens);
            if (number == null) {
                return;
            }

            Todo todo = Cloud.TODOS.get(number - 1);
            todo.setComplete(false);
            Cloud.sayTodo(todo, number);
            break;
        }
        case "bye":
            Cloud.say("\\o");
            System.exit(0);
            break;
        default:
            // Stores new TODO
            Todo todo = new Todo(input);
            Cloud.TODOS.add(todo);
            Cloud.sayTodo(todo, Cloud.TODOS.size());
            break;
        }
    }

    private static Integer verifyNumber(List<Token> tokens) {
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

    private static void sayTodo(Todo todo, int number) {
        Cloud.say(
            String.format(
                "%s | #%d: %s",
                todo.isComplete() ? "X" : " ",
                number,
                todo.getDescription()
            )
        );
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
