package duchess;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class used to execute Duchess actions.
 */
public class DuchessGUI {
    /**
     * Name for the Duchess AI.
     */
    public static final String NAME = "Duchess";

    /**
     * An ArrayList to store text in.
     */
    private static TaskList storedTasks = new TaskList();

    /**
     * Prints all stored text.
     *
     * @param s - the string to be stored.
     */
    private static String listTasks() {
        String returnString = "";

        returnString += Ui.duchessPrintGUI("Here are the things you said!! ヽ(^o^)丿");

        final ArrayList<String> arr = new ArrayList<>();
        DuchessGUI.storedTasks.forEach((Task t, Integer index) -> {
            arr.add(Ui.duchessPrintGUI(String.format("%d: %s", index + 1, DuchessGUI.storedTasks.getTask(index).toString())));
        });

        for(String s : arr) {
            returnString += s;
        }

        return returnString;
    }
    /**
     * Marks a task.
     *
     * @param i - the index of the task to be marked.
     */
    private static String markTask(int index) {
        String returnString = "";

        // Within bounds
        if (index < 0 || index >= DuchessGUI.storedTasks.size()) {
            returnString += Ui.duchessPrintGUI("(´；ω；`) Sorry, no such task exists... ;-;");
            return returnString;
        }

        DuchessGUI.storedTasks.getTask(index).changeStatus(TaskStatus.MARKED);
        returnString += Ui.duchessPrintGUI("Task has been marked!! (＾▽＾)");
        returnString += Ui.duchessPrintGUI(String.format("%d: %s", index + 1, DuchessGUI.storedTasks.getTask(index).toString()));

        return returnString;
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static String unmarkTask(int index) {
        String returnString = "";

        if (index < 0 || index >= DuchessGUI.storedTasks.size()) {
            returnString += Ui.duchessPrintGUI("(´；ω；`) Sorry, no such task exists... ;-;");
            return returnString;
        }

        DuchessGUI.storedTasks.getTask(index).changeStatus(TaskStatus.UNMARKED);
        returnString += Ui.duchessPrintGUI("Task has been unmarked!! (＾▽＾)");
        returnString += Ui.duchessPrintGUI(String.format("%d: %s", index + 1, DuchessGUI.storedTasks.getTask(index).toString()));
        
        return returnString;
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static String deleteTask(int index) {
        String returnString = "";

        if (index < 0 || index >= DuchessGUI.storedTasks.size()) {
            returnString += Ui.duchessPrintGUI("(´；ω；`) Sorry, no such task exists... ;-;");
            return returnString;
        }

        Task task = DuchessGUI.storedTasks.removeTask(index);
        returnString += Ui.duchessPrintGUI("Deleted successfully!! d(*⌒▽⌒*)b");
        returnString += Ui.duchessPrintGUI(String.format("%d: %s", index + 1, task.toString()));
        returnString += Ui.duchessPrintGUI("");
        returnString += Ui.duchessPrintGUI(String.format("Now you have %d task(s)!! ヽ(´▽`)/", DuchessGUI.storedTasks.size()));
        return returnString;
    }

    /**
     * Adds a new Event task with the specified title.
     *
     * @param event - the Event object to be added
     */
    private static String addTask(Task task) {
        String returnString = "";
        DuchessGUI.storedTasks.addTask(task);

        returnString += Ui.duchessPrintGUI("Added successfully!! d(*⌒▽⌒*)b");
        returnString += Ui.duchessPrintGUI(String.format("%d: %s", DuchessGUI.storedTasks.indexOf(task) + 1, task.toString()));
        returnString += Ui.duchessPrintGUI("");
        returnString += Ui.duchessPrintGUI(String.format("Now you have %d task(s)!! ヽ(´▽`)/", DuchessGUI.storedTasks.size()));

        return returnString;
    }

    /**
     * Searches for all tasks by a specified string and prints the results.
     *
     * @param searchString - the term to search for.
     */
    private static String searchTasks(String searchString) {
        String returnString = "";
        TaskList newTaskList = DuchessGUI.storedTasks.filterReplaceNull((Task t) -> t.getName().contains(searchString));

        returnString += Ui.duchessPrintGUI("Here are the things I found!! ヽ(^o^)丿");

        final ArrayList<String> arr = new ArrayList<>();
        newTaskList.forEach((Task t, Integer index) -> {
            if (t != null) {
                arr.add(Ui.duchessPrintGUI(String.format("%d: %s", index + 1, t.toString())));
            }
        });

        for(String s : arr) {
            returnString += s;
        }
        
        return returnString;
    }

    /**
     * Returns, as a String, the response of DuchessGUI when a user input command is given.
     *
     * @param userInput - the user's input.
     * @return            the DuchessGUI's reponse.
     */
    public String getResponse(String userInput) {
        String returnString = "";

        if (Parser.isExitCommand(userInput)) {
            return Ui.printFarewellGUI();
        }

        // Check if this command is a list.
        if (Parser.isListTasksCommand(userInput)) {
            return DuchessGUI.listTasks();
        }

        // Check if this command is a mark task command.
        if (Parser.isMarkTaskCommand(userInput)) {
            try {
                int index = Parser.parseMarkTaskCommand(userInput);
                index -= 1; // 1-indexing for user-facing side
                return DuchessGUI.markTask(index);
            } catch (DuchessException e) {
                returnString += Ui.duchessPrintGUI(e.getMessage());
                returnString += Ui.duchessPrintGUI("(／°▽°)／Try something like this!!");
                returnString += Ui.duchessPrintGUI("mark [task number]");
                return returnString;
            }
        }

        // Check if this command is an unmarked task command.
        if (Parser.isUnmarkTaskCommand(userInput)) {
            try {
                int index = Parser.parseUnmarkTaskCommand(userInput);
                index -= 1; // 1-indexing for user-facing side
                return DuchessGUI.unmarkTask(index);
            } catch (DuchessException e) {
                returnString += Ui.duchessPrintGUI(e.getMessage());
                returnString += Ui.duchessPrintGUI("(／°▽°)／Try something like this!!");
                returnString += Ui.duchessPrintGUI("unmark [task number]");
                return returnString;
            }
        }

        // Check if this command is an unmarked task command.
        if (Parser.isDeleteTaskCommand(userInput)) {
            try {
                int index = Parser.parseDeleteTaskCommand(userInput);
                index -= 1; // 1-indexing for user-facing side
                return DuchessGUI.deleteTask(index);
            } catch (DuchessException e) {
                returnString += Ui.duchessPrintGUI(e.getMessage());
                returnString += Ui.duchessPrintGUI("(／°▽°)／Try something like this!!");
                returnString += Ui.duchessPrintGUI("delete [task number]");
                return returnString;
            }
        }

        // Check if this command is an unmarked task command.
        if (Parser.isSearchTaskCommand(userInput)) {
            try {
                String searchQuery = Parser.parseSearchTaskCommand(userInput);
                return DuchessGUI.searchTasks(searchQuery);
            } catch (DuchessException e) {
                returnString += Ui.duchessPrintGUI(e.getMessage());
                returnString += Ui.duchessPrintGUI("(／°▽°)／Try something like this!!");
                returnString += Ui.duchessPrintGUI("search [query]");
                return returnString;
            }
        }

        // Check if this command is a ToDo command.
        if (Parser.isToDoCommand(userInput)) {
            try {
                ToDo todo = Parser.parseToDoCommand(userInput);
                return DuchessGUI.addTask(todo);
            } catch (DuchessException e) {
                returnString += Ui.duchessPrintGUI(e.getMessage());
                returnString += Ui.duchessPrintGUI("(／°▽°)／Try something like this!!");
                returnString += Ui.duchessPrintGUI("todo [name]");
                return returnString;
            }
        }

        // Check if this command is a Deadline command.
        if (Parser.isDeadlineCommand(userInput)) {
            try {
                Deadline deadline = Parser.parseDeadlineCommand(userInput);
                return DuchessGUI.addTask(deadline);
            } catch (DuchessException e) {
                returnString += Ui.duchessPrintGUI(e.getMessage());
                returnString += Ui.duchessPrintGUI("(／°▽°)／Try something like this!!");
                returnString += Ui.duchessPrintGUI("deadline [name] /by [year-month-date]");
                return returnString;
            }
        }

        // Check if this command is a Event command.
        if (Parser.isEventCommand(userInput)) {
            try {
                Event event = Parser.parseEventCommand(userInput);
                return DuchessGUI.addTask(event);
            } catch (DuchessException e) {
                returnString += Ui.duchessPrintGUI(e.getMessage());
                returnString += Ui.duchessPrintGUI("(／°▽°)／Try something like this!!");
                returnString += Ui.duchessPrintGUI("event [name] /from [time] /to [time]");
                return returnString;
            }
        }

        return Ui.duchessPrintGUI("(´；ω；`) Oopsies... I don't know what that means ;-;");
    }

    public void setStoredTasks(TaskList taskList) {
        DuchessGUI.storedTasks = taskList;
    }

    public TaskList getStoredTasks() {
        return DuchessGUI.storedTasks;
    }
}
