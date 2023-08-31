package command;


import task.ListOfTask;
import dukeExceptions.DukeException;

import java.time.LocalDateTime;

public class Commands {

    /**
     * List of both primary and secondary commands.
     */
    public enum COMMANDS {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BY, FROM, TO, SORT, FIND, UNKNOWN}

    private COMMANDS state;
    private String name;
    private int index;
    private LocalDateTime dateTime;

    /**
     * Construct a Commands object with only the primary command.
     * @param command The command or action given by the Parse object.
     */
    public static Commands of(COMMANDS command) {
        return new Commands(command);
    }

    /**
     * Construct a Commands object with both a primary command and task description.
     * @param command The command or action given by the Parse object.
     * @param task The task name or description.
     */
    public static Commands of(COMMANDS command, String task) {
        return new Commands(command, task);
    }

    /**
     * Construct a Commands object with both a primary command and the index of the task.
     * @param command The command or action given by the Parse object.
     * @param index The index of the task that the command should act on.
     */
    public static Commands of(COMMANDS command, int index) {
        return new Commands(command, index);
    }

    /**
     * Construct a Commands object with both a primary command and the index of the task.
     * @param command The command or action given by the Parse object.
     * @param dateTime The date and time of a command giving in the format of 'dd-MM-yyyy HHmm'.
     */
    public static Commands of(COMMANDS command, LocalDateTime dateTime) {
        return new Commands(command, dateTime);
    }

    /**
     * Construct a Commands object with both a primary command and the index of the task.
     * @param command The command or action given by the Parse object.
     * @param task The task name or description.
     * @param secondaryCommand A sub-command that supplements the main command.
     */
    public static Commands of(COMMANDS command, String task, Commands secondaryCommand) {
        return new Commands.TwoCommands(command, task, secondaryCommand);
    }

    /**
     * Construct a Commands object with both a primary command and the index of the task.
     * @param command The command or action given by the Parse object.
     * @param task The task name or description.
     * @param secondaryCommand A sub-command that supplements the main command.
     * @param tertiaryCommand A sub-command that supplements the main command and comes after the secondaryCommand.
     */
    public static Commands of(COMMANDS command, String task, Commands secondaryCommand, Commands tertiaryCommand) {
        return new Commands.ThreeCommands(command, task, secondaryCommand, tertiaryCommand);
    }

    private Commands(COMMANDS command) {
        this.state = command;
    }

    private Commands(COMMANDS command, String task) {
        this.state = command;
        this.name = task;
    }

    private Commands(COMMANDS command, int index) {
        this.state = command;
        this.index = index;
    }


    private Commands(COMMANDS command, LocalDateTime dateTime) {
        this.state = command;
        this.dateTime = dateTime;
    }

    /**
     * Check if this object's COMMANDS is the same as command.
     * @param command The COMMANDS enum that you want to compare
     * @return Returns true same, false otherwise.
     */
    public boolean checkCommand(COMMANDS command) {
        if (this.state == command) {
            return true;
        }
        return false;
    }
    /**
     * Executes the action or throws a DukeException.
     * @param taskList The list of tasks that the action will be executed in.
     * @return Returns 0 if the 'bye' command is executed, returns 1 if any other command is successfully executed.
     * @throws DukeException The exception thrown when encountering any problems in executing.
     */
    public int action(ListOfTask taskList) throws DukeException {
        return execute(taskList, 0, null);
    }

    /**
     * Executes the action or throws a DukeException.
     * @param taskList The list of tasks that the action will be executed in.
     * @param lineNumber Line Number of the command when reading from the save file.
     * @param error The command reading from the save file.
     * @return Returns 0 if the 'bye' command is executed, returns 1 if any other command is successfully executed.
     * @throws DukeException The exception thrown when encountering any problems in executing.
     */
    public int execute(ListOfTask taskList, int lineNumber, String error) throws DukeException {
        boolean load = true;
        if (lineNumber == 0 && error == null) {
            load = false;
        }
            switch (this.state) {

            case BYE:
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

    /**
     * Compares LocalDateTime between this object and c.
     * @param c Commands object to be compared to.
     * @return Returns true if this object's time is before c's time, false for all other cases.
     */
    public boolean compareTime(Commands c) {
        try {
            if (this.dateTime.isBefore(c.dateTime)) {
                return true;
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    /**
     * Compares this object to another object.
     * @param obj An object.
     * @return Returns true if both objects are equivalent, false if otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Commands) {
            Commands b = (Commands) obj;
            if (b.state == this.state && b.dateTime == this.dateTime && b.index == this.index && b.name == this.name) {
                return true;
            }
        }
        return false;
    }

    private static class TwoCommands extends Commands {
        private COMMANDS state2;
        private String name2;
        private Commands secondaryCommand;
        private TwoCommands(COMMANDS command, String str, COMMANDS command2, String str2) {
            super(command, str);
            this.state2 = command2;
            this.name2 = str2;
        }
        private TwoCommands(COMMANDS command, String str, Commands secondaryCommand) {
            super(command,str);
            this.secondaryCommand = secondaryCommand;
        }

        /**
         * Executes the action or throws a DukeException.
         * @param taskList The list of tasks that the action will be executed in.
         * @param lineNumber Line Number of the command when reading from the save file.
         * @param error The command reading from the save file.
         * @return Returns 0 if the 'bye' command is executed, returns 1 if any other command is successfully executed.
         * @throws DukeException The exception thrown when encountering any problems in executing.
         */
        @Override
        public int execute(ListOfTask taskList, int lineNumber, String error) throws DukeException {
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

        /**
         * Compares this object to another object.
         * @param obj An object.
         * @return Returns true if both objects are equivalent, false if otherwise.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Commands.TwoCommands) {
                Commands.TwoCommands b = (Commands.TwoCommands) obj;
                if (super.equals(b) && b.state2 == this.state2 && this.name2.equals(b.name2) && this.secondaryCommand.equals(b.secondaryCommand)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class ThreeCommands extends Commands {
        private COMMANDS state2;
        private String name2;
        private COMMANDS state3;
        private String name3;
        private Commands phaseTwo;
        private Commands phaseThree;
        private ThreeCommands(COMMANDS command, String str, COMMANDS command2, String str2, COMMANDS command3, String str3) {
            super(command,str);
            this.state2 = command2;
            this.name2 = str2;
            this.state3 = command3;
            this.name3 = str3;
        }

        private ThreeCommands(COMMANDS command, String str, Commands phaseTwo, Commands phaseThree) {
            super(command,str);
            this.phaseTwo = phaseTwo;
            this.phaseThree = phaseThree;
        }

        /**
         * Executes the action or throws a DukeException.
         * @param taskList The list of tasks that the action will be executed in.
         * @param lineNumber Line Number of the command when reading from the save file.
         * @param error The command reading from the save file.
         * @return Returns 0 if the 'bye' command is executed, returns 1 if any other command is successfully executed.
         * @throws DukeException The exception thrown when encountering any problems in executing.
         */
        @Override
        public int execute(ListOfTask taskList, int lineNumber, String error) throws DukeException {
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

        /**
         * Compares this object to another object.
         * @param obj An object.
         * @return Returns true if both objects are equivalent, false if otherwise.
         */
        @Override
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
