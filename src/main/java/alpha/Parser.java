package alpha;

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
            System.out.println(e1.getMessage() + " Please enter the index of the number you would like to mark.");
            return new InvalidCommand(taskList, fileHandler, ui);
        } catch (InvalidIndexException e2) {
            System.out.println(e2.getMessage() + " Please enter a valid index. To check all valid indices, " +
                    "type \"list\" and press ENTER");
            return new InvalidCommand(taskList, fileHandler, ui);
        } catch (InvalidInputException e3) {
            System.out.println(e3.getMessage() + " Please input something meaningful.");
            return new InvalidCommand(taskList, fileHandler, ui);
        } catch (NumberFormatException e4) {
            System.out.println("Please enter a number.");
            return new InvalidCommand(taskList, fileHandler, ui);
        } catch (InvalidFormatException | MissingInfoException e5) {
            if (e5.getTask() == TaskException.TaskType.TODO) {
                System.out.println(e5.getMessage() + " Please enter a todo in the " +
                        "format \"todo YOUR_DESCRIPTION\"");
            } else if (e5.getTask() == TaskException.TaskType.DEADLINE) {
                System.out.println(e5.getMessage() + " Please enter a deadline in the format " +
                        "\"deadline YOUR_DESCRIPTION /by YOUR_TIME\" ");
            } else if (e5.getTask() == TaskException.TaskType.EVENT) {
                System.out.println(e5.getMessage() + " Please enter an event in the format " +
                        "\"event YOUR_DESCRIPTION " +
                        "/from START_TIME /to END_TIME\" ");
            }
            return new InvalidCommand(taskList, fileHandler, ui);
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
    public Command addEvent(String input) throws MissingInfoException, InvalidFormatException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 3) {
            throw new MissingInfoException("Missing Information!", TaskException.TaskType.EVENT);
        } else {
            String[] splitEvent = input.split("/");
            if (splitEvent[1].startsWith("from") && splitEvent[2].startsWith("to")) {
                Event event = Event.makeEvent(splitEvent[0].substring(6),
                        splitEvent[1].substring(5),
                        splitEvent[2].substring(3));
                return new AddCommand(taskList, fileHandler, ui, event);
            } else {
                throw new InvalidFormatException("Invalid Format!", TaskException.TaskType.EVENT);
            }
        }
    }

    /**
     * Returns a Command that adds a ToDo based on an input.
     *
     * @return a Command that adds a ToDo.
     * @throws MissingInfoException If length of splitInput == 1
     */
    public Command addToDo(String input) throws MissingInfoException {
        String[] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            throw new MissingInfoException("Missing Information!", TaskException.TaskType.TODO);
        } else {
            ToDo todo = ToDo.createToDo(input.substring(5));
            return new AddCommand(taskList, fileHandler, ui, todo);
        }
    }

    /**
     * Returns a Command that adds a deadline based on an input.
     *
     * @return a Command that adds a ToDo.
     * @throws MissingInfoException If length of splitInput == 1
     */
    public Command addDeadline(String input) throws MissingInfoException, InvalidFormatException {
        String[] splitInput = input.split(" ");
        if (splitInput.length < 2) {
            throw new MissingInfoException("Missing Information!", TaskException.TaskType.DEADLINE);
        } else if (input.split("/by").length != 2) {
            throw new InvalidFormatException("Invalid Format!", TaskException.TaskType.DEADLINE);
        } else {
            String[] splitDeadline = input.split("/by");
            Deadline deadline = Deadline.makeDeadline(splitDeadline[0].substring(9),
                    splitDeadline[1]);
            return new AddCommand(taskList, fileHandler, ui, deadline);
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
}
