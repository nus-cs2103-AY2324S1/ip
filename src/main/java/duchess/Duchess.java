package duchess;

import duchess.command.Command;
import duchess.command.CommandType;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * The main class used to execute Duchess actions.
 */
public class Duchess {
    /**
     * Name for the Duchess AI.
     */
    public final String NAME = "Duchess";

    /**
     * An ArrayList to store text in.
     */
    private TaskList storedTasks = new TaskList();

    /**
     * Callback handler, executed after any command is executed.
     * Accepts a string, the String that Duchess prints out.
     */
    private Consumer<String> callbackHandler = null;

    public void setCallbackHandler(Consumer<String> callback) {
        this.callbackHandler = callback;
    }

    /**
     * Executes the callback handler assigned to this Duchess if it is not null.
     *
     * @param s - the String to pass to the callback handler.
     */
    private void executeCallbackHandler(String s) {
        if (this.callbackHandler != null) {
            this.callbackHandler.accept(s);
        }
    }

    /**
     * Tags a task.
     *
     * @param index - the index of the task to be tagged.
     * @param tags  - an array of Strings that represent all the tags to be added to the task.
     */
    private String addTagsToTask(int index, String[] tags) {
        String response = "";

        // Within bounds
        if (index < 0 || index >= this.storedTasks.size()) {
            response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
            this.executeCallbackHandler(response);
            return response;
        }

        Task task = this.storedTasks.getTask(index);

        for (String tag : tags) {
            task.addTag(tag);
        }

        response += Ui.duchessFormat("Tags have been added!! (＾▽＾)");
        response += Ui.duchessFormat(String.format("%d: %s", this.storedTasks.indexOf(task) + 1, task.toString()));

        this.executeCallbackHandler(response);
        return response;
    }

    /**
     * Removes a tag from a task.
     *
     * @param index - the index of the task to be tagged.
     * @param tags  - an array of Strings that represent all the tags to be removed from the task.
     */
    private String removeTagsFromTask(int index, String[] tags) {
        String response = "";

        // Within bounds
        if (index < 0 || index >= this.storedTasks.size()) {
            response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
            this.executeCallbackHandler(response);
            return response;
        }

        Task task = this.storedTasks.getTask(index);

        for (String tag : tags) {
            task.removeTag(tag);
        }

        response += Ui.duchessFormat("Tags have been removed!! (＾▽＾)");
        response += Ui.duchessFormat(String.format("%d: %s", this.storedTasks.indexOf(task) + 1, task.toString()));

        this.executeCallbackHandler(response);
        return response;
    }

    /**
     * Attempts to parse and execute the user input as a Duchess command.
     *
     * @param userInput - the user's input.
     */
    public void parseUserInput(String userInput) {
        Command command = null;

        // Check if this command is a list.
        if (Parser.isListTasksCommand(userInput)) {
            command = Command.getCommand(CommandType.LIST);
            String output = command.execute(userInput, this.storedTasks);
            this.executeCallbackHandler(output);
            return;
        }

        // Check if this command is a mark task command.
        if (Parser.isMarkTaskCommand(userInput)) {
            command = Command.getCommand(CommandType.MARK);
            String output = command.execute(userInput, this.storedTasks);
            this.executeCallbackHandler(output);
            return;
        }

        // Check if this command is an unmarked task command.
        if (Parser.isUnmarkTaskCommand(userInput)) {
            command = Command.getCommand(CommandType.UNMARK);
            String output = command.execute(userInput, this.storedTasks);
            this.executeCallbackHandler(output);
            return;
        }

        // Check if this command is a delete task command.
        if (Parser.isDeleteTaskCommand(userInput)) {
            command = Command.getCommand(CommandType.DELETE);
            String output = command.execute(userInput, this.storedTasks);
            this.executeCallbackHandler(output);
            return;
        }

        // Check if this command is a search task command.
        if (Parser.isSearchTaskCommand(userInput)) {
            command = Command.getCommand(CommandType.SEARCH);
            String output = command.execute(userInput, this.storedTasks);
            this.executeCallbackHandler(output);
            return;
        }

        // Check if this command is a ToDo command.
        if (Parser.isToDoCommand(userInput)) {
            command = Command.getCommand(CommandType.TODO);
            String output = command.execute(userInput, this.storedTasks);
            this.executeCallbackHandler(output);
            return;
        }

        // Check if this command is a Deadline command.
        if (Parser.isDeadlineCommand(userInput)) {
            command = Command.getCommand(CommandType.DEADLINE);
            String output = command.execute(userInput, this.storedTasks);
            this.executeCallbackHandler(output);
            return;
        }

        // Check if this command is an Event command.
        if (Parser.isEventCommand(userInput)) {
            command = Command.getCommand(CommandType.EVENT);
            String output = command.execute(userInput, this.storedTasks);
            this.executeCallbackHandler(output);
            return;
        }

        // Check if this command is an Tag command.
        if (Parser.isAddTagCommand(userInput)) {
            try {
                int tagIndex = Parser.parseAddTagCommandIndex(userInput);
                String[] tags = Parser.parseAddTagCommandTags(userInput);
                this.addTagsToTask(tagIndex, tags);
            } catch (DuchessException e) {
                String response = "";

                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("tag add [task number] [tag name]");

                this.executeCallbackHandler(response);
            }
            return;
        }

        // Check if this command is an Tag command.
        if (Parser.isRemoveTagCommand(userInput)) {
            try {
                int tagIndex = Parser.parseRemoveTagCommandIndex(userInput);
                String[] tags = Parser.parseRemoveTagCommandTags(userInput);
                this.removeTagsFromTask(tagIndex, tags);
            } catch (DuchessException e) {
                String response = "";

                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("tag delete[task number] [tag name]");

                this.executeCallbackHandler(response);
            }
            return;
        }

        this.executeCallbackHandler(Ui.duchessFormat("(´；ω；`) Oopsies... I don't know what that means ;-;"));
    }

    public void run() {
        this.executeCallbackHandler(Ui.printGreeting());

        this.loadTasks();

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

            parseUserInput(userInput);
        }

        sc.close();

        // Save the tasks.
        this.saveTasks();

        this.executeCallbackHandler(Ui.printFarewell());
    }

    public void saveTasks() {
        Storage.saveTasksToFile(this.storedTasks);
    }

    public void loadTasks() {
        // Create the save file, if it does not exist.
        Storage.createSaveFile();

        // Load tasks from the save file.
        this.storedTasks = Storage.loadTasksFromFile();
    }

    public static void main(String[] args) {
        Duchess duchess = new Duchess();
        duchess.setCallbackHandler((s) -> System.out.println(s));
        duchess.run();
    }
}
