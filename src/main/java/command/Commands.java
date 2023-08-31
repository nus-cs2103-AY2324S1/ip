package command;

import dukeExceptions.DukeNumberFormatException;
import dukeExceptions.DukeUnknownCommandException;
import dukeExceptions.LoadException;
import task.ListOfTask;
import dukeExceptions.DukeException;
import ui.Ui;

import java.time.LocalDateTime;

public class Commands {
    public enum COMMANDS {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BY, FROM, TO, SORT, FIND, UNKNOWN}

    COMMANDS state;
    String name;
    int index;
    LocalDateTime dateTime;

    public Commands(COMMANDS command) {
        this.state = command;
    }

    public Commands(COMMANDS command, String str) {
        this.state = command;
        this.name = str;
    }

    public Commands(COMMANDS command, int index) {
        this.state = command;
        this.index = index;
    }

    public Commands(COMMANDS command, LocalDateTime dateTime) {
        this.state = command;
        this.dateTime = dateTime;
    }

    public COMMANDS getCommand() {
        return this.state;
    }

    public int execute(ListOfTask taskList, Ui ui, int lineNumber, String error) throws DukeException {
        boolean load = true;
        if (lineNumber == 0 && error == null) {
            load = false;
        }
            switch (this.state) {

            case BYE:
                ui.exit();
                return 0;

            case LIST:
                taskList.listTasks();
                break;

            case TODO:
                if (!load) {
                    taskList.addTask(this.name, true);
                } else {
                    taskList.addTask(this.name, false);
                }
                break;

            case FIND:
                //taskList.find(this.name);
                break;

            case SORT:
                break;

            case MARK:
                if (!load) {
                    taskList.mark(this.index, true);
                } else {
                    taskList.mark(this.index, false);
                }
                break;

            case UNMARK:
                if (!load) {
                    taskList.unMark(this.index, true);
                } else {
                    taskList.unMark(this.index, false);
                }
                break;

            case DELETE:
                if (!load) {
                    taskList.delete(this.index, true);
                } else {
                    taskList.delete(this.index, false);
                }
                break;
            }
        return 1;
    }

    public boolean compareTime(Commands c) {
        if (this.dateTime.isBefore(c.dateTime)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Commands) {
            Commands b = (Commands) obj;
            if (b.state == this.state
                    && b.dateTime == this.dateTime
                    && b.index == this.index
                    && b.name == this.name) {
                return true;
            }
        }
        return false;
    }

    public static class TwoCommands extends Commands {
        private COMMANDS state2;
        private String name2;
        private Commands secondaryCommand;
        public TwoCommands(COMMANDS command, String str, COMMANDS command2, String str2) {
            super(command, str);
            this.state2 = command2;
            this.name2 = str2;
        }
        public TwoCommands(COMMANDS command, String str, Commands secondaryCommand) {
            super(command,str);
            this.secondaryCommand = secondaryCommand;
        }

        @Override
        public int execute(ListOfTask taskList, Ui ui, int lineNumber, String error) throws DukeException {
            boolean load = true;
            if (lineNumber == 0 && error == null) {
                load = false;
            }
            switch (super.state) {

            case DEADLINE:
                if (!load) {
                    taskList.addTask(super.name, this.secondaryCommand.dateTime, true);
                } else {
                    taskList.addTask(super.name, this.secondaryCommand.dateTime, false);
                }
                break;
            }
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Commands.TwoCommands) {
                Commands.TwoCommands b = (Commands.TwoCommands) obj;
                if (super.equals(b)
                        && b.state2 == this.state2
                        && this.name2.equals(b.name2)
                        && this.secondaryCommand.equals(b.secondaryCommand)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class ThreeCommands extends Commands {
        private COMMANDS state2;
        private String name2;
        private COMMANDS state3;
        private String name3;
        private Commands phaseTwo;
        private Commands phaseThree;
        public ThreeCommands(COMMANDS command, String str, COMMANDS command2,
                             String str2, COMMANDS command3, String str3) {
            super(command,str);
            this.state2 = command2;
            this.name2 = str2;
            this.state3 = command3;
            this.name3 = str3;
        }

        public ThreeCommands(COMMANDS command, String str, Commands phaseTwo, Commands phaseThree) {
            super(command,str);
            this.phaseTwo = phaseTwo;
            this.phaseThree = phaseThree;
        }

        @Override
        public int execute(ListOfTask taskList, Ui ui, int lineNumber, String error) throws DukeException {
            boolean load = true;
            if (lineNumber == 0 && error == null) {
                load = false;
            }
            switch (super.state) {

            case EVENT:
                if (!load) {
                    taskList.addTask(super.name, this.phaseTwo.dateTime, this.phaseThree.dateTime, true);
                } else {
                    taskList.addTask(super.name, this.phaseTwo.dateTime, this.phaseThree.dateTime, false);
                }
                break;
            }
            return 1;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Commands.ThreeCommands) {
                Commands.ThreeCommands b = (Commands.ThreeCommands) obj;
                if (super.equals(b) && b.state2 == this.state2 && this.name2.equals(b.name2)
                        && b.state3 == this.state3 && this.name3.equals(b.name3)
                        && this.phaseTwo.equals(b.phaseTwo) && this.phaseThree.equals(b.phaseThree)) {
                    return true;
                }
            }
            return false;
        }
    }
}
