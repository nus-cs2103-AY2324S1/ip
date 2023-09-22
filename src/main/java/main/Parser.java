package main;

import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import exception.DuplicateException;
import exception.InvalidFormatException;
import exception.InvalidIndexException;
import exception.InvalidInputException;
import exception.MissingIndexException;
import exception.MissingInfoException;
import exception.TaskException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import ui.UI;

import java.time.format.DateTimeParseException;

/**
 * Class that handles the user's inputs and makes sense of them. Uses certain key phrases for different commands.
 *
 * @author Wong Joon Hung
 */
public class Parser {

    private final static String END = "bye";
    private final static String LIST = "list";
    private final static String CHECK = "mark";
    private final static String UNCHECK = "unmark";
    private final static String TODO = "todo";
    private final static String DEADLINE = "deadline";
    private final static String EVENT = "event";
    private final static String DELETE = "delete";
    private final static String FIND = "find";
    private final static int EVENT_DESC_IDX = 6;
    private final static int EVENT_FROM_IDX = 5;
    private final static int EVENT_TO_IDX = 3;
    private final static int DEADLINE_DESC_IDX = 9;
    private final static int TODO_DESC_IDX = 5;

    private FileHandler fileHandler;
    private TaskList taskList;
    private UI ui;



    /**
     * Constructor for the class Parser.
     *
     * @param fileHandler FileHandler to write to.
     * @param taskList    TaskList to add tasks to.
     * @param ui          User Interface to output responses for Alpha.
     */
    public Parser(FileHandler fileHandler, TaskList taskList, UI ui) {
        this.fileHandler = fileHandler;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Returns a command based on the user's input.
     *
     * @param input User input to be parsed.
     * @return Command to be executed.
     */
    public Command parse(String input) {
        String[] splitInput = input.split(" "); // Splits string to check for "mark" or "unmark"
        try {
            if (input.equals(LIST)) {
                return this.list();
            } else if (splitInput[0].equals(CHECK)) {
                return check(input);
            } else if (splitInput[0].equals(UNCHECK)) {
                return uncheck(input);
            } else if (splitInput[0].equals(DEADLINE)) {
                return addDeadline(input);
            } else if (splitInput[0].equals(EVENT)) {
                return addEvent(input);
            } else if (splitInput[0].equals(TODO)) {
                return addToDo(input);
            } else if (input.equals(TODO)) {
                return delete(input);
            } else if (input.equals(END)) {
                return new ExitCommand(taskList, fileHandler, ui);
            } else if (splitInput[0].equals(DELETE)) {
                return delete(input);
            } else if (splitInput[0].equals(FIND)) {
                return find(input);
            } else {
                throw new InvalidInputException("Invalid Input!");
            }
        } catch (MissingIndexException e1) {
            return new InvalidCommand(taskList, fileHandler, ui, e1.getMessage() +
                    " Please enter the index of the number you would like to mark.");
        } catch (InvalidIndexException e2) {
            return new InvalidCommand(taskList, fileHandler, ui, e2.getMessage() +
                    " Please enter a valid index. To check all valid indices, " +
                    "type \"list\" and press ENTER");
        } catch (InvalidInputException e3) {
            return new InvalidCommand(taskList, fileHandler, ui, e3.getMessage()
                    + " Please input something meaningful.");
        } catch (NumberFormatException e4) {
            return new InvalidCommand(taskList, fileHandler, ui, "Please enter a number.");
        } catch (InvalidFormatException | MissingInfoException e5) {
            if (e5.getTask() == TaskException.TaskType.TODO) {
                return new InvalidCommand(taskList, fileHandler, ui, e5.getMessage()
                        + " Please enter a todo in the " +
                        "format \"todo YOUR_DESCRIPTION\"");
            } else if (e5.getTask() == TaskException.TaskType.DEADLINE) {
                return new InvalidCommand(taskList, fileHandler, ui, e5.getMessage()
                        + " Please enter a deadline in the format " +
                        "\"deadline YOUR_DESCRIPTION /by YOUR_TIME\" ");
            } else if (e5.getTask() == TaskException.TaskType.EVENT) {
                return new InvalidCommand(taskList, fileHandler, ui,
                        " Please enter an event in the format " +
                        "\"event YOUR_DESCRIPTION " +
                        "/from START_TIME /to END_TIME\" ");
            }
            return new InvalidCommand(taskList, fileHandler, ui, "");
        } catch (DateTimeParseException e6) {
            return new InvalidCommand(taskList, fileHandler, ui,
                    e6.getMessage() + "The date is in an invalid format! " +
                            "Enter the date in the format YYYY-MM-DD");
        } catch (DuplicateException e7) {
            return new InvalidCommand(taskList, fileHandler, ui, "There's already a duplicate in the " +
                    "task list");
        }
    }

    /**
     * Returns a list command.
     *
     * @return Command that lists all the stored tasks.
     */
    public Command list() {
        return new ListCommand(taskList, fileHandler, ui);
    }

    /**
     * Returns a Command that adds an event based on an input.
     *
     * @return a Command that adds an event.
     * @throws MissingInfoException   If length of splitInput < 3
     * @throws InvalidFormatException If /from and /to are not found within the input.
     */
    public Command addEvent(String input) throws MissingInfoException, InvalidFormatException, DuplicateException {
        String[] splitInput = input.split(" ");

        if (splitInput.length < 3) {
            throw new MissingInfoException("Missing Information!", TaskException.TaskType.EVENT);
        } else {
            String[] splitEvent = input.split("/");
            assert splitEvent.length >= 3 : "Input should have at least 3 parts separated by '/'";
            if (splitEvent[1].startsWith("from") && splitEvent[2].startsWith("to") &&
                    splitEvent[1].length() > EVENT_FROM_IDX && splitEvent[2].length() > EVENT_TO_IDX) {
                Event event = Event.makeEvent(splitEvent[0].substring(EVENT_DESC_IDX),
                        splitEvent[1].substring(EVENT_FROM_IDX),
                        splitEvent[2].substring(EVENT_TO_IDX));
                if (check_Duplicates(event)) {
                    throw new DuplicateException("Duplicate!");
                } else {
                    return new AddCommand(taskList, fileHandler, ui, event);
                }
            } else {
                throw new InvalidFormatException("Invalid Format!", TaskException.TaskType.EVENT);
            }
        }}

    /**
     * Returns a Command that adds a ToDo based on an input.
     *
     * @return a Command that adds a ToDo.
     * @throws MissingInfoException If length of splitInput == 1
     */
    public Command addToDo(String input) throws MissingInfoException, DuplicateException {
        String[] splitInput = input.split(" ");
        assert splitInput.length >= 1 : "Input should have at least 1 part";

        if (splitInput.length == 1) {
            throw new MissingInfoException("Missing Information!", TaskException.TaskType.TODO);
        } else {
            ToDo todo = ToDo.createToDo(input.substring(TODO_DESC_IDX));
            if (!check_Duplicates(todo)) {
                return new AddCommand(taskList, fileHandler, ui, todo);
            } else {
                throw new DuplicateException("Duplicate!");
            }
        }
    }

    /**
     * Returns a Command that adds a deadline based on an input.
     *
     * @return a Command that adds a ToDo.
     * @throws MissingInfoException If length of splitInput == 1
     */
    public Command addDeadline(String input) throws MissingInfoException, InvalidFormatException,
            DateTimeParseException, DuplicateException {
        String[] splitInput = input.split(" ");
        assert splitInput.length >= 1 : "Input should have at least 1 part";

        if (splitInput.length < 2) {
            throw new MissingInfoException("Missing Information!", TaskException.TaskType.DEADLINE);
        } else if (input.split("/by").length != 2) {
            throw new InvalidFormatException("Invalid Format!", TaskException.TaskType.DEADLINE);
        } else {
            String[] splitDeadline = input.split("/by");
            assert splitDeadline.length == 2 : "Input should be split into exactly 2 parts by '/by'";
            Deadline deadline = Deadline.makeDeadline(splitDeadline[0].substring(DEADLINE_DESC_IDX),
                    splitDeadline[1]);
            if (check_Duplicates(deadline)) {
                throw new DuplicateException("Duplicate!");
            } else {
                return new AddCommand(taskList, fileHandler, ui, deadline);
            }
        }
    }

    /**
     * Returns a Command that deletes a task from storage based on an input.
     *
     * @return a Command that deletes a task.
     * @throws MissingIndexException if the index is missing.
     * @throws InvalidIndexException if the index exceeds or is lesser than the number of stored tasks.
     */
    public Command delete(String input) throws MissingIndexException, InvalidIndexException {
        String[] splitInput = input.split(" ");
        assert splitInput.length >= 1 : "Input should have at least 1 part";
        if (splitInput.length == 1) {
            throw new MissingIndexException("Missing Index!");
        } else if (Integer.parseInt(splitInput[1]) > taskList.size()) {
            throw new InvalidIndexException("Invalid Index!");
        } else {
            return new DeleteCommand(taskList, fileHandler, ui, Integer.parseInt(splitInput[1]));
        }
    }

    /**
     * Returns a Command that checks a task based on an index.
     *
     * @return a Command that checks a task.
     * @throws MissingIndexException if the index is missing.
     * @throws InvalidIndexException if the index exceeds or is lesser than the number of stored tasks.
     */
    public Command check(String input) throws MissingIndexException, InvalidIndexException {
        String[] splitInput = input.split(" ");
        assert splitInput.length >= 1 : "Input should have at least 1 part";
        if (splitInput.length == 1) {
            throw new MissingIndexException("Missing Index!");
        } else if (Integer.parseInt(splitInput[1]) > taskList.size() || splitInput.length > 2) {
            throw new InvalidIndexException("Invalid Index!");
        } else {
            return new MarkCommand(taskList, fileHandler, ui, Integer.parseInt(splitInput[1]));
        }
    }

    /**
     * Returns a Command that unchecks a task based on an index.
     *
     * @return a Command that unchecks a task.
     * @throws MissingIndexException if the index is missing.
     * @throws InvalidIndexException if the index exceeds or is lesser than the number of stored tasks.
     */
    public Command uncheck(String input) throws MissingIndexException, InvalidIndexException {
        String[] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new MissingIndexException("Missing Index!");
        } else if (Integer.parseInt(splitInput[1]) > taskList.size() || splitInput.length > 2) {
            throw new InvalidIndexException("Invalid Index!");
        } else {
            return new UnmarkCommand(taskList, fileHandler, ui, Integer.parseInt(splitInput[1]));
        }
    }

    public Command find(String input) throws MissingIndexException {
        String[] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new MissingIndexException("Missing Index!");
        } else {
            splitInput[0] = "";
            return new FindCommand(taskList, fileHandler, ui, String.join(" ", splitInput));
        }
    }

    public boolean check_Duplicates(Task task) {
        boolean is_Duplicate = false;
        for (int i = 0; i < taskList.size(); i++) {
            if (task.equals(taskList.getTask(i))) {
                is_Duplicate = true;
            }
        }
        return is_Duplicate;
    }
}
