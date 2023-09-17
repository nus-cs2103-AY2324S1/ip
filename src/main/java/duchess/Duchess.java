package duchess;

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
     * Prints all stored text.
     *
     * @param s - the string to be stored.
     * @return    Duchess' response to the command.
     */
    private String listTasks() {
        // An array of the segments of the response. This is used to prevent the issue where lambdas can only modify final variables.
        final ArrayList<String> responsesArray = new ArrayList<>();

        responsesArray.add(Ui.duchessFormat("Here are the things you said!! ヽ(^o^)丿"));
        this.storedTasks.forEach((Task t, Integer index) -> {
            responsesArray.add(Ui.duchessFormat(String.format("%d: %s", index + 1, this.storedTasks.getTask(index).toString())));
        });

        String response = "";
        for (String responsePart : responsesArray) {
            response += responsePart;
        }

        this.executeCallbackHandler(response);
        return response;
    }

    /**
     * Marks a task.
     *
     * @param i - the index of the task to be marked.
     * @return    Duchess' response to the command.
     */
    private String markTask(int index) {
        String response = "";

        // Within bounds
        if (index < 0 || index >= this.storedTasks.size()) {
            response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
            this.executeCallbackHandler(response);
            return response;
        }

        this.storedTasks.getTask(index).changeStatus(TaskStatus.MARKED);
        response += Ui.duchessFormat("Task has been marked!! (＾▽＾)");
        response += Ui.duchessFormat(String.format("%d: %s", index + 1, this.storedTasks.getTask(index).toString()));

        this.executeCallbackHandler(response);
        return response;
    }

    /**
     * Unmarks a task.
     *
     * @param i - the index of the task to be unmarked.
     * @return    Duchess' response to the command.
     */
    private String unmarkTask(int index) {
        String response = "";

        if (index < 0 || index >= this.storedTasks.size()) {
            response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
            this.executeCallbackHandler(response);
            return response;
        }

        this.storedTasks.getTask(index).changeStatus(TaskStatus.UNMARKED);
        response += Ui.duchessFormat("Task has been unmarked!! (＾▽＾)");
        response += Ui.duchessFormat(String.format("%d: %s", index + 1, this.storedTasks.getTask(index).toString()));

        this.executeCallbackHandler(response);
        return response;
    }

    /**
     * Deletes a task.
     *
     * @param i - the index of the task to be deleted.
     * @return    Duchess' response to the command.
     */
    private String deleteTask(int index) {
        String response = "";

        if (index < 0 || index >= this.storedTasks.size()) {
            response += Ui.duchessFormat("(´；ω；`) Sorry, no such task exists... ;-;");
            this.executeCallbackHandler(response);
            return response;
        }

        Task task = this.storedTasks.removeTask(index);
        response += Ui.duchessFormat("Deleted successfully!! d(*⌒▽⌒*)b");
        response += Ui.duchessFormat(String.format("%d: %s", index + 1, task.toString()));
        response += Ui.duchessFormat("");
        response += Ui.duchessFormat(String.format("Now you have %d task(s)!! ヽ(´▽`)/", this.storedTasks.size()));

        this.executeCallbackHandler(response);
        return response;
    }

    /**
     * Adds a new Event task with the specified title.
     *
     * @param event - the Event object to be added
     * @return    Duchess' response to the command.
     */
    private String addTask(Task task) {
        String response = "";

        assert task != null : "Task is null";

        this.storedTasks.addTask(task);

        response += Ui.duchessFormat("Added successfully!! d(*⌒▽⌒*)b");
        response += Ui.duchessFormat(String.format("%d: %s", this.storedTasks.indexOf(task) + 1, task.toString()));
        response += Ui.duchessFormat("");
        response += Ui.duchessFormat(String.format("Now you have %d task(s)!! ヽ(´▽`)/", this.storedTasks.size()));

        this.executeCallbackHandler(response);
        return response;
    }

    /**
     * Searches for all tasks by a specified string and returns the results.
     *
     * @param searchString - the term to search for.
     * @return    Duchess' response to the command.
     */
    private String searchTasks(String searchString) {
        // An array of the segments of the response. This is used to prevent the issue where lambdas can only modify final variables.
        final ArrayList<String> responsesArray = new ArrayList<>();

        assert searchString != null : "Search string is null";

        TaskList newTaskList = this.storedTasks.filterReplaceNull((Task t) -> t.getName().contains(searchString));

        responsesArray.add(Ui.duchessFormat("Here are the things I found!! ヽ(^o^)丿"));

        newTaskList.forEach((Task t, Integer index) -> {
            if (t != null) {
                responsesArray.add(Ui.duchessFormat(String.format("%d: %s", index + 1, t.toString())));
            }
        });

        String response = "";
        for (String responsePart : responsesArray) {
            response += responsePart;
        }
        
        this.executeCallbackHandler(response);
        return response;
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
        // Check if this command is a list.
        if (Parser.isListTasksCommand(userInput)) {
            this.listTasks();
            return;
        }

        // Check if this command is a mark task command.
        if (Parser.isMarkTaskCommand(userInput)) {
            try {
                int index = Parser.parseMarkTaskCommand(userInput);
                index -= 1; // 1-indexing for user-facing side
                this.markTask(index);
            } catch (DuchessException e) {
                String response = "";

                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("mark [task number]");

                this.executeCallbackHandler(response);
            }
            return;
        }

        // Check if this command is an unmarked task command.
        if (Parser.isUnmarkTaskCommand(userInput)) {
            try {
                int index = Parser.parseUnmarkTaskCommand(userInput);
                index -= 1; // 1-indexing for user-facing side
                this.unmarkTask(index);
            } catch (DuchessException e) {
                String response = "";

                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("unmark [task number]");
                
                this.executeCallbackHandler(response);
            }
            return;
        }

        // Check if this command is a delete task command.
        if (Parser.isDeleteTaskCommand(userInput)) {
            try {
                int index = Parser.parseDeleteTaskCommand(userInput);
                index -= 1; // 1-indexing for user-facing side
                this.deleteTask(index);
            } catch (DuchessException e) {
                String response = "";

                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("delete [task number]");

                this.executeCallbackHandler(response);
            }
            return;
        }

        // Check if this command is a search task command.
        if (Parser.isSearchTaskCommand(userInput)) {
            try {
                String searchQuery = Parser.parseSearchTaskCommand(userInput);
                this.searchTasks(searchQuery);
            } catch (DuchessException e) {
                String response = "";
                
                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("search [query]");

                this.executeCallbackHandler(response);
            }
            return;
        }

        // Check if this command is a ToDo command.
        if (Parser.isToDoCommand(userInput)) {
            try {
                ToDo todo = Parser.parseToDoCommand(userInput);
                this.addTask(todo);
            } catch (DuchessException e) {
                String response = "";

                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("todo [name]");

                this.executeCallbackHandler(response);
            }
            return;
        }

        // Check if this command is a Deadline command.
        if (Parser.isDeadlineCommand(userInput)) {
            try {
                Deadline deadline = Parser.parseDeadlineCommand(userInput);
                this.addTask(deadline);
            } catch (DuchessException e) {
                String response = "";

                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("deadline [name] /by [year-month-date]");

                this.executeCallbackHandler(response);
            }
            return;
        }

        // Check if this command is an Event command.
        if (Parser.isEventCommand(userInput)) {
            try {
                Event event = Parser.parseEventCommand(userInput);
                this.addTask(event);
            } catch (DuchessException e) {
                String response = "";

                response += Ui.duchessFormat(e.getMessage());
                response += Ui.duchessFormat("(／°▽°)／Try something like this!!");
                response += Ui.duchessFormat("event [name] /from [time] /to [time]");

                this.executeCallbackHandler(response);
            }
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
