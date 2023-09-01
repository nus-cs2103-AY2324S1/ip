package duke.core;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import duke.command.Command;

public class Ui {
    private static String LINE_SEPARATOR = "    ----------------------------------------------------------------------";

    private static Scanner scanner = new Scanner(System.in);

    public static <T> void respond(T message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(String.format("     %s",  message.toString()));
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }

    public static <T> void respond(List<T> messages) {
        System.out.println(LINE_SEPARATOR);
        for (T message: messages) {
            System.out.println(String.format("     %s",  message.toString()));
        }
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }

    public static <T> void respond(Stream<T> messages) {
        System.out.println(LINE_SEPARATOR);
        messages.forEach(message -> System.out.println(String.format("     %s",  message.toString())));
        System.out.println(LINE_SEPARATOR);
        System.out.print(">>> ");
    }

    /** Returns the Command after reading the next line of input from the user. */
    public static Command readCommand() {
        String input = Ui.scanner.nextLine();

        if (input.trim().equals("")) {
            return null;
        }

        return Parser.parseCommand(input);
    }
}
