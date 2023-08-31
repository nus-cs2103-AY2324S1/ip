package parser;

import command.Commands;
import duke.Duke;
import dukeExceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ParserStud {

    private String command;
    private String[] initialParse;
    private String[] phaseParse;
    public ParserStud(String command) {
        this.command = command;
    }

    public Commands parse() throws DukeException {
        Commands.COMMANDS cmd = this.mainCommand();
        if (cmd == Commands.COMMANDS.BYE || cmd == Commands.COMMANDS.LIST) {
            return Commands.of(cmd);
        }

        if (cmd == Commands.COMMANDS.TODO || cmd == Commands.COMMANDS.FIND) {
            if (this.secondWord() == null) {
                throw new DukeException("Please add the task name");
            } else {
                return Commands.of(cmd, this.secondWord());
            }
        }

        if (cmd == Commands.COMMANDS.BY || cmd == Commands.COMMANDS.FROM || cmd == Commands.COMMANDS.TO) {
            String restOfCommand = this.secondWord().trim();
            try {
                LocalDateTime dateTime = LocalDateTime.parse(restOfCommand, Duke.FORMAT);
                return Commands.of(cmd, dateTime);
            } catch (DateTimeParseException e) {
                throw new DukeDateTimeParseException("The format for dates&time is 'dd-MM-yyyy hhmm'");
            }
        }

        if (cmd == Commands.COMMANDS.MARK || cmd == Commands.COMMANDS.UNMARK || cmd == Commands.COMMANDS.DELETE) {
            try {
                int index = Integer.parseInt(this.secondWord());
                return Commands.of(cmd, index);
            } catch (NumberFormatException e) {
                throw new DukeNumberFormatException("Place a number after the command");
            }
        }

        if (cmd == Commands.COMMANDS.DEADLINE) {
            try {
                String task = this.phaseParse();
                String command2 = this.phaseTwo();
                if (task != null) {
                    ParserStud phaseTwo = new ParserStud(command2);
                    Commands c = phaseTwo.parse();
                    if (c.checkCommand(Commands.COMMANDS.BY)) {
                        return Commands.of(Commands.COMMANDS.DEADLINE, task, c);
                    }
                } else {
                    throw new DukeException("Please add the task name");
                }
            } catch (DukeUnknownCommandException e) {
                throw new DukeNullPointerException("The format for the command is: deadline task /by date&time");
            } catch (NullPointerException e) {
                throw new DukeNullPointerException("The format for the command is: deadline task /by date&time");
            }
        }

        if (cmd == Commands.COMMANDS.EVENT) {
            try {
                String task = this.phaseParse();
                String command2 = this.phaseTwo();
                String command3 = this.phaseThree();
                if (task != null) {
                    ParserStud phaseTwo = new ParserStud(command2);
                    Commands c1 = phaseTwo.parse();
                    ParserStud phaseThree = new ParserStud(command3);
                    Commands c2 = phaseThree.parse();
                    if (c1.checkCommand(Commands.COMMANDS.FROM) && c2.checkCommand(Commands.COMMANDS.TO)) {
                        if (c1.compareTime(c2)) {
                            return Commands.of(Commands.COMMANDS.EVENT, task, c1, c2);
                        } else {
                            throw new DukeFromEarlierThanToException("From must be earlier than To");
                        }
                    } else {
                        throw new DukeNullPointerException("The format for the command is: event task /from startDayDateTime /to endDayDateTime");
                    }
                } else {
                    throw new DukeException("Please add the task name");
                }
            } catch (DukeUnknownCommandException e) {
                throw new DukeNullPointerException("The format for the command is: event task /from startDayDateTime /to endDayDateTime");
            } catch (NullPointerException e) {
                throw new DukeNullPointerException("The format for the command is: event task /from startDayDateTime /to endDayDateTime");
            }
        }

        throw new DukeUnknownCommandException("Unknown command");
    }

    public Commands.COMMANDS mainCommand() {
        this.initialParse = command.split(" ",2);
        switch (initialParse[0]) {
        case ("bye"):
            return Commands.COMMANDS.BYE;
        case ("list"):
            return Commands.COMMANDS.LIST;
        case ("todo"):
            return Commands.COMMANDS.TODO;
        case ("deadline"):
            return Commands.COMMANDS.DEADLINE;
        case ("event"):
            return Commands.COMMANDS.EVENT;
        case ("mark"):
            return Commands.COMMANDS.MARK;
        case ("unmark"):
            return Commands.COMMANDS.UNMARK;
        case ("delete"):
            return Commands.COMMANDS.DELETE;
        case ("by"):
            return Commands.COMMANDS.BY;
        case ("from"):
            return Commands.COMMANDS.FROM;
        case ("to"):
            return Commands.COMMANDS.TO;
        case ("sort"):
            return Commands.COMMANDS.SORT;
        case ("find"):
            return Commands.COMMANDS.FIND;
        default:
            return Commands.COMMANDS.UNKNOWN;
        }
    }

    public String secondWord() {
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

    public String phaseParse() {
        try {
            this.phaseParse = this.initialParse[1].split("/");
            return phaseParse[0];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public String phaseTwo() {
        try {
            return this.phaseParse[1];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public String phaseThree() {
        try {
            return this.phaseParse[2];
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
