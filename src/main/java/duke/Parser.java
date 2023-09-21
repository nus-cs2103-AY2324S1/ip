package duke;
/**
 * Parser class that handles the parsing of user input
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final SearchEngine searchEngine;
    private boolean hasCommands = true;

    /**
     * Constructor for Parser
     * @param taskList the taskList
     * @param storage the storage
     * @param ui the ui
     */
    public Parser(TaskList taskList, Storage storage, Ui ui, SearchEngine searchEngine) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.searchEngine = searchEngine;
    }

    /**
     * Parses the user input and executes the relevant command
     * @param input the user's input
     * @param command   the command to be executed
     */
    public void parse(String input, Command command) {
        String[] splitStringByBlanks = input.split(" ");
        try {
            switch (command) {
            case BYE:
                bye();
                break;
            case LIST:
                this.ui.list(this.taskList);
                break;
            case SHOWTAGS:
                this.ui.showTags(this.taskList);
                break;
            case UNMARK: // unmark needs to come before mark because we use startsWith to check for command
                unmark(splitStringByBlanks[1]);
                break;
            case MARK:
                mark(splitStringByBlanks[1]);
                break;
            case TODO:
                createToDoTask(input);
                break;
            case DEADLINE:
                createDeadlineTask(input);
                break;
            case EVENT:
                createEventTask(input);
                break;
            case DELETE:
                deleteTask(splitStringByBlanks[1]);
                break;
            case FIND:
                findTask(input, splitStringByBlanks[1]);
                break;
            case TAG:
                tagTask(input, splitStringByBlanks[1], splitStringByBlanks[2]);
                break;
            case REMOVETAG:
                removeTagTask(input, splitStringByBlanks[1], splitStringByBlanks[2]);
                break;
            default:
                // WHY: If it has reach the default statement, the command is not valid, program should be stopped
                assert false : "Invalid command";
            }
        } catch (NumberFormatException e) {
            // If we mark, unmark or delete  a non int task number
            System.out.println("You have enter a non-integer number\n"
                    + "Enter a valid task number that is a integer shown in list");
        } catch (WrongInputException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You have entered a task number outside the valid range\n"
                    + "Please enter a valid task number from list");
        }
    }
    /**
     * Returns whether the chatbot should continue running
     * @return whether the chatbot should continue running
     */
    public boolean shouldContinue() {
        return this.hasCommands;
    }

    /**
     * Terminates the chatbot
     */
    public void bye() {
        this.ui.terminate();
        this.hasCommands = false;
    }

    /**
     * Marks the task with the given task number
     * @param taskNumber  the task number
     */
    public void unmark(String taskNumber) throws NumberFormatException, NullPointerException {
        int taskNumberUnmark = Integer.parseInt(taskNumber);
        this.taskList.unmarkTask(taskNumberUnmark - 1, this.storage);
    }
    /**
     * Unmarks the task with the given task number
     * @param taskNumber  the task number
     * @throws NumberFormatException if the task number is not a number
     * @throws NullPointerException if the task number is null
     */
    public void mark(String taskNumber) throws NumberFormatException, NullPointerException {
        int taskNumberMark = Integer.parseInt(taskNumber);
        this.taskList.markTask(taskNumberMark - 1, this.storage);
    }

    /**
     * Creates a To-Do task
     * @param input the user's input
     * @throws WrongInputException if the input is invalid
     */
    public void createToDoTask(String input) throws WrongInputException {
        ToDos.taskValidator(input);
        Task toDo = ToDos.createTaskFromInput(input);
        int totalItemNumber = this.taskList.length();
        totalItemNumber++;
        this.taskList.addTask(toDo, totalItemNumber, this.storage);
    }

    /**
     * Creates a Deadline task
     * @param input the user's input
     * @throws WrongInputException if the input is invalid
     */
    public void createDeadlineTask(String input) throws WrongInputException {
        Deadline.taskValidator(input);
        Task deadlineTask = Deadline.createTaskFromInput(input);
        int totalItemNumber = this.taskList.length();
        totalItemNumber++;
        this.taskList.addTask(deadlineTask, totalItemNumber, this.storage);
    }

    /**
     * Creates an Event task
     * @param input the user's input
     * @throws WrongInputException if the input is invalid
     */
    public void createEventTask(String input) throws WrongInputException {
        Event.taskValidator(input);
        Task event = Event.createTaskFromInput(input);
        int totalItemNumber = this.taskList.length();
        totalItemNumber++;
        this.taskList.addTask(event, totalItemNumber, this.storage);
    }

    /**
     * Deletes the task with the given task number
     * @param taskNumber the task number
     * @throws NumberFormatException if the task number is not a number
     * @throws NullPointerException if the task number is null
     * @throws IndexOutOfBoundsException if the task number is out of bounds
     */
    public void deleteTask(String taskNumber) throws NumberFormatException, NullPointerException,
            IndexOutOfBoundsException {
        int taskNumberDelete = Integer.parseInt(taskNumber);
        int totalItemNumber = this.taskList.length();
        totalItemNumber--;
        this.taskList.deleteTask(taskNumberDelete - 1, totalItemNumber, this.storage);
    }

    /**
     * Finds the tasks with the given keyword
     * @param input the user's input
     * @param keyword the keyword to search for
     * @throws WrongInputException if the input is invalid
     */
    public void findTask(String input, String keyword) throws WrongInputException {
        this.searchEngine.searchValidator(input);
        TaskList searchResult = this.searchEngine.search(keyword);
        this.ui.showSearchResult(searchResult);
    }

    /**
     * Tags the task with the given task number
     * @param input the user's input
     * @param taskNumber the task number
     * @param tag  the tag to be added
     * @throws WrongInputException if the input is invalid
     */
    public void tagTask(String input, String taskNumber, String tag) throws WrongInputException {
        Task.tagValidator(input);
        int taskNumberTag = Integer.parseInt(taskNumber);
        this.taskList.updateTags(taskNumberTag - 1, tag, this.storage);
    }

    /**
     * Removes the tag from the task with the given task number
     * @param input the user's input
     * @param taskNumber the task number
     * @param tagToRemove the tag to be removed
     * @throws WrongInputException if the input is invalid
     */
    public void removeTagTask(String input, String taskNumber, String tagToRemove) throws WrongInputException {
        Task.tagValidator(input);
        int taskNumberRemoveTag = Integer.parseInt(taskNumber);
        this.taskList.removeTag(taskNumberRemoveTag - 1, tagToRemove, this.storage);
    }
}
