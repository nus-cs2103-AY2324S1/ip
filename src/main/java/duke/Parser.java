package duke;
/**
 * Parser class that handles the parsing of user input
 */
public class Parser {
    public static final int DEADLINEOFFSET = 9;
    public static final int EVENTOFFSET = 6;

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    private final SearchEngine searchEngine;

    private boolean hasCommands = true;

    /**
     * Constructor for Parser
     * @param taskList  the taskList
     * @param storage   the storage
     * @param ui    the ui
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
        // Split the string based on spaces
        String[] splitString = input.split(" ");
        try {
            switch (command) {
            case BYE:
                this.ui.terminate();
                hasCommands = false;
                break;
            case LIST:
                this.ui.list(this.taskList);
                break;
            case UNMARK:
                // Order matters, if we check for marks first, unmark falls under mark loop
                // Split the string based on spaces
                // The input after the mark word should be task no (which should be index 1)
                int taskNumberUnmark = Integer.parseInt(splitString[1]);
                // The above should throw a NumberFormatException
                this.taskList.unmarkTask(taskNumberUnmark - 1, storage);
                break;
            case MARK:
                // The input after the mark word should be task no (which should be index 1)
                int taskNumberMark = Integer.parseInt(splitString[1]);
                // The above should throw a NumberFormatException if not the right number
                this.taskList.markTask(taskNumberMark - 1, storage);
                break;
            case TODO:
                // Test whether a ToDos input is valid
                ToDos.taskValidator(input);
                // for To-Dos anything after the command is task name
                Task toDo = new ToDos(input.substring(5));
                totalItemNumber++;
                this.taskList.addTask(toDo, totalItemNumber, storage);
                break;
            case DEADLINE:
                // Test whether a deadline's input is valid
                Deadline.taskValidator(input);
                // for Deadline is slightly more complex, we will split by /by
                String[] segementedString = input.split(" /by ");
                // We should only have 2 segments for the Array, before and after
                String deadline = segementedString[1];
                // Hardcoded because we know how words are positioned
                String taskNameDeadline = segementedString[0].substring(DEADLINEOFFSET);
                DateTime deadlineDateTime = DateTime.createDateTime(deadline);
                Task deadlineTask = new Deadline(taskNameDeadline, deadlineDateTime);
                totalItemNumber++;
                this.taskList.addTask(deadlineTask, totalItemNumber, storage);
                break;
            case EVENT:
                // Test whether an event's input is valid
                Event.taskValidator(input);
                // Event, split string twice to get relevant component
                String[] segmentedViaBy = input.split(" /from ");
                String taskNameEvent = segmentedViaBy[0].substring(EVENTOFFSET);
                String[] segmentedViaTo = segmentedViaBy[1].split(" /to ");
                String start = segmentedViaTo[0];
                DateTime startDateTime = DateTime.createDateTime(start);
                String end = segmentedViaTo[1];
                DateTime endDateTime = DateTime.createDateTime(end);
                Task event = new Event(taskNameEvent, startDateTime, endDateTime);
                totalItemNumber++;
                this.taskList.addTask(event, totalItemNumber, storage);
                break;
            case DELETE:
                int taskNumberDelete = Integer.parseInt(splitString[1]);
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
            System.out.println("Please enter a valid task number from the range in  list");
        }
    }
    public boolean shouldContinue() {
        return this.hasCommands;
    }

}
