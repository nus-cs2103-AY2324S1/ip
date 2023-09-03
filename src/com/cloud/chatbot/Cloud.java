package com.cloud.chatbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cloud.chatbot.annotations.Nullable;
import com.cloud.chatbot.exceptions.MissingFlagInputException;
import com.cloud.chatbot.exceptions.MissingInputException;
import com.cloud.chatbot.todo.Deadline;
import com.cloud.chatbot.todo.Event;
import com.cloud.chatbot.todo.Todo;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.token.FlagManager;
import com.cloud.chatbot.token.Token;



/**
 * The chatbot's main class.
 */
public final class Cloud {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Todo> TODOS = new ArrayList<>();

    private static void handle(String input) {
        CommandManager manager = new CommandManager(input);
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

    private static @Nullable Todo createTodo(CommandManager manager) {
        String description;
        try {
            description = manager.getDetails();
        } catch (MissingInputException e) {
            Cloud.say("Please enter a description for your TODO.");
            return null;
        }

        @Nullable FlagManager managerBy = manager.findFlag("by");
        @Nullable FlagManager managerFrom = manager.findFlag("from");
        @Nullable FlagManager managerTo = manager.findFlag("to");

        try {
            if (managerBy != null) {
                return new Deadline(description, managerBy.getSubInput());
            }
            if (managerFrom != null && managerTo != null) {
                return new Event(description, managerFrom.getSubInput(), managerTo.getSubInput());
            }
        } catch (MissingFlagInputException e) {
            Cloud.say(
                String.format(
                    "Please enter a description for the \"%s\" flag.",
                    e.getFlagText()
                )
            );
            return null;
        }

        return new Todo(description);
    }

    private static @Nullable Integer verifyNumber(CommandManager manager) {
        if (manager.getTokenCount() <= 1) {
            return null;
        }

        Token numberToken = manager.getToken(1);
        if (!numberToken.isInt()) {
            Cloud.say(
                String.format(
                    "\"%s\" is not a valid number.",
                    numberToken.toString()
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
