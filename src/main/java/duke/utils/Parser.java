package duke.utils;

import duke.commands.*;

/**
 * A utility class that parses the command inputted by the user.
 */
public class Parser {

    /**
     * Returns a Command object from parsing fullCommand.
     *
     * @param fullCommand
     * @return A Command object containing the information from fullCommand
     */
    public Command parse(String fullCommand) {
        String[] words = fullCommand.split(" ");
        String command = fullCommand;
        if (words.length > 1) {
            command = words[0];
        }

        switch (command) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "delete":
                try {
                    int taskNumber = Integer.parseInt(words[1]);
                    return new DeleteCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Missing task number");
                    Ui.printLine();
                    return null;
                }

            case "todo":
                return new AddCommand("todo", fullCommand);

            case "deadline":
                return new AddCommand("deadline", fullCommand);

            case "event":
                return new AddCommand("event", fullCommand);

            case "mark":
                try {
                    int taskNumber = Integer.parseInt(words[1]);
                    return new MarkCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Missing task number");
                    Ui.printLine();
                    return null;
                }

            case "unmark":
                try {
                    int taskNumber = Integer.parseInt(words[1]);
                    return new UnmarkCommand(taskNumber);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Missing task number");
                    Ui.printLine();
                    return null;
                }

            default:
                try {
                    throw(new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                } catch (DukeException invalidCommand) {
                    System.out.println(invalidCommand.getMessage());
                    Ui.printLine();
                    return null;
                }
        }
    }
}
