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
        int totalItemNumber = this.taskList.length();
        // Using string blank = " "; and splitting by blank will give parsing error later on
        String[] splitStringByBlanks = input.split(" ");
        try {
            switch (command) {
            case BYE:
                this.ui.terminate();
                hasCommands = false;
                break;
            case LIST:
                this.ui.list(this.taskList);
                break;
            case UNMARK: // Order matters, if we check for marks first, unmark falls under mark loop
                // Throw NumberFormatException if not the right number
                int taskNumberUnmark = Integer.parseInt(splitStringByBlanks[1]);
                this.taskList.unmarkTask(taskNumberUnmark - 1, storage);
                break;
            case MARK:
                // The above should throw a NumberFormatException if not the right number
                int taskNumberMark = Integer.parseInt(splitStringByBlanks[1]);
                this.taskList.markTask(taskNumberMark - 1, storage);
                break;
            case TODO:
                // Test whether a ToDos input is valid
                ToDos.taskValidator(input);
                Task toDo = ToDos.createTaskFromInput(input);
                totalItemNumber++;
                this.taskList.addTask(toDo, totalItemNumber, storage);
                break;
            case DEADLINE:
                // Test whether a deadline's input is valid
                Deadline.taskValidator(input);
                Task deadlineTask = Deadline.createTaskFromInput(input);
                totalItemNumber++;
                this.taskList.addTask(deadlineTask, totalItemNumber, storage);
                break;
            case EVENT:
                // Test whether an event's input is valid
                Event.taskValidator(input);
                Task event = Event.createTaskFromInput(input);
                totalItemNumber++;
                this.taskList.addTask(event, totalItemNumber, storage);
                break;
            case DELETE:
                int taskNumberDelete = Integer.parseInt(splitStringByBlanks[1]);
                totalItemNumber--;
                this.taskList.deleteTask(taskNumberDelete - 1, totalItemNumber, storage);
                break;
            case FIND:
                searchEngine.searchValidator(input);
                String keyword = input.split(" ")[1];
                TaskList searchResult = this.searchEngine.search(keyword);
                this.ui.showSearchResult(searchResult);
                break;
            default:
                System.out.println("Bot is broken, restart the entire programme");
                break;
            }
        } catch (NullPointerException e) {
            // If we mark a task number outside the range
            System.out.println(e + "\nPlease enter a valid task number from list");
        } catch (NumberFormatException e) {
            // If we mark a non int task number
            System.out.println("Enter a valid task number that is a integer shown in list");
        } catch (WrongInputException e) {
            System.out.println(e);
        } catch (IndexOutOfBoundsException e) {
            // To catch invalid number inputs for delete
            System.out.println("Please enter a valid task number from the range in list");
        }
    }
    public boolean shouldContinue() {
        return this.hasCommands;
    }

}
