package duke.util;

import java.io.IOException;
import java.time.LocalDate;

import duke.Duke;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a Parser that parses user input for SeeWhyAre bot to perform corresponding actions.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class Parser {
    //60 underscores.
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private TaskList taskList;
    private Ui ui;

    /**
     * Represents the supported command keywords in SeeWhyAre bot.
     * They are stored as an enumeration.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, FIND, DEADLINE, TODO, EVENT, VIEW, UNKNOWN
    }

    /**
     * Instantiates a parser with the specified task list and user interface.
     *
     * @param taskList The task list to work with.
     * @param ui The user interface to display messages.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Retrieves the command keyword from the user input.
     * The command keyword is the first word from the user input.
     *
     * @param line The user input to be parsed.
     * @return The command keyword extracted from the user input.
     */
    private String getCommand(String line) {
        return line.trim().split("\\s+")[0].toUpperCase();
    }

    /**
     * Parses user input and performs the corresponding action.
     * Available commands:
     * - bye: to end the programme.
     * - list: to list down user's current task list.
     * - mark {number}: to mark the task with the specified task index as done.
     * - unmark {number}: to mark the task with the specified task index as not done yet.
     * - delete {number}: to delete the task with the specified task index in the task list.
     * - find (keyword}: to find the list of tasks that contains the corresponding keyword.
     * - deadline {taskname} /by {time}: to add a new task as a deadline task.
     * - todo {taskname}: to add a new task as a to-do item. (No need to provide time).
     * - event {taskname} /from {starttime} /to {endtime}: to add a new task as an event task (with given start time and end time).
     * - view {date}: list down the tasks that are to be done on that day.
     * Note that the commands are not case-sensitive. For instance: "BYE", "ByE", "bYe" will all be treated as the "bye" command.
     *
     * @param userInput The user's input to be parsed.
     */
    public String parseInput(String userInput) {
        try {
            Command command = Command.valueOf(getCommand(userInput));
            switch (command) {
            case BYE:
                return ui.farewell();
            case LIST:
                return taskList.listAllTasks();
            case MARK:
                return handleMarkTask(userInput);
            case UNMARK:
                return handleUnmarkTask(userInput);
            case DELETE:
                return taskList.deleteTask(userInput);
            case FIND:
                return handleFindTask(userInput);
            case DEADLINE:
                return Deadline.handleDeadlineTask(userInput);
            case TODO:
                return Todo.handleTodoTask(userInput);
            case EVENT:
                return Event.handleEventTask(userInput);
            case VIEW:
                return taskList.viewSchedule(userInput);
            default:
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private String handleMarkTask(String userInput) {
        try {
            String[] words = userInput.split("\\s+");
            int taskIndex = Integer.parseInt(words[1]) - 1;
            return taskList.markTask(taskIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter a valid integer index!\n"
                    + String.format("You currently have %d task(s)", taskList.listOfTasks.size());
        } catch (IOException e) {
            return e.toString();
        }
    }

    private String handleUnmarkTask(String userInput) {
        try {
            String[] words = userInput.split("\\s+");
            int taskIndex = Integer.parseInt(words[1]) - 1;
            return taskList.unmarkTask(taskIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter a valid integer index!\n"
                    + String.format("You currently have %d task(s)", taskList.listOfTasks.size());
        } catch (IOException e) {
            return e.toString();
        }
    }

    private String handleFindTask(String userInput) {
        String keyword = userInput.trim().replaceFirst("find", "").trim();
        return taskList.findTask(keyword);
    }
    private String handleException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            InvalidCommandException exception = new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
            return exception.toString();
        }
        if (e instanceof EmptyDescriptionException ||
                e instanceof IOException || e instanceof InvalidDateException) {
            return e.toString();
        } else {
            return "Very Invalid command! Please enter valid commands";
        }
    }
}
