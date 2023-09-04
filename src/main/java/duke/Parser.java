package duke;

/**
 * This class deals with making sense of the user command
 */
public class Parser {
    private static boolean isExit = false;
    public static boolean isExit() {
        return isExit;
    }
    /**
     * This function takes in the user input and carries out the next steps accordingly
     *
     * @param userInput what the user types in to the console
     * @param tasks the duke.TaskList
     * @param storage the duke.Storage
     * @throws DukeException from all the methods in duke.TaskList
     */
    public static void parse(String userInput, TaskList tasks, Storage storage) throws DukeException {
        if (userInput.equals("list")) {
            tasks.displayList();
        } else if (userInput.equals("bye")) {
            isExit = true;
        } else if (userInput.contains("unmark")) {
            tasks.markDescription(userInput);
        } else if (userInput.contains("mark")) {
            tasks.markDescription(userInput);
        } else if (userInput.contains("todo")) {
            if (userInput.length() <= 5) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                tasks.addTask("T", userInput.substring(5));
            }
        } else if (userInput.contains("deadline")) {
            if (userInput.length() <= 9) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                tasks.addTask("D", userInput.substring(9));
            }
        } else if (userInput.contains("event")) {
            if (userInput.length() <= 6) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            } else {
                tasks.addTask("E", userInput.substring(6));
            }
        } else if (userInput.contains("delete")) {
            tasks.deleteTask(userInput);
        } else if (userInput.contains("find")) {
            tasks.displayMatchingList(userInput.substring(5));
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        storage.updateFile(tasks);
    }
}
