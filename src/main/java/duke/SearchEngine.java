package duke;

/**
 * Class to encapsulate the search engine
 */
public class SearchEngine {

    private Storage searchStorage;

    /**
     * Constructor for SearchEngine
     */
    public SearchEngine() {
        this.searchStorage = Storage.createStorage("./data/search.txt");
    }

    /**
     * Searches the taskList for tasks that contain the input
     * @param input the input to be searched for
     * @return  a TaskList of tasks that contain the input
     */
    public TaskList search(String input) {
        // Clear before every search to prevent file corruption
        this.searchStorage.clearFile();
        TaskList taskList = new TaskList("./data/duke.txt");
        TaskList filteredList = new TaskList("./data/search.txt");
        final TaskList finalFilteredList = filter(taskList, filteredList, input);
        return finalFilteredList;
    }

    /**
     * Filters the inputList for tasks that contain the keyword
     * @param inputList the list to be filtered
     * @param filteredList the list to store the filtered tasks
     * @param keyword the keyword to be searched for
     * @return a TaskList of tasks that contain the keyword
     */
    public TaskList filter(TaskList inputList, TaskList filteredList, String keyword) {
        for (int i = 0; i < inputList.length(); i++) {
            Task task = inputList.getTaskObject(i);
            if (task.getTaskName().contains(keyword)) {
                filteredList.addTaskQuietly(task);
            }
        }
        return filteredList;
    }

    /**
     *  Validates the input to ensure it's not an empty string
     *  @param input the input to be validated
     *  @return true if the input is valid
     */
    public boolean searchValidator(String input) throws WrongInputException {
        if (input.split("find").length < 1) {
            throw new WrongInputException("find <text>", "type find followed by non-space characters");
        }
        String check = input.split("find")[1];
        if (check.trim().isEmpty()) {
            throw new WrongInputException("<text> cannot be empty", "type non-space characters");
        }
        return true;
    }
}
