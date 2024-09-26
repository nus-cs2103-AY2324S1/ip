package parser;

import static command.Commands.CommandEnum.BY;
import static command.Commands.CommandEnum.FROM;
import static command.Commands.CommandEnum.TO;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import command.Commands;
import duke.Duke;
import dukeexceptions.DukeDateTimeParseException;
import dukeexceptions.DukeException;
import dukeexceptions.DukeFromEarlierThanToException;
import dukeexceptions.DukeNullPointerException;
import dukeexceptions.DukeNumberFormatException;
import dukeexceptions.DukeUnknownCommandException;


/**
 * This class takes in the user input and parses returns a Commands object.
 */
public class Parser {

    private final String command;
    private String[] initialParse;
    private String[] phaseParse;

    /**
     * Constructs the Parser object.
     *
     * @param command The string that needs to be parsed.
     */
    public Parser(String command) {
        this.command = command;
    }

    /**
     * Parses the string into actionable commands.
     *
     * @return Returns a Command object.
     * @throws DukeException If the input is not a command
     */
    public Commands parse() throws DukeException {
        Commands.CommandEnum mainCmd = this.mainCommand();
        switch (mainCmd) {
        case BYE: case LIST: case UNDO:
            return Commands.of(mainCmd);

        case TODO: case FIND:
            return parseToDoFind(mainCmd);

        case BY: case FROM: case TO:
            return parseByFromTo(mainCmd);

        case MARK: case UNMARK: case DELETE:
            return parseMarkUnmarkDelete(mainCmd);

        case DEADLINE:
            return parseDeadline(mainCmd);

        case EVENT:
            return parseEvent(mainCmd);
        default:
            // Default would be unknown command, but that is an exception, thus it will be thrown.
        }
        throw new DukeUnknownCommandException("Unknown command");
    }

    private Commands parseToDoFind(Commands.CommandEnum mainCmd) throws DukeException {
        if (this.secondString() == null) {
            throw new DukeException("Please add the task name");
        } else {
            return Commands.of(mainCmd, this.secondString());
        }
    }

    private Commands parseByFromTo(Commands.CommandEnum mainCmd) throws DukeException {
        try {
            String restOfCommand = this.secondString().trim();
            LocalDateTime dateTime = LocalDateTime.parse(restOfCommand, Duke.FORMAT);
            return Commands.of(mainCmd, dateTime);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new DukeDateTimeParseException("The format for dates&time is 'dd-MM-yyyy hhmm'");
        }
    }

    private Commands parseMarkUnmarkDelete(Commands.CommandEnum mainCmd) throws DukeException {
        try {
            int index = Integer.parseInt(this.secondString());
            return Commands.of(mainCmd, index);
        } catch (NumberFormatException | NullPointerException e) {
            throw new DukeNumberFormatException("Place a number after the command");
        }
    }

    private Commands parseDeadline(Commands.CommandEnum mainCmd) throws DukeException {
        try {
            String task = this.commandPhaseParse();
            String command2 = this.phaseTwo();

            if (task == null) {
                // No task name
                throw new DukeException("Please add the task name");
            }

            Parser phaseTwo = new Parser(command2);
            Commands c = phaseTwo.parse();

            if (c.checkCommand(BY)) {
                return Commands.of(mainCmd, task, c);
            } else {
                // Wrong format
                throw new NullPointerException();
            }
        } catch (DukeUnknownCommandException | NullPointerException e) {
            // Wrong format
            throw new DukeNullPointerException("The format for the command is: "
                    + "deadline task /by date&time");
        }
    }

    private Commands parseEvent(Commands.CommandEnum mainCmd) throws DukeException {
        try {
            String task = this.commandPhaseParse();
            String secondaryCommand = this.phaseTwo();
            String tertiaryCommand = this.phaseThree();

            if (task == null) {
                throw new DukeException("Please add the task name");
            }

            Parser phaseTwo = new Parser(secondaryCommand);
            Commands secCmd = phaseTwo.parse();
            Parser phaseThree = new Parser(tertiaryCommand);
            Commands terCmd = phaseThree.parse();

            if (!secCmd.compareTime(terCmd)) {
                throw new DukeFromEarlierThanToException("From must be earlier than To");
            }

            if (secCmd.checkCommand(FROM) && terCmd.checkCommand(TO)) {
                return Commands.of(mainCmd, task, secCmd, terCmd);
            } else {
                // Wrong format
                throw new NullPointerException();
            }

        } catch (DukeUnknownCommandException | NullPointerException e) {
            // Wrong format
            throw new DukeNullPointerException("The format for the command is: "
                    + "event task /from startDayDateTime /to endDayDateTime");
        }
    }

    private Commands.CommandEnum mainCommand() {
        this.initialParse = command.split(" ", 2);
        switch (initialParse[0]) {
        case ("bye"):
            return Commands.CommandEnum.BYE;
        case ("list"):
            return Commands.CommandEnum.LIST;
        case ("undo"):
            return Commands.CommandEnum.UNDO;
        case ("todo"):
            return Commands.CommandEnum.TODO;
        case ("deadline"):
            return Commands.CommandEnum.DEADLINE;
        case ("event"):
            return Commands.CommandEnum.EVENT;
        case ("mark"):
            return Commands.CommandEnum.MARK;
        case ("unmark"):
            return Commands.CommandEnum.UNMARK;
        case ("delete"):
            return Commands.CommandEnum.DELETE;
        case ("by"):
            return Commands.CommandEnum.BY;
        case ("from"):
            return Commands.CommandEnum.FROM;
        case ("to"):
            return Commands.CommandEnum.TO;
        case ("find"):
            return Commands.CommandEnum.FIND;
        default:
            return Commands.CommandEnum.UNKNOWN;
        }
    }

    private String secondString() {
        try {
            if (this.initialParse[1].equals("")) {
                return null;
            } else {
                return this.initialParse[1];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private String commandPhaseParse() {
        try {
            this.phaseParse = this.initialParse[1].split("/");
            return phaseParse[0];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private String phaseTwo() {
        try {
            return this.phaseParse[1];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private String phaseThree() {
        try {
            return this.phaseParse[2];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
