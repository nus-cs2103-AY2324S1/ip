package com.cloud.chatbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cloud.chatbot.annotations.Nullable;
import com.cloud.chatbot.exceptions.MissingInputException;
import com.cloud.chatbot.todo.Deadline;
import com.cloud.chatbot.todo.Event;
import com.cloud.chatbot.todo.Todo;
import com.cloud.chatbot.token.Token;
import com.cloud.chatbot.token.TokenManager;



/**
 * The chatbot's main class.
 */
public final class Cloud {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Todo> TODOS = new ArrayList<>();

    private static void handle(String input) {
        TokenManager manager = new TokenManager(input);
        String command;
        try {
            command = manager.getCommand();
        } catch (MissingInputException e) {
            // Ignore empty inputs
            return;
        }

        switch (command) {
        case "todo":
        case "t":
        case "deadline":
        case "d":
        case "event":
        case "e":
        case "add": {
            @Nullable Todo todo = Cloud.createTodo(manager);
            if (todo == null) {
                break;
            }

            Cloud.TODOS.add(todo);
            Cloud.say(todo.toString(Cloud.TODOS.size()));
            break;
        }
        case "list":
        case "l":{
            if (Cloud.TODOS.size() <= 0) {
                Cloud.say("Your TODO list is empty.");
                break;
            }

            for (int i = 0; i < Cloud.TODOS.size(); i++) {
                Todo todo = Cloud.TODOS.get(i);
                Cloud.say(todo.toString(i + 1));
            }
            break;
        }
        case "mark":
        case "m": {
            @Nullable Integer number = Cloud.verifyNumber(manager);
            if (number == null) {
                break;
            }

            Todo todo = Cloud.TODOS.get(number - 1);
            todo.setComplete(true);
            Cloud.say(todo.toString(number));
            break;
        }
        case "unmark":
        case "un": {
            @Nullable Integer number = Cloud.verifyNumber(manager);
            if (number == null) {
                break;
            }

            Todo todo = Cloud.TODOS.get(number - 1);
            todo.setComplete(false);
            Cloud.say(todo.toString(number));
            break;
        }
        case "bye":
        case "exit":
        case "quit":
        case "q":
        case "done": {
            Cloud.say("\\o");
            System.exit(0);
            break;
        }
        default: {
            Cloud.say(
                String.format(
                    "\"%s\" is not a valid command.",
                    command
                )
            );
            break;
        }
        }
    }

    private static @Nullable Todo createTodo(TokenManager manager) {
        String description;
        try {
            description = manager.getDescription();
        } catch (MissingInputException e) {
            Cloud.say(e.getMessage());
            return null;
        }

        @Nullable TokenManager inputBy = manager.findFlag("by");
        @Nullable TokenManager inputFrom = manager.findFlag("from");
        @Nullable TokenManager inputTo = manager.findFlag("to");

        if (inputBy != null) {
            return new Deadline(description, inputBy.toString());
        }
        if (inputFrom != null && inputTo != null) {
            return new Event(description, inputFrom.toString(), inputTo.toString());
        }
        return new Todo(description);
    }

    private static @Nullable Integer verifyNumber(TokenManager manager) {
        List<Token> tokens = manager.getTokens();
        if (tokens.size() <= 1) {
            return null;
        }

        Token numberToken = tokens.get(1);
        if (!numberToken.isInt()) {
            Cloud.say(
                String.format(
                    "\"%s\" is not a valid number.",
                    numberToken.get()
                )
            );
            return null;
        }

        if (!numberToken.isValidNumber(Cloud.TODOS)) {
            Cloud.say(
                String.format(
                    "TODO #%d does not exist.",
                    numberToken.toInt()
                )
            );
            return null;
        }

        return numberToken.toInt();
    }

    private static void say(String text) {
        System.out.println(text);
    }

    private static void inputMarker() {
        System.out.print("\n>>> ");
    }

    /**
     * The chatbot's main method.
     *
     * @param args Java arguments.
     */
    public static void main(String[] args) {
        Cloud.say("Cloud online.");
        Cloud.inputMarker();

        while (Cloud.SCANNER.hasNextLine()) {
            String input = Cloud.SCANNER.nextLine();
            Cloud.handle(input);

            Cloud.inputMarker();
        }
    }
}
