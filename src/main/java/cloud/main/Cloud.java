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

    private static void handle(String input) {
        String[] words = input.split(" ");
        String command = words.length >= 1 ? words[0] : "";

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
            Integer number = wordToInt(words, 1);
            if (number == null) {
                return;
            }

            Todo todo = Cloud.TODOS.get(number - 1);
            todo.setComplete(true);
            Cloud.sayTodo(todo, number);
            break;
        }
        case "unmark": {
            Integer number = wordToInt(words, 1);
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

    private static Integer wordToInt(String[] words, int index) {
        String numberString = words.length >= index + 1 ? words[index] : "";
        int number;
        try {
            number = Integer.parseInt(numberString);
        } catch (NumberFormatException e) {
            Cloud.say(
                String.format(
                    "\"%s\" is not a valid TODO number.",
                    numberString
                )
            );
            return null;
        }

        if (number < 0 || number > Cloud.TODOS.size()) {
            Cloud.say(
                String.format(
                    "TODO #%d does not exist.",
                    number
                )
            );
            return null;
        }

        return number;
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
