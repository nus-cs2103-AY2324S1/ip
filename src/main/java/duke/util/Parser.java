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
            String firstWord = getCommand(userInput);
            String[] words = userInput.split("\\s+"); // Split input by space, put into array
            Command command;
            try {
                command = Command.valueOf(firstWord);
            } catch (IllegalArgumentException e) {
                command = Command.UNKNOWN;
            }

            switch (command) {
            case BYE:
                Duke.setIsFinishedToTrue();
                return ui.farewell();
            case LIST:
                return taskList.listAllTasks();
            case MARK:
                int taskIndex = Integer.parseInt(words[1]) - 1;
                return taskList.markTask(taskIndex);
            case UNMARK:
                taskIndex = Integer.parseInt(words[1]) - 1;
                return taskList.unmarkTask(taskIndex);
            case DELETE:
                return taskList.deleteTask(userInput);
            case FIND:
                String keyword = userInput.trim().replaceFirst("find", "").trim();
                return taskList.findTask(keyword);
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
        } catch (IOException e) {
            System.err.println(HORIZONTAL_LINE + "\n" + e + HORIZONTAL_LINE);
            return e.toString();
        } catch (EmptyDescriptionException e) {
            return e.printExceptionMessage();
        } catch (InvalidCommandException e) {
            return e.printExceptionMessage();
        } catch (InvalidDateException e) {
            return e.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter valid Integer index!\n"
                    + String.format("You currently have %d task(s)", taskList.listOfTasks.size());
        } catch (Exception e) {
            return "Very Invalid command! Please enter valid commands";
        }
    }

}
