package duke.parser;

import duke.Duke;
import duke.command.Command;
import duke.exception.DukeException;

public class Parser {
    private Command command;
    private String arguments = "";

    public Parser(String rawData) {
        try {
            this.command = parseCommand(rawData);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    public Command parseCommand(String rawData) throws DukeException {
        String[] commandArray = rawData.split("\\s+", 2);
        if (commandArray.length == 0) {
            throw new DukeException("Hey, Type Something!");
        } else if (commandArray.length > 1) {
            this.arguments = commandArray[1];
        }
        return Command.getCommand(commandArray[0]);
    }

    public Command getCommand() {
        return this.command;
    }

    public String getArguments() {
        return this.arguments;
    }

    public String parseToDoArguments() throws DukeException {
        if (arguments.length() == 0) {
            throw new DukeException("Hey! The task description cannot be empty!");
        }
        return arguments;
    }

    /**
     * Parses the find arguments.
     *
     * @return The arguments of the find command.
     * @throws DukeException if argument is empty.
     */
    public String parseFindQuery() throws DukeException {
        if (arguments.length() == 0) {
            throw new DukeException("Hey! The task description cannot be empty!");
        }
        return arguments;
    }

    public String[] parseDeadlineArguments() throws DukeException {
        if (this.arguments.length() == 0) {
            throw new DukeException("Hey! The task description cannot be empty!");
        }

        String[] arr = this.arguments.split(" /by ");

        if (arr.length != 2) {
            throw new DukeException("Hey, the Deadline given is Invalid! " +
                    "Make sure that you follow this format:\n" +
                    Duke.INDENTATION + "'taskDescription /by time'");
        }

        return arr;
    }

    public String[] parseEventArguments() throws DukeException {
        if (this.arguments.length() == 0) {
            throw new DukeException("Hey! The task description cannot be empty!");
        }
        String[] arr = this.arguments.split(" /from ");
        if (arr.length != 2) {
            throw new DukeException("Hey, the Event given is Invalid!" +
                    " Make sure that you follow this format:\n" +
                    Duke.INDENTATION + " 'eventDescription /from startTime /to endTime'");
        }
        String desc = arr[0];

        String[] startEnd = arr[1].split(" /to ");
        if (startEnd.length != 2) {
            throw new DukeException("Hey, the Event given is Invalid!" +
                    " Make sure that you follow this format:\n" +
                    Duke.INDENTATION + " 'eventDescription /from startTime /to endTime'");
        }
        String start = startEnd[0];
        String end = startEnd[1];

        return new String[]{desc, start, end};
    }

}
