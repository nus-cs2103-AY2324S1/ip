package command;

import dukeExceptions.LoadException;
import task.ListOfTask;
import dukeExceptions.DukeException;
import task.ListOfTaskStud;
import ui.UiStud;

import java.time.LocalDateTime;

public class CommandsStud {
    public enum COMMANDS {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BY, FROM, TO, SORT, FIND, UNKNOWN}

    private COMMANDS state;
    private String name;
    private int index;
    private LocalDateTime dateTime;

    public CommandsStud(CommandsStud.COMMANDS command) {
        this.state = command;
    }

    public CommandsStud(CommandsStud.COMMANDS command, String str) {
        this.state = command;
        this.name = str;
    }

    public CommandsStud(CommandsStud.COMMANDS command, int index) {
        this.state = command;
        this.index = index;
    }

    public CommandsStud(CommandsStud.COMMANDS command, LocalDateTime dateTime) {
        this.state = command;
        this.dateTime = dateTime;
    }

    public CommandsStud.COMMANDS getCommand() {
        return this.state;
    }

    public int execute(ListOfTaskStud taskList, UiStud ui, int lineNumber, String error) {
        boolean load = true;
        if (lineNumber == 0 && error == null) {
            load = false;
        }
        try {
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

            case UNKNOWN:
                throw new DukeException("line corrupted");

            default:
                throw new DukeException("line corrupted " + this.state + " is not a primary command");
            }
        } catch (DukeException e) {
            System.out.println(new LoadException(e.getMessage(), lineNumber, error).getMessage());
        }
        return 1;
    }

    public boolean compareTime(CommandsStud c) {
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
        if (obj instanceof CommandsStud) {
            CommandsStud b = (CommandsStud) obj;
            if ((b.state == this.state) && (b.dateTime == this.dateTime) && (b.index == this.index) && (b.name == this.name)) {
                return true;
            }
        }
        return false;
    }

    public static class TwoCommandsStud extends CommandsStud {
        private COMMANDS state2;
        private String name2;
        private CommandsStud secondaryCommand;
        public TwoCommandsStud(COMMANDS command, String str, COMMANDS command2, String str2) {
            super(command, str);
            this.state2 = command2;
            this.name2 = str2;
        }
        public TwoCommandsStud(COMMANDS command, String str, CommandsStud secondaryCommand) {
            super(command,str);
            this.secondaryCommand = secondaryCommand;
        }

        @Override
        public int execute(ListOfTaskStud taskList, UiStud ui, int lineNumber, String error) {
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
            if (obj instanceof CommandsStud.TwoCommandsStud) {
                CommandsStud.TwoCommandsStud b = (CommandsStud.TwoCommandsStud) obj;
                if (super.equals(b) && b.state2 == this.state2 && this.name2.equals(b.name2) && this.secondaryCommand.equals(b.secondaryCommand)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class ThreeCommandsStud extends CommandsStud {
        private COMMANDS state2;
        private String name2;
        private COMMANDS state3;
        private String name3;
        private CommandsStud phaseTwo;
        private CommandsStud phaseThree;
        public ThreeCommandsStud(COMMANDS command, String str, COMMANDS command2, String str2, COMMANDS command3, String str3) {
            super(command,str);
            this.state2 = command2;
            this.name2 = str2;
            this.state3 = command3;
            this.name3 = str3;
        }

        public ThreeCommandsStud(COMMANDS command, String str, CommandsStud phaseTwo, CommandsStud phaseThree) {
            super(command,str);
            this.phaseTwo = phaseTwo;
            this.phaseThree = phaseThree;
        }

        @Override
        public int execute(ListOfTaskStud taskList, UiStud ui, int lineNumber, String error) {
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
            if (obj instanceof CommandsStud.ThreeCommandsStud) {
                CommandsStud.ThreeCommandsStud b = (CommandsStud.ThreeCommandsStud) obj;
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

