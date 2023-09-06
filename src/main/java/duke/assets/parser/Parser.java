package duke.assets.parser;

import duke.assets.commands.*;
import duke.data.TaskList;
import duke.dukeexceptions.CorruptDataException;
import duke.dukeexceptions.InvalidCommandException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Parser {
    public CommandAbstract createUserCommand(String input) throws InvalidCommandException {
        try {
            String command = input.split(" ")[0];
        } catch (IndexOutOfBoundsException exp) {
            throw new InvalidCommandException(input);
        }

        switch (input.split(" ")[0].toLowerCase()) {
            case "bye":
                return new ByeCommand(input);
            case "list":
                return new ListCommand(input);
            case "mark":
                return new MarkCommand(input);
            case "unmark":
                return new UnmarkCommand(input);
            case "delete":
                return new DeleteCommand(input);
            case "todo":
                return new CreateTodoCommand(input, false);
            case "deadline":
                return new CreateDeadlineCommand(input, false);
            case "event":
                return new CreateEventCommand(input, false);
        }
        throw new InvalidCommandException(input);
    }

    public void passUserCommand(String input, TaskList tasklist) {
        try {
            this.createUserCommand(input).execute(tasklist);
        } catch (InvalidCommandException exp) {
            System.out.println("ChadGPT: Please ensure that you have entered a valid command.");
        }
    }

    public CommandAbstract createDataCommand(String input) throws CorruptDataException {
        String dataRegexString = "^[TDE] \\| [01] \\| .+($|\\s.+ - .+$)";
        Pattern dataRegex = Pattern.compile(dataRegexString);
        Matcher dataMatcher = dataRegex.matcher(input);
        if (dataMatcher.find()) {
            String[] delimited = input.split(" \\| ");
            boolean isDone = delimited[1].equals("1");
            switch(delimited[0]) {
                case "T":
                    return new CreateTodoCommand("todo " + delimited[2], isDone);
                case "D":
                    return new CreateDeadlineCommand("deadline " + delimited[2]
                            + " /by " + delimited[3], isDone);
                case "E":
                    String[] dateAndTimeDelimited = delimited[3].split(" - ");
                    return new CreateEventCommand("event " + delimited[2]
                            + " /from " + dateAndTimeDelimited[0] + " /to "
                            + dateAndTimeDelimited[1], isDone);
            }
        }
        throw new CorruptDataException(input);
    }

    public void passDataCommand(String input, TaskList tasklist) throws CorruptDataException {
        try {
            this.createDataCommand(input).execute(tasklist);
        } catch (InvalidCommandException exp) {
            throw new CorruptDataException(input);
        }
    }
}
