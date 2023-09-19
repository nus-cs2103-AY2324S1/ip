package command;

import java.time.LocalDateTime;
import java.util.Objects;

import dukeexceptions.DukeException;
import task.ListOfTask;
import ui.Ui;

/**
 * This class uses its state to execute commands.
 * The Commands factory methods should only be used by Parser class.
 */
public class Commands {

    /**
     * List of both primary and secondary commands.
     */
    public enum CommandEnum { BYE, LIST, TODO, DEADLINE, EVENT, MARK,
            UNMARK, DELETE, BY, FROM, TO, FIND, UNDO, UNKNOWN }

    private final CommandEnum primaryCommand;
    private String taskDescription;
    private int index;
    private LocalDateTime dateTime;

    private Commands(CommandEnum command) {
        this.primaryCommand = command;
    }

    private Commands(CommandEnum command, String task) {
        this.primaryCommand = command;
        this.taskDescription = task;
    }

    private Commands(CommandEnum command, int index) {
        this.primaryCommand = command;
        this.index = index;
    }

    private Commands(CommandEnum command, LocalDateTime dateTime) {
        this.primaryCommand = command;
        this.dateTime = dateTime;
    }

    /**
     * Constructs a Commands object with only the primary command.
     *
     * @param command The command or action given by the Parse object.
     */
    public static Commands of(CommandEnum command) {
        return new Commands(command);
    }

    /**
     * Constructs a Commands object with both a primary command and task description.
     *
     * @param command The command or action given by the Parse object.
     * @param task The task name or description.
     */
    public static Commands of(CommandEnum command, String task) {
        return new Commands(command, task);
    }

    /**
     * Constructs a Commands object with both a primary command and the index of the task.
     *
     * @param command The command or action given by the Parse object.
     * @param index The index of the task that the command should act on.
     */
    public static Commands of(CommandEnum command, int index) {
        return new Commands(command, index);
    }

    /**
     * Constructs a Commands object with both a primary command and the index of the task.
     *
     * @param command The command or action given by the Parse object.
     * @param dateTime The date and time of a command giving in the format of 'dd-MM-yyyy HHmm'.
     */
    public static Commands of(CommandEnum command, LocalDateTime dateTime) {
        return new Commands(command, dateTime);
    }

    /**
     * Constructs a Commands object with both a primary command and the index of the task.
     *
     * @param command The command or action given by the Parse object.
     * @param task The task name or description.
     * @param secondaryCommand A sub-command that supplements the main command.
     */
    public static Commands of(CommandEnum command, String task, Commands secondaryCommand) {
        return new Commands.TwoCommands(command, task, secondaryCommand);
    }

    /**
     * Constructs a Commands object with both a primary command and the index of the task.
     *
     * @param command The command or action given by the Parse object.
     * @param task The task name or description.
     * @param secondaryCommand A sub-command that supplements the main command.
     * @param tertiaryCommand A sub-command that supplements the main command and comes after the secondaryCommand.
     */
    public static Commands of(CommandEnum command, String task, Commands secondaryCommand, Commands tertiaryCommand) {
        return new Commands.ThreeCommands(command, task, secondaryCommand, tertiaryCommand);
    }

    /**
     * Checks if this object's COMMANDS is the same as command.
     *
     * @param command The COMMANDS enum that you want to compare
     * @return Returns true same, false otherwise.
     */
    public boolean checkCommand(CommandEnum command) {
        return this.primaryCommand == command;
    }
    /**
     * Executes the action or throws a DukeException.
     *
     * @param taskList The list of tasks that the action will be executed in.
     * @return Returns a string representing the task done, or an error or null.
     * @throws DukeException The exception thrown when encountering any problems in executing.
     */
    public String execute(ListOfTask taskList) throws DukeException {
        return execute(taskList, true);
    }

    /**
     * Executes the action or throws a DukeException.
     *
     * @param taskList The list of tasks that the action will be executed in.
     * @return Returns a string representing the task done, or an error or null.
     * @throws DukeException The exception thrown when encountering any problems in executing.
     */
    public String execute(ListOfTask taskList, boolean print) throws DukeException {
        String response;
        switch (this.primaryCommand) {

        case BYE:
            response = Ui.exit();
            break;

        case LIST:
            response = taskList.listTasks();
            break;

        case FIND:
            response = taskList.find(this.taskDescription);
            break;

        case TODO:
            response = taskList.addToDo(this.taskDescription, print);
            break;

        case MARK:
            response = taskList.markOrUnmark(this.index, true, print);
            break;

        case UNMARK:
            response = taskList.markOrUnmark(this.index, false, print);
            break;

        case DELETE:
            response = taskList.delete(this.index);
            break;

        case UNDO:
            response = taskList.getPreviousCommand().undo(taskList);
            break;

        default:
            // No tasks to do which is impossible as the Commands that are not here will not get through Parser.
            return null;
        }

        // Adding the user's command input into the previous command stack
        if (print) {
            switch (primaryCommand) {
            case TODO: case MARK: case UNMARK: case DELETE:
                taskList.addCommand(this);
                break;

            default:

            }
        }

        return response;
    }

    private String undo(ListOfTask taskList) throws DukeException {
        switch (this.primaryCommand) {

        case TODO: case DEADLINE: case EVENT:
            int index = taskList.size();
            return taskList.delete(index);

        case MARK:
            return taskList.markOrUnmark(this.index, false, true);

        case UNMARK:
            return taskList.markOrUnmark(this.index, true, true);

        case DELETE:
            return taskList.undoDeleteTask(this.index);

        default:

        }

        return null;
    }

    /**
     * Compares LocalDateTime between this object and c.
     *
     * @param c Commands object to be compared to.
     * @return Returns true if this object's time is before c's time, false for all other cases.
     */
    public boolean compareTime(Commands c) {
        try {
            return this.dateTime.isBefore(c.dateTime);
        } catch (NullPointerException e) {
            return false;
        }
    }


    private static class TwoCommands extends Commands {
        private final Commands secondaryCommand;

        private TwoCommands(CommandEnum command, String str, Commands secondaryCommand) {
            super(command, str);
            this.secondaryCommand = secondaryCommand;
        }

        /**
         * @inheritDoc
         */
        @Override
        public String execute(ListOfTask taskList, boolean print) throws DukeException {
            String response;
            if (Objects.requireNonNull(super.primaryCommand) == CommandEnum.DEADLINE) {
                response = taskList.addDeadline(super.taskDescription, this.secondaryCommand.dateTime, print);
            } else {
                return null;
            }

            // Adding the user's command input into the previous command stack
            if (print) {
                taskList.addCommand(this);
            }
            return response;
        }
    }

    private static class ThreeCommands extends Commands {
        private final Commands phaseTwo;
        private final Commands phaseThree;

        private ThreeCommands(CommandEnum command, String str, Commands phaseTwo, Commands phaseThree) {
            super(command, str);
            this.phaseTwo = phaseTwo;
            this.phaseThree = phaseThree;
        }

        /**
         * @inheritDoc
         */
        @Override
        public String execute(ListOfTask taskList, boolean print) throws DukeException {
            String response;
            if (super.primaryCommand == CommandEnum.EVENT) {
                response = taskList.addEvent(super.taskDescription, this.phaseTwo.dateTime,
                        this.phaseThree.dateTime, print);
            } else {
                return null;
            }

            // Adding the user's command input into the previous command stack
            if (print) {
                taskList.addCommand(this);
            }
            return response;
        }
    }
}
