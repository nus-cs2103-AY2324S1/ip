package miles;

import miles.task.Deadline;
import miles.task.Event;
import miles.task.Task;
import miles.task.ToDo;

/**
 * Represents the parsing of user input, making sense of what command the user has given.
*/
public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Parser(Storage storage, TaskList taskList) {
        this.ui = new Ui();
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Returns a task number from either a mark or unmark command.
     * 
     * @param command the command that the user inputs: either "mark" or "unmark"
     * @param input   user input
     * @return        task number that the user wants to mark or unmark
     */
    public static int getTaskNumber(String command, String input) {
        String taskNum = input.replace(command + " ", "");
        // remove leading and trailing whitespaces
        String trimmedTaskNum = taskNum.trim();
        return Integer.valueOf(trimmedTaskNum);
    }

    /**
     * Extract the keyword from the input string when the command is a "find".
     * 
     * @param input the input string
     * @return      the keyword to find
     */
    public static String getKeywordToFind(String input) {
        String keyword = input.replace("find ", "");
        return keyword.trim();
    }

    /**
     * Parses the input that the user gives.
     * 
     * @param input  user input
     * @return       a boolean that indicates whether the program should break out of the while loop
    */
    public boolean parse(String input) {
        int n = this.taskList.getSize();
        boolean toBreak = false;

        if (input.equals("bye")) {
            toBreak = true;
        } else if (input.equals("")) {
            MilesExceptionHandler.handleEmptyInput();
        } else if (input.equals("list")) {
            this.taskList.displayList();
        } else if (input.contains("unmark")) {
            // mark is in the word unmark so this case has to go first
            int taskNum = getTaskNumber("unmark", input);
            this.taskList.markTaskAsUndone(taskNum);
            this.storage.saveFile(this.taskList);
        } else if (input.contains("mark")) {
            int taskNum = getTaskNumber("mark", input);
            this.taskList.markTaskAsDone(taskNum);
            this.storage.saveFile(this.taskList);
        } else if (input.contains("todo")) {
            try {
                ToDo newToDo = new ToDo(input);
                this.storage.saveWhenAddTask(newToDo, this.taskList);
                this.ui.printAddedTask(newToDo, n + 1);
            } catch (IllegalArgumentException e) {
                MilesExceptionHandler.printErrorMsg(e.getMessage());
            }
        } else if (input.contains("deadline")) {
            try {
                Deadline newDeadline = new Deadline(input);
                this.storage.saveWhenAddTask(newDeadline, this.taskList);
                this.ui.printAddedTask(newDeadline, n + 1);
            } catch (IllegalArgumentException e) {
                MilesExceptionHandler.printErrorMsg(e.getMessage());
            } 
        } else if (input.contains("event")) {
            try {
                Event newEvent = new Event(input);
                this.storage.saveWhenAddTask(newEvent, this.taskList);
                this.ui.printAddedTask(newEvent, n + 1);
            } catch (IllegalArgumentException e) {
                MilesExceptionHandler.printErrorMsg(e.getMessage());
            } 
        } else if (input.contains("delete")) {
            try {
                int taskNum = getTaskNumber("delete", input);
                Task deletedTask = taskList.deleteTask(taskNum - 1);
                this.storage.saveFile(this.taskList);
                this.ui.printDeletedTask(deletedTask, n - 1);
            } catch (IndexOutOfBoundsException e) {
                int taskNum = getTaskNumber("delete", input);
                MilesExceptionHandler.handleTaskNumOutOfBounds(taskNum);
            }
        } else if (input.contains("find")) {
            String keyword = getKeywordToFind(input);
            this.taskList.displayListWithKeyword(keyword);
        } else {
            MilesExceptionHandler.handleUnseenInput();
        }

        return toBreak;
    }
}
