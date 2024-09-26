package uicomponents;

import crackerpackage.Cracker;
import crackerpackage.tasks.Deadline;
import crackerpackage.tasks.Event;
import crackerpackage.tasks.Task;
import crackerpackage.tasks.Todo;
import exceptions.EmptyDescriptionException;
import exceptions.IllegalFormatException;

/**
 * A parser that extracts keywords from input text.
 *
 * @author Anton Tan Hong Zhi
 */
public class Parser {




    /**
     * parses the command header involved in the command.
     *
     * @param input the command to be parsed
     * @return type of header of the command
     */
    public static Cracker.Type parseCommand(String input) {
        switch(input.split(" ")[0]) {
        case "mark":
        case "unmark":
            return Cracker.Type.MARK;
        case "todo":
        case "deadline":
        case "event":
            return Cracker.Type.TASK;
        case "delete":
            return Cracker.Type.DELETE;
        case "list":
            return Cracker.Type.LIST;
        case "bye":
            return Cracker.Type.QUIT;
        case "find":
            return Cracker.Type.FIND;
        case "clear":
            return Cracker.Type.CLEAR;

        default:
            return Cracker.Type.UNKNOWN;
        }
    }

    /**
     * Returns a task based on the header of the command.
     *
     * @param input the command to be parsed
     * @return a task extracted from the command
     * @throws EmptyDescriptionException
     * @throws IllegalFormatException
     */
    public static Task parseTask(String input) throws EmptyDescriptionException, IllegalFormatException {
        String command = input.split(" ")[0];
        switch (command.toLowerCase()) {
        case "todo":
            return new Todo(input.replaceFirst(command, ""));
        case "event":
            return new Event(input.replaceFirst(command, ""));
        case "deadline":
            return new Deadline(input.replaceFirst(command, ""));
        default:
            System.out.println("should not be triggered");
            return null;
        }
    }

    /**
     * Returns a task based on the content saved in the storage.
     * @param arr
     * @return a task saved in the storage
     * @throws EmptyDescriptionException
     * @throws IllegalFormatException
     */
    public static Task parseTask(String[] arr) throws EmptyDescriptionException, IllegalFormatException {
        switch (arr[0].charAt(0)) {
        case 'T':
            return new Todo(arr[2]);
        case 'E':
            return new Event(arr[2], arr[3], arr[4]);
        case 'D':
            return new Deadline(arr[2], arr[3]);
        default:
            System.out.println("Corrupt file detected");
            return null;
        }
    }

    /**
     * Returns the number involved in the command.
     *
     * @param input the command to be parsed
     * @return the index requested by the user
     */
    public static int parseIndex(String input) {
        return Integer.parseInt(input.replace(input.split(" ")[0], "").trim()) - 1;
    }

    /**
     * Returns the keyword from a find operation
     * @param input
     */
    public static String parseKeyword(String input) {
        String command = input.split(" ")[0];
        return input.replaceFirst(command, "").trim();
    }
}
