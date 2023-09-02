package duke;

/**
 * Class to encapsulate the search engine
 */
public class SearchEngine {
    private Ui ui;

    private Storage searchStorage;

    /**
     * Constructor for SearchEngine
     * @param ui    the ui
     */
    public SearchEngine(Ui ui) {
        this.ui = ui;
        this.searchStorage = Storage.createStorage("./data/search.txt");
    }

    /**
     * Searches the taskList for tasks that contain the input
     * @param input
     * @return
     */
    public TaskList search(String input) {
        // Clear before every search to prevent file corruption
        this.searchStorage.clearFile();
        TaskList taskList = new TaskList("./data/duke.txt");
        TaskList filteredList = new TaskList("./data/search.txt");
        for (int i = 0; i < taskList.length(); i++) {
            Task task = taskList.getTaskObject(i);
            if (task.getTaskName().contains(input)) {
                filteredList.addTaskQuietly(task);
            }
        }
        return filteredList;
    }

    /**
     *  Validates the input to ensure its not an empty string
     *  @param input    the input to be validated
     *  @return true if the input is valid
     */
    public boolean searchValidator(String input) throws WrongInputException {
        if (input.split("find").length < 1) {
            throw new WrongInputException("find <text>", "type find followed by non-space characters");
        }
        String check = input.split("find")[0];
        if (check.trim().isEmpty()) {
            throw new WrongInputException("<text> cannot be empty", "type non-space characters");
        }
        return true;
    }
}
