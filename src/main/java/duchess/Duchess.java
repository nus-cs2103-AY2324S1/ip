package duchess;

import java.util.Scanner;

/**
 * The main class used to execute Duchess actions.
 */
public class Duchess {
    /**
     * Name for the Duchess AI.
     */
    public static final String NAME = "Duchess";

    /**
     * An ArrayList to store text in.
     */
    private static TaskList storedTasks = new TaskList();
    /**
     * Outputs the same string parameter that was passed in.
     *
     * @param s - the string to be echoed.
     */
    private static void echo(String s) {
        Ui.duchessPrint(s);
    }

    /**
     * Prints all stored text.
     *
     * @param s - the string to be stored.
     */
    private static void listTasks() {
        Ui.duchessPrint("Here are the things you said!! ヽ(^o^)丿");
        Duchess.storedTasks.forEach((Task t, Integer index) -> {
            Ui.duchessPrint(String.format("%d: %s", index + 1, Duchess.storedTasks.getTask(index).toString()));
        });
    }
    /**
     * Marks a task.
     *
     * @param i - the index of the task to be marked.
     */
    private static void markTask(int index) {
        // Within bounds
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Ui.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Duchess.storedTasks.getTask(index).changeStatus(TaskStatus.MARKED);
        Ui.duchessPrint("Task has been marked!! (＾▽＾)");
        Ui.duchessPrint(String.format("%d: %s", index + 1, Duchess.storedTasks.getTask(index).toString()));
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static void unmarkTask(int index) {
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Ui.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Duchess.storedTasks.getTask(index).changeStatus(TaskStatus.UNMARKED);
        Ui.duchessPrint("Task has been unmarked!! (＾▽＾)");
        Ui.duchessPrint(String.format("%d: %s", index + 1, Duchess.storedTasks.getTask(index).toString()));
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static void deleteTask(int index) {
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Ui.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Task task = Duchess.storedTasks.removeTask(index);
        Ui.duchessPrint("Deleted successfully!! d(*⌒▽⌒*)b");
        Ui.duchessPrint(String.format("%d: %s", index + 1, task.toString()));
        Ui.duchessPrint("");
        Ui.duchessPrint(String.format("Now you have %d task(s)!! ヽ(´▽`)/", Duchess.storedTasks.size()));
    }

    /**
     * Adds a new Event task with the specified title.
     *
     * @param event - the Event object to be added
     */
    private static void addTask(Task task) {
        Duchess.storedTasks.addTask(task);

        Ui.duchessPrint("Added successfully!! d(*⌒▽⌒*)b");
        Ui.duchessPrint(String.format("%d: %s", Duchess.storedTasks.indexOf(task) + 1, task.toString()));
        Ui.duchessPrint("");
        Ui.duchessPrint(String.format("Now you have %d task(s)!! ヽ(´▽`)/", Duchess.storedTasks.size()));
    }

    /**
     * Searches for all tasks by a specified string and prints the results.
     *
     * @param searchString - the term to search for.
     */
    private static void searchTasks(String searchString) {
        TaskList newTaskList = Duchess.storedTasks.filterReplaceNull((Task t) -> t.getName().contains(searchString));

        Ui.duchessPrint("Here are the things I found!! ヽ(^o^)丿");

        newTaskList.forEach((Task t, Integer index) -> {
            if (t != null) {
                Ui.duchessPrint(String.format("%d: %s", index + 1, t.toString()));
            }
        });
    }


    public static void main(String[] args) {
        Ui.printGreeting();

        // Create the save file, if it does not exist.
        Storage.createSaveFile();

        // Load tasks from the save file.
        Duchess.storedTasks = Storage.loadTasksFromFile();

        Scanner sc = new Scanner(System.in);
        String userInput = "";

        while (!Parser.isExitCommand(userInput)) {
            System.out.println();
            System.out.print("$>  ");
            userInput = sc.nextLine().trim();
            System.out.println();

            // Check if this command is an exit.
            if (Parser.isExitCommand(userInput)) {
                break;
            }

            // Check if this command is a list.
            if (Parser.isListTasksCommand(userInput)) {
                Duchess.listTasks();
                continue;
            }

            // Check if this command is a mark task command.
            if (Parser.isMarkTaskCommand(userInput)) {
                try {
                    int index = Parser.parseMarkTaskCommand(userInput);
                    index -= 1; // 1-indexing for user-facing side
                    Duchess.markTask(index);
                } catch (DuchessException e) {
                    Ui.duchessPrint(e.getMessage());
                    Ui.duchessPrint("(／°▽°)／Try something like this!!");
                    Ui.duchessPrint("mark [task number]");
                }
                continue;
            }

            // Check if this command is an unmarked task command.
            if (Parser.isUnmarkTaskCommand(userInput)) {
                try {
                    int index = Parser.parseUnmarkTaskCommand(userInput);
                    index -= 1; // 1-indexing for user-facing side
                    Duchess.unmarkTask(index);
                } catch (DuchessException e) {
                    Ui.duchessPrint(e.getMessage());
                    Ui.duchessPrint("(／°▽°)／Try something like this!!");
                    Ui.duchessPrint("unmark [task number]");
                }
                continue;
            }

            // Check if this command is an unmarked task command.
            if (Parser.isDeleteTaskCommand(userInput)) {
                try {
                    int index = Parser.parseDeleteTaskCommand(userInput);
                    index -= 1; // 1-indexing for user-facing side
                    Duchess.deleteTask(index);
                } catch (DuchessException e) {
                    Ui.duchessPrint(e.getMessage());
                    Ui.duchessPrint("(／°▽°)／Try something like this!!");
                    Ui.duchessPrint("delete [task number]");
                }
                continue;
            }

            // Check if this command is an unmarked task command.
            if (Parser.isSearchTaskCommand(userInput)) {
                try {
                    String searchQuery = Parser.parseSearchTaskCommand(userInput);
                    Duchess.searchTasks(searchQuery);
                } catch (DuchessException e) {
                    Ui.duchessPrint(e.getMessage());
                    Ui.duchessPrint("(／°▽°)／Try something like this!!");
                    Ui.duchessPrint("search [query]");
                }
                continue;
            }

            // Check if this command is a ToDo command.
            if (Parser.isToDoCommand(userInput)) {
                try {
                    ToDo todo = Parser.parseToDoCommand(userInput);
                    Duchess.addTask(todo);
                } catch (DuchessException e) {
                    Ui.duchessPrint(e.getMessage());
                    Ui.duchessPrint("(／°▽°)／Try something like this!!");
                    Ui.duchessPrint("todo [name]");
                }
                continue;
            }

            // Check if this command is a Deadline command.
            if (Parser.isDeadlineCommand(userInput)) {
                try {
                    Deadline deadline = Parser.parseDeadlineCommand(userInput);
                    Duchess.addTask(deadline);
                } catch (DuchessException e) {
                    Ui.duchessPrint(e.getMessage());
                    Ui.duchessPrint("(／°▽°)／Try something like this!!");
                    Ui.duchessPrint("deadline [name] /by [year-month-date]");
                }
                continue;
            }

            // Check if this command is a Event command.
            if (Parser.isEventCommand(userInput)) {
                try {
                    Event event = Parser.parseEventCommand(userInput);
                    Duchess.addTask(event);
                } catch (DuchessException e) {
                    Ui.duchessPrint(e.getMessage());
                    Ui.duchessPrint("(／°▽°)／Try something like this!!");
                    Ui.duchessPrint("event [name] /from [time] /to [time]");
                }
                continue;
            }

            Ui.duchessPrint("(´；ω；`) Oopsies... I don't know what that means ;-;");
        }

        sc.close();

        // Save the tasks.
        Storage.saveTasksToFile(Duchess.storedTasks);

        Ui.printFarewell();
    }
}
