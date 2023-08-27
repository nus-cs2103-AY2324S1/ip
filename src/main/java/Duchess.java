import java.util.Scanner;
import java.util.ArrayList;

import java.util.regex.Matcher;

public class Duchess {
    /**
     * Name for the Duchess AI.
     */
    public static final String NAME = "Duchess";

    /**
     * An ArrayList to store text in.
     */
    private static final ArrayList<Task> storedTasks = new ArrayList<>();

    /**
     * Prints text, but with a consistent formatting in Duchess style.
     */
    private static void duchessPrint(String s) {
        System.out.println(String.format("[D]: %s", s));
    }

    /**
     * Prints greeting to the user and returns.
     */
    private static void printGreeting() {
        Duchess.duchessPrint("(^O^)／ Heya~");
        Duchess.duchessPrint("I am ~~Duchess~~!!");
        Duchess.duchessPrint("What can I do for you!! o_O");
    }
    /**
     * Prints farewell/exit message.
     */
    private static void printFarewell() {
        Duchess.duchessPrint("Bye bye!! Hope to see you again (＾▽＾)");
    }

    /**
     * Outputs the same string parameter that was passed in.
     *
     * @param s - the string to be echoed.
     */
    private static void echo(String s) {
        Duchess.duchessPrint(s);
    }

    /**
     * Prints all stored text.
     *
     * @param s - the string to be stored.
     */
    private static void listTasks() {
        Duchess.duchessPrint("Here are the things you said!! ヽ(^o^)丿");
        for (int i = 0; i < Duchess.storedTasks.size(); i++) {
            Duchess.duchessPrint(String.format("%d: %s", i + 1, Duchess.storedTasks.get(i).toString()));
        }
    }
    /**
     * Marks a task.
     *
     * @param i - the index of the task to be marked.
     */
    private static void markTask(int index) {
        // Within bounds
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Duchess.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Duchess.storedTasks.get(index).changeStatus(TaskStatus.MARKED);
        Duchess.duchessPrint("Task has been marked!! (＾▽＾)");
        duchessPrint(String.format("%d: %s", index + 1, Duchess.storedTasks.get(index).toString()));
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static void unmarkTask(int index) {
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Duchess.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Duchess.storedTasks.get(index).changeStatus(TaskStatus.UNMARKED);
        Duchess.duchessPrint("Task has been unmarked!! (＾▽＾)");
        duchessPrint(String.format("%d: %s", index + 1, Duchess.storedTasks.get(index).toString()));
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     */
    private static void deleteTask(int index) {
        if (index < 0 || index >= Duchess.storedTasks.size()) {
            Duchess.duchessPrint("(´；ω；`) Sorry, no such task exists... ;-;");
            return;
        }

        Task task = Duchess.storedTasks.remove(index);
        Duchess.duchessPrint("Deleted successfully!! d(*⌒▽⌒*)b");
        duchessPrint(String.format("%d: %s", index + 1, task.toString()));
        Duchess.duchessPrint("");
        Duchess.duchessPrint(String.format("Now you have %d task(s)!! ヽ(´▽`)/", Duchess.storedTasks.size()));
    }

    /**
     * Adds a new Event task with the specified title.
     *
     * @param event - the Event object to be added
     */
    private static void addTask(Task task) {
        Duchess.storedTasks.add(task);

        Duchess.duchessPrint("Added successfully!! d(*⌒▽⌒*)b");
        Duchess.duchessPrint(String.format("%d: %s", Duchess.storedTasks.indexOf(task) + 1, task.toString()));
        Duchess.duchessPrint("");
        Duchess.duchessPrint(String.format("Now you have %d task(s)!! ヽ(´▽`)/", Duchess.storedTasks.size()));
    }



    public static void main(String[] args) {
        Duchess.printGreeting();

        // Create the save file, if it does not exist.
        Data.createSaveFile();

        // Load tasks from the save file.
        Data.loadTasksFromFile(Duchess.storedTasks);

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
                }
                catch (DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("mark [task number]");
                }
                continue;
            }

            // Check if this command is an unmarked task command.
            if (Parser.isUnmarkTaskCommand(userInput)) {
                try {
                    int index = Parser.parseUnmarkTaskCommand(userInput);
                    index -= 1; // 1-indexing for user-facing side
                    Duchess.unmarkTask(index);
                }
                catch (DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("unmark [task number]");
                }
                continue;
            }

            // Check if this command is an unmarked task command.
            if (Parser.isDeleteTaskCommand(userInput)) {
                try {
                    int index = Parser.parseDeleteTaskCommand(userInput);
                    index -= 1; // 1-indexing for user-facing side
                    Duchess.deleteTask(index);
                }
                catch (DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("delete [task number]");
                }
                continue;
            }

            // Check if this command is a ToDo command.
            if (Parser.isToDoCommand(userInput)) {
                try {
                    ToDo todo = Parser.parseToDoCommand(userInput);
                    Duchess.addTask(todo);
                }
                catch (DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("todo [name]");
                }
                continue;
            }

            // Check if this command is a Deadline command.
            if (Parser.isDeadlineCommand(userInput)) {
                try {
                    Deadline deadline = Parser.parseDeadlineCommand(userInput);
                    Duchess.addTask(deadline);
                }
                catch(DuchessException e) {                    
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("deadline [name] /by [year-month-date]");
                }
                continue;
            }

            // Check if this command is a Event command.
            if (Parser.isEventCommand(userInput)) {
                try {
                    Event event = Parser.parseEventCommand(userInput);
                    Duchess.addTask(event);
                }
                catch(DuchessException e) {
                    Duchess.duchessPrint(e.getMessage());
                    Duchess.duchessPrint("(／°▽°)／Try something like this!!");
                    Duchess.duchessPrint("event [name] /from [time] /to [time]");
                }
                continue;
            }

            Duchess.duchessPrint("(´；ω；`) Oopsies... I don't know what that means ;-;");
        }

        sc.close();
        
        // Save the tasks.
        Data.saveTasksToFile(Duchess.storedTasks);

        Duchess.printFarewell();
    }
}
