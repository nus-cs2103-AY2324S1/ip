package duke.util;

import java.io.IOException;

import duke.Duke;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
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
     * Represents the supported command keywords in SeeWhyAre bot. Stored as an enumeration.
     */
    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, FIND, DEADLINE, TODO, EVENT, UNKNOWN
    }

    /**
     * Constructs a Parser with the specified task list and CLI user interface.
     *
     * @param taskList The task list to work with.
     * @param ui The CLI user interface to display messages.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    private void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
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
     *
     * @param userInput The user's input to be parsed.
     * @throws IOException If there is an issue with I/O operations.
     * @throws EmptyDescriptionException If a description is required but not provided.
     * @throws InvalidCommandException If an invalid command is entered.
     */
    public String parseInput(String userInput)
            throws IOException, EmptyDescriptionException, InvalidCommandException {
        try {
            String firstWord = getCommand(userInput);
            String[] words = userInput.split("\\s+"); // Split input by space, put into array
            Command command; //Use enum
            try {
                command = Command.valueOf(firstWord);
            } catch (IllegalArgumentException e) {
                command = Command.UNKNOWN;
            }

            //A-Enum: Use switch-case instead of if-else for neater code
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
                taskIndex = Integer.parseInt(words[1]) - 1; //Same variable name taskIndex as above
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
            default:
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IOException e) {
            System.err.println(HORIZONTAL_LINE + "\n" + e.toString() + HORIZONTAL_LINE);
            return e.toString();
        } catch (EmptyDescriptionException e) {
            return e.printExceptionMessage();
        } catch (InvalidCommandException e) {
            return e.printExceptionMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please enter valid Integer index!\n"
                    + String.format("You currently have %d task(s)", taskList.listOfTasks.size());
//            System.out.println("     Please enter valid Integer index!");
//            System.out.printf("     You currently have %d tasks", taskList.listOfTasks.size());
        } catch (Exception e) {
            return "Very Invalid command! Please enter valid commands";
        }
    }

}
