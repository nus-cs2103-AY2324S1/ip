package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import command.Commands;
import duke.Duke;
import dukeExceptions.DukeDateTimeParseException;
import dukeExceptions.DukeException;
import dukeExceptions.DukeFromEarlierThanToException;
import dukeExceptions.DukeNullPointerException;
import dukeExceptions.DukeNumberFormatException;
import dukeExceptions.DukeUnknownCommandException;

/**
 * This class takes in the user input and parses returns a Commands object.
 */
public class Parser {

    private String command;
    private String[] initialParse;
    private String[] phaseParse;

    /**
     * Construct the Parser object.
     * @param command The string that needs to be parsed.
     */
    public Parser(String command) {
        this.command = command;
    }

    /**
     * Parses the string into actionable commands.
     * @return Returns a Command object.
     * @throws DukeException If the input is not a command
     */
    public Commands parse() throws DukeException {
        Commands.CommandEnum cmd = this.mainCommand();
        switch (cmd) {
        case BYE: case LIST:
            return Commands.of(cmd);

        case TODO: case FIND:
            if (this.secondWord() == null) {
                throw new DukeException("Please add the task name");
            } else {
                return Commands.of(cmd, this.secondWord());
            }

        case BY: case FROM: case TO:
            try {
                String restOfCommand = this.secondWord().trim();
                LocalDateTime dateTime = LocalDateTime.parse(restOfCommand, Duke.FORMAT);
                return Commands.of(cmd, dateTime);
            } catch (DateTimeParseException | NullPointerException e) {
                throw new DukeDateTimeParseException("The format for dates&time is 'dd-MM-yyyy hhmm'");
            }

        case MARK: case UNMARK: case DELETE:
            try {
                int index = Integer.parseInt(this.secondWord());
                return Commands.of(cmd, index);
            } catch (NumberFormatException | NullPointerException e) {
                throw new DukeNumberFormatException("Place a number after the command");
            }

        case DEADLINE:
            try {
                String task = this.phaseParse();
                String command2 = this.phaseTwo();
                if (task != null) {
                    Parser phaseTwo = new Parser(command2);
                    Commands c = phaseTwo.parse();
                    if (c.checkCommand(Commands.CommandEnum.BY)) {
                        return Commands.of(Commands.CommandEnum.DEADLINE, task, c);
                    }
                    break;
                } else {
                    throw new DukeException("Please add the task name");
                }
            } catch (DukeUnknownCommandException e) {
                throw new DukeNullPointerException("The format for the command is: deadline task /by date&time");
            } catch (NullPointerException e) {
                throw new DukeNullPointerException("The format for the command is: deadline task /by date&time");
            }

        case EVENT:
            try {
                String task = this.phaseParse();
                String command2 = this.phaseTwo();
                String command3 = this.phaseThree();
                if (task != null) {
                    Parser phaseTwo = new Parser(command2);
                    Commands c1 = phaseTwo.parse();
                    Parser phaseThree = new Parser(command3);
                    Commands c2 = phaseThree.parse();
                    if (!c1.compareTime(c2)) {
                        throw new DukeFromEarlierThanToException("From must be earlier than To");
                    }
                    if (c1.checkCommand(Commands.CommandEnum.FROM) && c2.checkCommand(Commands.CommandEnum.TO)) {
                        return Commands.of(Commands.CommandEnum.EVENT, task, c1, c2);
                    }
                    break;
                } else {
                    throw new DukeException("Please add the task name");
                }
            } catch (DukeUnknownCommandException e) {
                throw new DukeNullPointerException("The format for the command is: "
                        + "event task /from startDayDateTime /to endDayDateTime");
            } catch (NullPointerException e) {
                throw new DukeNullPointerException("The format for the command is: "
                        + "event task /from startDayDateTime /to endDayDateTime");
            }
        default:

        }
        throw new DukeUnknownCommandException("Unknown command");
    }

    private Commands.CommandEnum mainCommand() {
        this.initialParse = command.split(" ", 2);
        switch (initialParse[0]) {
        case ("bye"):
            return Commands.CommandEnum.BYE;
        case ("list"):
            return Commands.CommandEnum.LIST;
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
        case ("sort"):
            return Commands.CommandEnum.SORT;
        case ("find"):
            return Commands.CommandEnum.FIND;
        default:
            return Commands.CommandEnum.UNKNOWN;
        }
    }

    private String secondWord() {
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

    private String phaseParse() {
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
