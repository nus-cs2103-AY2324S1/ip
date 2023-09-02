package UIComponents;

import crackerpackage.Cracker;
import Exceptions.EmptyDescriptionException;
import Exceptions.IllegalFormatException;
import crackerpackage.tasks.Deadline;
import crackerpackage.tasks.Event;
import crackerpackage.tasks.Task;
import crackerpackage.tasks.Todo;

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
    public static Cracker.Type parseCommand(String input){
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
        switch (command.toLowerCase()){
            case "todo" :
                return new Todo(input.replaceFirst(command,""));
            case "event":
                return new Event(input.replaceFirst(command,""));
            case "deadline":
                return new Deadline(input.replaceFirst(command,""));
            default :
                System.out.println("should not be triggered");
                return null;
        }
    }

    /**
     * Returns the number involved in the command.
     *
     * @param input the command to be parsed
     * @return
     */
    public static int parseIndex(String input){
        return Integer.parseInt(input.replace(input.split(" ")[0], "").trim()) - 1;
    }


}